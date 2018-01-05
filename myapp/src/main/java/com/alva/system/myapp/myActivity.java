package com.alva.system.myapp;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2018/1/5.
 */

public class myActivity extends Activity{
    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取设备管理器
        mDevicePolicyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, LockReceiver.class);
        // 判断该组件是否有系统管理员的权限
        if (mDevicePolicyManager.isAdminActive(mComponentName)) {

            mDevicePolicyManager.lockNow(); //锁屏
        } else {
            activeManager();    //激活权限
        }
//        onDestroy();
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 激活设备管理器获取权限
     */
    private void activeManager() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "一键锁屏");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("yangjie","onDestroy");
        //结束进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("yangjie","onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("yangjie","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("yangjie","onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("yangjie","onRestart");
    }
}
