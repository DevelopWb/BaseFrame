package a3phone.of.com.main;


import a3phone.of.com.main.base.BaseAppActivity;
import a3phone.of.com.main.base.customview.a3levelcombo.A3LevelCombo;
import a3phone.of.disabled.basecomponent.mvp.BasePresenter;

public class TestActivity extends BaseAppActivity {


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public int getLayoutView() {
        return R.layout.activity_test;
    }

    public void initView() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
