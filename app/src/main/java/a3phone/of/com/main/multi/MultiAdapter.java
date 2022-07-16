package a3phone.of.com.main.multi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a3phone.of.com.main.R;
import a3phone.of.com.main.bean.multiBean.BaseNormalRecyclerviewBean;
import a3phone.of.com.main.bean.multiBean.MultipleItem;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class MultiAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_EDIT_HOR, R.layout.item_layout_type_edit_hor);
        addItemType(MultipleItem.ITEM_EDIT_VER, R.layout.item_layout_type_edit_ver);
        addItemType(MultipleItem.ITEM_SELECT, R.layout.item_layout_type_select);
        addItemType(MultipleItem.ITEM_TIME_START_END, R.layout.item_layout_type_time_start_end);
        addItemType(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, R.layout.item_layout_type_recyclerview);


    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.ITEM_NORMAL_RECYCLEVIEW:
                //recycleview

                BaseNormalRecyclerviewBean baseNormalRecyclerviewBean = (BaseNormalRecyclerviewBean) item.getObject();
                RecyclerView recyclerView = helper.getView(R.id.item_normal_rv);
                LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL
                        , false);
                BaseQuickAdapter adapter = baseNormalRecyclerviewBean.getAdapter();
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
                switch (baseNormalRecyclerviewBean.getType()) {
//                    case BaseInspectContract.BASE_RECYCLERVIEW_TYPE_TEXT_VALUE:
//                        List<TextKeyValueBean> arrays = (List<TextKeyValueBean>) baseNormalRecyclerviewBean.getObject();
//                        adapter.setNewData(arrays);
//                        break;
//                    case BaseInspectContract.BASE_RECYCLERVIEW_TYPE_RESPONSIBILITY_CONTENT:
//                        List<String> pics = (List<String>) baseNormalRecyclerviewBean.getObject();
//                        adapter.setNewData(pics);
//                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
