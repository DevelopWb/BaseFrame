package a3phone.of.com.main.multi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private TextKeyValueBean selectBean;
    private TextView selectTv;


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
                selectBean = (TextKeyValueBean) multipleItem.getObject();
                selectTv = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.select_value_tv);
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
                                String time = CalendarUtil.getTimeFromDate("yyyy-MM-dd",
                                        date);
                                selectTv.setText(time);
                                selectBean.setValue(time);
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
        Map<String, Object> map = new ArrayMap<>();
        BaseAdapterDataBean bean = new BaseAdapterDataBean();
        map = resolveData(baseQuickAdapter.getData(), map);
        map.size();
        return bean;

    }

    /**
     * 解析适配器数据
     * @param arrays
     * @param map
     * @return
     */
    private Map<String, Object> resolveData(List<MultipleItem> arrays, Map<String, Object> map) {
        for (MultipleItem array : arrays) {
            switch (array.getItemType()) {
                case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                    BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) array.getObject();

                    switch (baseNormalRecyclerviewBean.getType()) {
                        case MultiContact.BASE_RECYCLERVIEW_TYPE_MULTI:
                            List<MultipleItem> multipleItems = (List<MultipleItem>) baseNormalRecyclerviewBean.getObject();
                            resolveData(multipleItems, map);

                            break;
                        default:
                            break;
                    }
                    break;


                case MultipleItem.ITEM_SELECT:
                    TextKeyValueBean selectBean = (TextKeyValueBean) array.getObject();
                    String selectValue = selectBean.getValue();
                    switch (selectBean.getKey()) {
                        case MultiContact.PURCHASE_TYPE:
                           map.put(MultiContact.PURCHASE_TYPE,selectValue);
                            break;
                        case MultiContact.PURCHASE_DELIVER_TIME:
                            map.put(MultiContact.PURCHASE_DELIVER_TIME,selectValue);
                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_EDIT_VER:
                    TextKeyValueBean editVerBean = (TextKeyValueBean) array.getObject();
                    String editVerValue = editVerBean.getValue();

                    switch (editVerBean.getKey()) {
                        case MultiContact.PURCHASE_REASON:
                            map.put(MultiContact.PURCHASE_REASON,editVerValue);
                            break;
                        case MultiContact.REMARK:
                            map.put(MultiContact.REMARK,editVerValue);
                            break;
                        default:
                            break;
                    }
                    break;
                case MultipleItem.ITEM_FRAGMENT:
                    ItemFragmentBean fragmentBean = (ItemFragmentBean) array.getObject();
                    switch (fragmentBean.getTitle()) {
                        case MultiContact.REMARK_PICS:
                            List<String>  pics = fragmentBean.getFragmentPics();
                            if (pics != null) {
                                for (String pic : pics) {
                                    map.put(MultiContact.REMARK_PICS,pic);
                                }
                            }

                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return map;
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
                getBaseAdapterData();
                break;
            default:
                break;
        }
    }
}
