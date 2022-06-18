package a3phone.of.com.main.control;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import a3phone.of.com.main.R;
import a3phone.of.com.main.bean.ControlMenuBean;
import a3phone.of.com.main.utils.UserInfoManager;
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
        helper.setText(R.id.tabitem_text,item.getNAME());
        helper.setGone(R.id.edit_item_iv,false);
        if ("-1".equals(item.getNAME())) {
            helper.setImageResource(R.id.tabitem_image,R.mipmap.more_app_icon);
        }else {
            ImageLoadUtil.loadImage(mContext, UserInfoManager.getAppImageAbPath(item.getIMG()),helper.getView(R.id.tabitem_image));

        }
    }
}
