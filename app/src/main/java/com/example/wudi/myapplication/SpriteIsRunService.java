package com.example.wudi.myapplication;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by wudi on 16/5/13.
 */
public class SpriteIsRunService extends Service {

    private static final String TAG = "aaa";
    boolean isAppRunning;
    long startTime;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        flags = START_STICKY;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    startTime = System.currentTimeMillis();
                    try {


                        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
                        for (ActivityManager.RunningTaskInfo info : list) {
                            if (info.topActivity.getPackageName().equals("com.touchsprite.android") || info.baseActivity.getPackageName().equals("com.touchsprite.android")) {
                                isAppRunning = true;
                                //find it, break
                                break;
                            }
                        }
                        if (!isAppRunning) {
                            Intent Intent = new Intent();
                            Intent.setClassName("com.touchsprite.android", "com.touchsprite.android.activity.Activity_Login");
                            startActivity(Intent);
                        }
                        long sleepTime = 30 * 60 * 1000 - (System.currentTimeMillis()-startTime);
                        Thread.sleep(sleepTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Intent localIntent = new Intent();
        localIntent.setClass(this, SpriteIsRunService.class); //销毁时重新启动Service
        this.startService(localIntent);
    }


    /**
     * 强制停止应用程序
     *
     * @param pkgName
     */
    private void forceStopPackage(String pkgName, Context context) throws Exception {
        Log.e(TAG, "forceStopPackage");
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        Method method = Class.forName("android.app.ActivityManager").getMethod("forceStopPackage", String.class);
        method.invoke(am, pkgName);
        Log.e(TAG, "forceStopPackage   \n" +
                "        Log.i(TAG, \"forceStopPackage\");");
    }
}
