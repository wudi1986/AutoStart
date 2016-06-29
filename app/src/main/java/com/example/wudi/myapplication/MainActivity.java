package com.example.wudi.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.nfc.Tag;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    private ImageView mIVSign;
    private TextView tv;
    private Bitmap mSignBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
        mIVSign = (ImageView) findViewById(R.id.mIVSign);


        tv.setText("开机自启动");
        Log.i(TAG, "onCreate: onCreateonCreateonCreate");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Intent = new Intent();
                Intent.setClassName("com.touchsprite.android", "com.touchsprite.android.activity.Activity_Login");
                startActivity(Intent);


//                Intent localIntent = new Intent();
//                localIntent.setClass(MainActivity.this, SpriteIsRunService.class);
//                startService(localIntent);

//                Log.d(TAG, "reboot");
//                Intent reboot = new Intent(Intent.ACTION_REBOOT);
//                reboot.putExtra("nowait", 1);
//                reboot.putExtra("interval", 1);
//                reboot.putExtra("window", 0);
//                sendBroadcast(reboot);


            }
        }, 3000);
//        finish();
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WritePadDialog mWritePadDialog = new WritePadDialog(
                        MainActivity.this, new WriteDialogListener() {

                    @Override
                    public void onPaintDone(Object object) {
                        mSignBitmap = (Bitmap) object;
                        createSignFile();
                        mIVSign.setImageBitmap(mSignBitmap);
//                        mTVSign.setVisibility(View.GONE);
                    }
                });

                mWritePadDialog.show();
            }
        });
        //创建签名文件
    }

    private void createSignFile() {
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        String path = null;
        File file = null;
        try {
            path = Environment.getExternalStorageDirectory() + File.separator + System.currentTimeMillis() + ".jpg";
            file = new File(path);
            fos = new FileOutputStream(file);
            baos = new ByteArrayOutputStream();
            //如果设置成Bitmap.compress(CompressFormat.JPEG, 100, fos) 图片的背景都是黑色的
            mSignBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            if (b != null) {
                fos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



