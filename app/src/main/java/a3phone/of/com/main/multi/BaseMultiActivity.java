package a3phone.of.com.main.multi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import a3phone.of.com.main.bean.multiBean.BaseNormalRecyclerviewBean;
import a3phone.of.com.main.bean.multiBean.ItemFragmentBean;
import a3phone.of.com.main.bean.multiBean.MultipleItem;
import a3phone.of.com.main.bean.multiBean.TextKeyValueBean;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.utils.CalendarUtil;
import a3phone.of.disabled.basecomponent.utils.DisplayUtil;
import a3phone.of.disabled.basecomponent.utils.PickerManager;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;
import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public abstract class BaseMultiActivity extends BaseRecyclerviewActivity<MultiPresent> implements BaseIView, SelectPhotosFragment.OnPhotoItemClick, MultiContact.BaseNormalRvItemClick, View.OnClickListener {
    public TextView mCommitTv;


    @Override
    public void initView() {
        super.initView();
        SmartRefreshLayout.LayoutParams params = (SmartRefreshLayout.LayoutParams) mRecyclerview.getLayoutParams();
        params.setMargins(DisplayUtil.dp2px(mContext, 15), DisplayUtil.dp2px(mContext, 10), DisplayUtil.dp2px(mContext, 15), DisplayUtil.dp2px(mContext, 10));
        mRecyclerview.setLayoutParams(params);

        initAdapterClick();

    }

    @Override
    protected View getAdapterFootView() {
        View view = LayoutInflater.from(mContext.getApplicationContext()).inflate(R.layout.footview_commit, null);
        mCommitTv = view.findViewById(R.id.commit_form_tv);
        mCommitTv.setText("提交");
        mCommitTv.setOnClickListener(this);
        return view;
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
    protected BaseAdapterDataBean getBaseAdapterData(List<MultipleItem> arrays) {
        BaseAdapterDataBean bean = new BaseAdapterDataBean();
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
                case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                    BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) array.getObject();

                    switch (baseNormalRecyclerviewBean.getType()) {
                        case MultiContact.BASE_RECYCLERVIEW_TYPE_MULTI:
                            List<MultipleItem> multipleItems = (List<MultipleItem>) baseNormalRecyclerviewBean.getObject();
                            BaseAdapterDataBean baseAdapterDataBean = getBaseAdapterData(multipleItems);

                            break;
                        default:
                            break;
                    }
                    break;


                case MultipleItem.ITEM_SELECT:
                    TextKeyValueBean selectBean = (TextKeyValueBean) array.getObject();
                    switch (selectBean.getKey()) {
                        case MultiContact.PURCHASE_TYPE:
                            ToastUtils.toast(mContext, "采购类型");
                            break;
                        case MultiContact.PURCHASE_DELIVER_TIME:
                            ToastUtils.toast(mContext, "时间");

                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_EDIT_VER:
                    TextKeyValueBean editVerBean = (TextKeyValueBean) array.getObject();
                    switch (editVerBean.getKey()) {
                        case MultiContact.PURCHASE_REASON:
                            break;
                        case MultiContact.REMARK:
                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_FRAGMENT:
                    ItemFragmentBean fragmentBean = (ItemFragmentBean) array.getObject();
                    switch (fragmentBean.getTitle()) {
                        case MultiContact.REMARK_PICS:
                            break;
                        default:
                            break;
                    }
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit_form_tv:
                // TODO: 2022/7/22 提交
                getBaseAdapterData(baseQuickAdapter.getData());
                break;
            default:
                break;
        }
    }
}
