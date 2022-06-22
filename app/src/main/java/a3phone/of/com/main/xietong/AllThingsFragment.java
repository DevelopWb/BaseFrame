package a3phone.of.com.main.xietong;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseRecyclerviewFragment;
import a3phone.of.com.main.base.customview.a3levelcombo.A3LevelComboRv;
import a3phone.of.com.main.base.customview.a3levelcombo.OnItemClickCallBack;
import a3phone.of.com.main.xietong.sortLable.SortLableActivity;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/17 21:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/17 21:53
 */
public class AllThingsFragment extends BaseRecyclerviewFragment<XieTongPresent> implements BaseIView, View.OnClickListener {
    private A3LevelComboRv mAllThingsItemLcRv;
    private ImageView mEditItemIv;

    @Override
    protected View getAdapterHeadView() {
        return null;
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.xietong_things_fg;
    }

    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        baseQuickAdapter.setNewData(getBaseAppActivity().getTestData());
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
        return new AllThingsAdapter(R.layout.all_tings_item);
    }

    @Override
    protected XieTongPresent createPresenter() {
        return null;
    }


    public void initView() {
        super.initView();
        mAllThingsItemLcRv = (A3LevelComboRv) getView(R.id.all_things_item_lcrv);
        mAllThingsItemLcRv.setData(getBaseAppActivity().getTestData());
        mAllThingsItemLcRv.setOnItemClickCallBack(new OnItemClickCallBack() {
            @Override
            public void onItemClicked(BaseQuickAdapter adapter, int position) {
                        ToastUtils.toast(mContext,"dfad");
            }
        });
        mEditItemIv = (ImageView) getView(R.id.edit_item_iv);
        mEditItemIv.setOnClickListener(this);
        mRecyclerview.setBackgroundResource(R.drawable.sp_filled_gray_lighter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.edit_item_iv:
                startActivity(new Intent(mContext, SortLableActivity.class));
                break;
        }
    }
}
