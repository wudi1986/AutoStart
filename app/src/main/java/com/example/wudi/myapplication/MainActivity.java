package com.example.wudi.myapplication;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((TextView)findViewById(R.id.textView)).setText("开机自启动");
//                Intent Intent = new Intent();
//                Intent.setClassName("com.touchsprite.android", "com.touchsprite.android.activity.Activity_Login");
//                startActivity(Intent);
//                onKeyDown(KeyEvent.KEYCODE_VOLUME_DOWN,null);
                KeyEvent event = new KeyEvent(System.currentTimeMillis(),KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_VOLUME_DOWN,0,
                        0,-1,0,0x0,0x0);
                onKeyDown(KeyEvent.KEYCODE_BACK,event);
                Log.i(TAG, "run: "+KeyEvent.KEYCODE_BACK);


                Log.d(TAG, "reboot");
                Intent reboot = new Intent(Intent.ACTION_REBOOT);
                reboot.putExtra("nowait", 1);
                reboot.putExtra("interval", 1);
                reboot.putExtra("window", 0);
                sendBroadcast(reboot);
            }
        }, 3000);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyDown: " + keyCode);
        Log.i(TAG, "event: " + event);

        return super.onKeyDown(keyCode, event);
    }
}

