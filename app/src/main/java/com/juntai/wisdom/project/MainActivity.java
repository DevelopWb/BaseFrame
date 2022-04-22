package com.juntai.wisdom.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;



import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.ActivityManagerTool;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.service.LocateAndUpload;
import com.juntai.wisdom.project.base.BaseAppActivity;
import com.juntai.wisdom.project.entrance.LoginActivity;
import com.juntai.wisdom.project.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述  首页
 * @date 2022/4/22 14:39
 */
public class MainActivity extends BaseAppActivity<MainPagePresent> implements ViewPager.OnPageChangeListener,
        View.OnClickListener, MainPageContract.IMainPageView {
    //
    CGBroadcastReceiver broadcastReceiver = new CGBroadcastReceiver();



    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_msg, R.id.navigation_mine)
                .build();
        // 設置ActionBar跟随联动
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // 设置Nav跟随联动
        NavigationUI.setupWithNavController(navView, navController);

        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionConfig.BROAD_LOGIN);
        intentFilter.addAction(ActionConfig.BROAD_CASE_DETAILS);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void initData() {
        update(false);
    }



    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {

    }


    /**
     * 登录被顶
     */
    public class CGBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ActionConfig.BROAD_LOGIN.equals(intent.getAction())) {
                ToastUtils.info(MyApp.app, "账号在另一设备登录");
                UserInfoManager.clearUserData();
                ActivityManagerTool.getInstance().finishApp();
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        }
    }

    @Override
    protected MainPagePresent createPresenter() {
        return new MainPagePresent();
    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {

    }

    @Override
    public boolean requestLocation() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.e("EEEEEEEEEE", " = MainActivity  onDestroy");
        stopService(new Intent(MainActivity.this, LocateAndUpload.class));
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(mContext)
                .setMessage("请选择退出方式")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.app.isFinish = true;
                        finish();
                    }
                })
                .setNegativeButton("挂起", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //模拟home键,发送广播
                        //sendBroadcast(new Intent().setAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                        // .putExtra("reason","homekey"));
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }



}
