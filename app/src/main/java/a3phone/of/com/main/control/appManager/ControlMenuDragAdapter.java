package a3phone.of.com.main.control.appManager;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a3phone.of.com.main.R;
import a3phone.of.com.main.bean.PicTextBean;
import a3phone.of.disabled.basecomponent.utils.ImageLoadUtil;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class ControlMenuDragAdapter extends BaseItemDraggableAdapter<PicTextBean,BaseViewHolder> {

    public ControlMenuDragAdapter(int layoutResId, List<PicTextBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PicTextBean item) {
        ImageLoadUtil.loadImage(mContext,item.getPicRes(),helper.getView(R.id.tabitem_image));
        helper.setText(R.id.tabitem_text,item.getTextName());
        helper.setGone(R.id.tabitem_count,item.getUnReadAmount()>0);
        helper.setText(R.id.tabitem_count,String.valueOf(item.getUnReadAmount()));
    }
}
