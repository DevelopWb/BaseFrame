package a3phone.of.com.main.xietong;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseAppFragment;
import a3phone.of.com.main.base.customview.CustomViewPager;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/14 20:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/14 20:27
 */
public class XieTongFragment extends BaseAppFragment<XieTongPresent> implements BaseIView, View.OnClickListener {
    private TabLayout mXietongTablayout;
    private ImageView mMoreMenuBtn;
    private CustomViewPager mViewpager;
    private XieTongPagerAdapter adapter;

    @Override
    protected XieTongPresent createPresenter() {
        return null;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.xietong_fragment;
    }

    @Override
    protected void initView() {
        getBaseAppActivity().setTitleBg(R.drawable.sp_filled_white);
        mXietongTablayout = (TabLayout) getView(R.id.xietong_tablayout);
        mMoreMenuBtn = (ImageView) getView(R.id.search_btn);
        mMoreMenuBtn.setOnClickListener(this);
        mViewpager = (CustomViewPager) getView(R.id.viewpager);
        initTab();
        initTabData();
    }

    private void initTab() {
        adapter = new XieTongPagerAdapter(getChildFragmentManager(), mContext,
                getLabels());
        mViewpager.setAdapter(adapter);
        mViewpager.setScanScroll(false);
        /*viewpager切换监听，包含滑动点击两种*/
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mXietongTablayout.setupWithViewPager(mViewpager);

    }

    private List<String> getLabels() {
        List<String> arrays = new ArrayList<>();
        arrays.add("待办事项");
        arrays.add("已办事项");
        return arrays;
    }

    private void initTabData() {
        adapter.setTitles(getLabels());
        mViewpager.setOffscreenPageLimit(getLabels().size());
        /**
         * 添加自定义tab布局
         * */
        for (int i = 0; i < mXietongTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mXietongTablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(adapter.getTabView(i));
            }
        }
        /*viewpager切换默认第一个*/
        mViewpager.setCurrentItem(0);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_btn:
                break;
        }
    }
}
