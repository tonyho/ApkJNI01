//
// Created by hexiongjun on 12/9/15.
//

//#define LOG_TAG "LedService"

#include "jni.h"
//#include "JNIHelp.h"
//#include "android_runtime/AndroidRuntime.h"

//#include <utils/misc.h>
//#include <utils/Log.h>
//#include <hardware_legacy/led.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>


#define ALOGI printf
#define LED_NUM 3
int leds_fd[LED_NUM];
char path_buff[255];

static jint ledctrl(JNIEnv *env, jobject clazz, jint which, jint status)
{
    int ret = -1;
    if(status == 1) {
        ret = write(leds_fd[which], "255", 3);
    } else {
        ret = write(leds_fd[which], "0", 1);
    }
    if(ret < 0){
        return -1;
    }
    ALOGI("Native ctrl fd = [%d]\n", which);
    return 0;
}

static jint ledopen(JNIEnv *env, jobject clazz)
{
    int i = 0;
	printf("Native Open\n");
    for(i=0; i<LED_NUM; i++){
        sprintf(path_buff, "/sys/class/leds/led%d/brightness", i);
        printf("path:%s\n",path_buff);
        leds_fd[i] = open(path_buff, O_RDWR);

        if(leds_fd[i] < 0){
            ALOGI("led%d: %s, open failed\n", i, path_buff);
            return -1;
        } else {
            ALOGI("led%d: %s, open success\n", i, path_buff);
        }
    }
    return 0;
}

static void ledclose(JNIEnv *env, jobject clazz)
{
    int i = 0;
    for(i=0; i< LED_NUM; i++){
        close(leds_fd[i]);
    }
}

static JNINativeMethod method_table[] = {
    { "ledctrl", "(II)I", (void*)ledctrl },
    { "ledclose", "()V", (void*)ledclose },
    { "ledopen", "()I", (void*)ledopen }
};

#if 0
int register_android_server_LedService(JNIEnv *env)
{
    return jniRegisterNativeMethods(env, "libledctrl",
            method_table, NELEM(method_table));
}
#endif

JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *jvm, void *reserved)
{
	JNIEnv *env;
	jclass cls;

	printf("Enter the JNI Onload\n");
	if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_4)) {
		printf("Version Error in JNI OnLoad\n");
		return JNI_ERR; /* JNI version not supported */
	}
	// Package/ClassName
	// The package is define in the java first line
	cls = (*env)->FindClass(env, "libledctrl/HWLedCtrl");
	if (cls == NULL) {
		return JNI_ERR;
	}

	/* 2. map java hello <-->c c_hello */
	if ((*env)->RegisterNatives(env, cls, method_table, sizeof(method_table)/sizeof(JNINativeMethod)) < 0)
		return JNI_ERR;

	return JNI_VERSION_1_4;
}
