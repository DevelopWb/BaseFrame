package a3phone.of.com.main.home_page;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.com.main.base.BaseRecyclerviewFragment;

/**
 * @aouther tobato
 * @description 描述  homepage
 * @date 2021/4/18 14:59
 */
public class EmailFragment extends BaseRecyclerviewFragment<HomePagePresent> implements BaseIView,
        View.OnClickListener {




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {

    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected BaseQuickAdapter getBaseQuickAdapter() {
        return null;
    }

    @Override
    protected HomePagePresent createPresenter() {
        return new HomePagePresent();
    }


    @Override
    public void onSuccess(String tag, Object o) {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
