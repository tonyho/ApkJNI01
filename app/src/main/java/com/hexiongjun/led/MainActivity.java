package com.hexiongjun.led;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button = null;
    private CheckBox led1 = null;
    private CheckBox led2 = null;
    private CheckBox led3 = null;
    boolean ledon = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        led1 = (CheckBox) findViewById(R.id.led1);
        led2 = (CheckBox) findViewById(R.id.led2);
        led3 = (CheckBox) findViewById(R.id.led3);

        button = (Button) findViewById(R.id.BUTTON);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ledon = !ledon;
                if (ledon){
                    button.setText("ALL OFF");
                    led1.setChecked(true);
                    led2.setChecked(true);
                    led3.setChecked(true);
                } else{
                    button.setText("ALL ON");
                    led1.setChecked(false);
                    led2.setChecked(false);
                    led3.setChecked(false);
                }
            }
        });
    }

    // leds handler
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.led1:
                if (checked)
                    Toast.makeText(getApplicationContext(), "LED1 checked", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "LED1 unchecked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.led2:
                if (checked)
                    Toast.makeText(getApplicationContext(), "LED2 checked", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "LED2 unchecked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.led3:
                if (checked)
                    Toast.makeText(getApplicationContext(), "LED3 checked", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "LED3 unchecked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
