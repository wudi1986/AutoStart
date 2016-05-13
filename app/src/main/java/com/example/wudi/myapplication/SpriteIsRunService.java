package com.example.wudi.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wudi on 16/5/13.
 */
public class SpriteIsRunService extends Service{

    private static final String TAG = "SpriteIsRunService";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;
        Log.i(TAG, "onStartCommand: SpriteIsRunService");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
