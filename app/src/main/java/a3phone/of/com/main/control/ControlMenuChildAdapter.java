package a3phone.of.com.main.control;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import a3phone.of.com.main.R;
import a3phone.of.com.main.bean.ControlMenuBean;
import a3phone.of.com.main.bean.ControlMenuEditListBean;
import a3phone.of.disabled.basecomponent.utils.ImageLoadUtil;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class ControlMenuChildAdapter extends BaseQuickAdapter<ControlMenuBean,BaseViewHolder> {
    public ControlMenuChildAdapter(int layoutResId ) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ControlMenuBean item) {
        ImageLoadUtil.loadImage(mContext,item.getIMG(),helper.getView(R.id.tabitem_image));
        helper.setText(R.id.tabitem_text,item.getNAME());
        helper.setGone(R.id.edit_item_iv,false);
    }
}
