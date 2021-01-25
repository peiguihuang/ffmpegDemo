package com.gui.ffmpegdemo;


import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import android.view.View;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends Activity implements View.OnClickListener , EasyPermissions.PermissionCallbacks{
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    TextView tvInfo;
    private FFVideoPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_protocol).setOnClickListener(this);
        findViewById(R.id.btn_codec).setOnClickListener(this);
        findViewById(R.id.btn_filter).setOnClickListener(this);
        findViewById(R.id.btn_format).setOnClickListener(this);
        findViewById(R.id.btn_player).setOnClickListener(this);
        mPlayer = findViewById(R.id.ffVideoPlayer);

        tvInfo = findViewById(R.id.tv_info);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_protocol:
                tvInfo.setText(urlprotocolinfo());
                break;
            case R.id.btn_format:
                tvInfo.setText(avformatinfo());
                break;
            case R.id.btn_codec:
                tvInfo.setText(avcodecinfo());
                break;
            case R.id.btn_filter:
                tvInfo.setText(avfilterinfo());
                break;
            case R.id.btn_player:
                mPlayer.play("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
                break;
            default:
                break;
        }
    }

    public native String stringFromJNI();

    public native String urlprotocolinfo();

    public native String avformatinfo();

    public native String avcodecinfo();

    public native String avfilterinfo();




    private void initEasypermissions() {
        String[] permissions = {
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.VIBRATE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };

        if (!EasyPermissions.hasPermissions(this, permissions)) {
            Log.e("Lyz", "..........requestPermissions................");
            EasyPermissions.requestPermissions(this, "点击确定应用需要获取的权限", 101, permissions);
        }
    }

    //成功
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms != null && perms.size() > 0) {
            for (int i = 0; i < perms.size(); i++) {
                Log.e("Lyz", "...ok...perms......." + i + "....." + perms.get(i));
            }
            Log.e("Lyz", "..........onPermissionsGranted................");
            if (EasyPermissions.hasPermissions(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            }
        }
    }

    //失败
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (perms != null && perms.size() > 0) {
            for (int i = 0; i < perms.size(); i++) {
                Log.e("Lyz", "..faild....perms......." + i + "....." + perms.get(i));
            }
            Log.e("Lyz", "..........onPermissionsDenied................");
        }
    }

}
