package libledctrl;

/**
 * Created by hexiongjun on 12/11/15.
 */
public class HWLedCtrl {
    public static native int ledctrl(int which, int status);
    public static native int ledopen();
    public static native void ledclose();

    static {
        try {
            System.loadLibrary("ledctrl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
