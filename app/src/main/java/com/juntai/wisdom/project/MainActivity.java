package com.juntai.wisdom.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.juntai.disabled.basecomponent.utils.ActivityManagerTool;
import com.juntai.wisdom.project.base.BaseAppActivity;
import com.juntai.wisdom.project.base.customview.CustomViewPager;
import com.juntai.wisdom.project.base.customview.MainPagerAdapter;
import com.juntai.wisdom.project.control.ControlFragment;
import com.juntai.wisdom.project.home_page.HomePageFragment;
import com.juntai.wisdom.project.mine.MyCenterFragment;
import com.juntai.wisdom.project.utils.HawkProperty;
import com.orhanobut.hawk.Hawk;

public class MainActivity extends BaseAppActivity<MainPagePresent> implements ViewPager.OnPageChangeListener,
        View.OnClickListener, MainPageContract.IMainPageView {
    private MainPagerAdapter adapter;
    private LinearLayout mainLayout;
    private CustomViewPager mainViewpager;

    private TabLayout mainTablayout;
    private String[] title = new String[]{"首页", "工作", "我的"};
    private int[] tabDrawables = new int[]{R.drawable.home_index, R.drawable.home_msg, R.drawable.home_msg};
    private SparseArray<Fragment> mFragments = new SparseArray<>();


    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initToolbarAndStatusBar(false);
        mainViewpager = findViewById(R.id.main_viewpager);
        mainTablayout = findViewById(R.id.main_tablayout);
        mainLayout = findViewById(R.id.main_layout);
        mainViewpager.setScanScroll(false);
        mFragments.append(0, new HomePageFragment());//
        mFragments.append(1, new ControlFragment());//
        mFragments.append(2, new MyCenterFragment());//
        mainViewpager.setOffscreenPageLimit(5);
        initTab();
    }


    @Override
    public void initData() {
        update(false);
    }


    public void initTab() {
        adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(), title, tabDrawables,
                mFragments);
        mainViewpager.setAdapter(adapter);
        mainViewpager.setOffscreenPageLimit(title.length);
        /*viewpager切换监听，包含滑动点击两种*/
        mainViewpager.addOnPageChangeListener(this);
        for (int i = 0; i < title.length; i++) {
            TabLayout.Tab tab = mainTablayout.newTab();
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i, false));
                mainTablayout.addTab(tab);
            }
        }

        mainTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainViewpager.setCurrentItem(tab.getPosition(), false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        /*viewpager切换默认第一个*/
        mainViewpager.setCurrentItem(0);
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


    @Override
    protected MainPagePresent createPresenter() {
        return new MainPagePresent();
    }


    @Override
    public void onBackPressed() {
        showAlertDialog("请选择退出方式", "退出", "挂起", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyApp.app.isFinish = true;
                ActivityManagerTool.getInstance().finishApp();
            }
        }, new DialogInterface.OnClickListener() {
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
        });
    }


}
