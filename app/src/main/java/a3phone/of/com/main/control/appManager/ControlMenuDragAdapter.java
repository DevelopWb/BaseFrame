package a3phone.of.com.main.control.appManager;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import a3phone.of.com.main.R;
import a3phone.of.com.main.bean.ControlMenuBean;
import a3phone.of.com.main.bean.ControlMenuEditListBean;
import a3phone.of.com.main.utils.UserInfoManager;
import a3phone.of.disabled.basecomponent.utils.ImageLoadUtil;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class ControlMenuDragAdapter extends BaseItemDraggableAdapter<ControlMenuBean,BaseViewHolder> {

    public ControlMenuDragAdapter(int layoutResId, List<ControlMenuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ControlMenuBean item) {
        item.setDETAILNO(String.valueOf(helper.getAdapterPosition()));
        ImageLoadUtil.loadImage(mContext, UserInfoManager.getAppImageAbPath(item.getIMG()),helper.getView(R.id.tabitem_image));
        helper.setText(R.id.tabitem_text,item.getNAME());
        helper.setImageResource(R.id.edit_item_iv,R.mipmap.delete_red_bg);
    }
}
