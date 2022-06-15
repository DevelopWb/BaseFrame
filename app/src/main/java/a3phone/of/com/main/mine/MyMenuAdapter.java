package a3phone.of.com.main.mine;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import a3phone.of.com.main.R;
import a3phone.of.com.main.bean.MultipleItem;
import a3phone.of.com.main.bean.MyMenuBean;

import java.util.List;

/**
 * Describe:
 * Create by tobato
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyMenuAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyMenuAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.ITEM_DIVIDER, R.layout.memu_divider);
        addItemType(MultipleItem.ITEM_MYCENTER_MENUS,R.layout.my_center_menu_item);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem multipleItem) {
        switch (multipleItem.getItemType()) {
            case MultipleItem.ITEM_DIVIDER:
                break;
            case MultipleItem.ITEM_MYCENTER_MENUS:
                MyMenuBean item = (MyMenuBean) multipleItem.getObject();
                helper.setText(R.id.item_name, item.getName());

                if (item.getImageId()>0) {
                    helper.setGone(R.id.item_iv,true);
                    helper.setImageResource(R.id.item_iv, item.getImageId());
                }else {
                    helper.setGone(R.id.item_iv,false);
                }

                if (item.getNumber() > 0) {
                    helper.setVisible(R.id.item_number, true);
                    helper.setText(R.id.item_number, item.getNumber() > 99 ? "99+" : String.valueOf(item.getNumber()));
                } else {
                    helper.setVisible(R.id.item_number, false);
                }
                if (item.isHasEndLine()) {
                    helper.setGone(R.id.menu_item_divider_v, true);
                } else {
                    helper.setGone(R.id.menu_item_divider_v, false);
                }
                break;
            default:
                break;
        }


    }

}
