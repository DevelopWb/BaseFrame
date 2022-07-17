package a3phone.of.com.main.multi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Date;
import java.util.List;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseRecyclerviewActivity;
import a3phone.of.com.main.base.selectPics.SelectPhotosFragment;
import a3phone.of.com.main.bean.multiBean.BaseAdapterDataBean;
import a3phone.of.com.main.bean.multiBean.MultipleItem;
import a3phone.of.com.main.bean.multiBean.TextKeyValueBean;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.utils.CalendarUtil;
import a3phone.of.disabled.basecomponent.utils.DisplayUtil;
import a3phone.of.disabled.basecomponent.utils.PickerManager;
import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public abstract class BaseMultiActivity extends BaseRecyclerviewActivity<MultiPresent> implements BaseIView, SelectPhotosFragment.OnPhotoItemClick, MultiContact.BaseNormalRvItemClick {
    @Override
    public void initView() {
        super.initView();
        SmartRefreshLayout.LayoutParams params = (SmartRefreshLayout.LayoutParams) mRecyclerview.getLayoutParams();
        params.setMargins(DisplayUtil.dp2px(mContext, 15), DisplayUtil.dp2px(mContext, 10), DisplayUtil.dp2px(mContext, 15), DisplayUtil.dp2px(mContext, 10));
        mRecyclerview.setLayoutParams(params);

        initAdapterClick();

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, RecyclerView recyclerView, int position) {
        MultipleItem multipleItem = (MultipleItem) adapter.getItem(position);
        switch (multipleItem.getItemType()) {
            case MultipleItem.ITEM_SELECT:
                TextKeyValueBean selectBean = (TextKeyValueBean) multipleItem.getObject();
                TextView selectTv = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.select_value_tv);
                String selectKey = selectBean.getKey();
                switch (selectKey) {
                    case MultiContact.PURCHASE_TYPE:
                        // TODO: 2022/7/17 采购类型
                        break;
                    case MultiContact.PURCHASE_DELIVER_TIME:
                        // : 2022/7/17 交付时间
                        PickerManager.getInstance().showTimePickerView(mContext, new boolean[]{true, true, true, false, false, false}, MultiContact.PURCHASE_DELIVER_TIME, new PickerManager.OnTimePickerTimeSelectedListener() {
                            @Override
                            public void onTimeSelect(Date date, View v) {
                                selectTv.setText(CalendarUtil.getTimeFromDate("yyyy-MM-dd",
                                        date));
                            }
                        });
                        break;
                    case MultiContact.PAY_TYPE:
                        // TODO: 2022/7/17 支付方式
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void initAdapterClick() {
        ((MultiAdapter) baseQuickAdapter).setBaseNormalRvItemClick(this);
        baseQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }

    /**
     * 获取适配器数据
     *
     * @return
     */
    protected BaseAdapterDataBean getBaseAdapterData() {
        BaseAdapterDataBean bean = new BaseAdapterDataBean();
        List<MultipleItem> arrays = baseQuickAdapter.getData();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
                case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                    break;
                default:
                    break;
            }
        }
        return bean;

    }

    @Override
    protected View getAdapterHeadView() {
        return null;
    }


    @Override
    protected LinearLayoutManager getBaseAdapterManager() {
        return null;
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
        return new MultiAdapter(null, false, getSupportFragmentManager());
    }

    @Override
    protected MultiPresent createPresenter() {
        return new MultiPresent();
    }

    @Override
    public void onVedioClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }
}
