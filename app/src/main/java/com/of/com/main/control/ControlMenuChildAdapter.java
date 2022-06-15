package com.of.com.main.control;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.of.disabled.basecomponent.utils.ImageLoadUtil;
import com.of.com.main.R;
import com.of.com.main.bean.PicTextBean;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class ControlMenuChildAdapter extends BaseQuickAdapter<PicTextBean,BaseViewHolder> {
    public ControlMenuChildAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PicTextBean item) {
        ImageLoadUtil.loadImage(mContext,item.getPicRes(),helper.getView(R.id.tabitem_image));
        helper.setText(R.id.tabitem_text,item.getTextName());
        helper.setGone(R.id.tabitem_count,item.getUnReadAmount()>0);
        helper.setText(R.id.tabitem_count,String.valueOf(item.getUnReadAmount()));
    }
}
