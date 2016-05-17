package com.example.wudi.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by wudi on 16/5/12.
 */
public class BootBroadcastReceiver extends BroadcastReceiver{

    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {

//        if (intent.getAction().equals(ACTION)){
            Intent sayHelloIntent=new Intent(context,SpriteIsRunService.class);
            context.startService(sayHelloIntent);
//        }
    }
}
