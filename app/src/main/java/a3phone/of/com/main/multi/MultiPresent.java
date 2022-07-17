package a3phone.of.com.main.multi;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseAppPresent;
import a3phone.of.com.main.bean.ImportantTagBean;
import a3phone.of.com.main.bean.multiBean.BaseNormalRecyclerviewBean;
import a3phone.of.com.main.bean.multiBean.ItemFragmentBean;
import a3phone.of.com.main.bean.multiBean.MultipleItem;
import a3phone.of.com.main.bean.multiBean.TextKeyValueBean;
import a3phone.of.com.main.utils.UserInfoManager;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.mvp.IModel;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class MultiPresent extends BaseAppPresent<IModel, BaseIView> {
    @Override
    protected IModel createModel() {
        return null;
    }


    public List<MultipleItem> getPurchaseRequestData(FragmentManager mFragmentManager,boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_NORMAL_RECYCLEVIEW, new BaseNormalRecyclerviewBean(
                MultiContact.BASE_RECYCLERVIEW_TYPE_MULTI,
                getPurchaseBaseInfo(isDetail), new MultiAdapter(null,false,mFragmentManager))));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER,""));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MultiContact.PAY_TYPE, "", 0, true,isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER,""));

        List<String> fragmentPics = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_FRAGMENT, new ItemFragmentBean(MultiContact.REMARK_PICS,5, 9, 1,
                fragmentPics)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_DIVIDER,""));
        arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT_VER,
                new TextKeyValueBean(MultiContact.REMARK, "", 0, false,isDetail)));
        return arrays;
    }

    private List<MultipleItem> getPurchaseBaseInfo(boolean isDetail) {
        List<MultipleItem> arrays = new ArrayList<>();
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MultiContact.PURCHASE_TYPE, "", 0, true,isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_SELECT,
                new TextKeyValueBean(MultiContact.PURCHASE_DELIVER_TIME, "", 0, true,isDetail)));
        arrays.add(new MultipleItem(MultipleItem.ITEM_EDIT_VER,
                new TextKeyValueBean(MultiContact.PURCHASE_REASON, "", 0, true,isDetail)));
        return arrays;
    }


}
