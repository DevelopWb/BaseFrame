package a3phone.of.com.main.xietong.sortLable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a3phone.of.com.main.R;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class SortLableAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public SortLableAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.label_name_tv, item);
        helper.addOnClickListener(R.id.sort_top_iv);
    }
}
