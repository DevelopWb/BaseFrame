package a3phone.of.com.main.mine;


import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseAppPresent;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.mvp.BasePresenter;
import a3phone.of.disabled.basecomponent.mvp.IModel;
import a3phone.of.com.main.bean.MultipleItem;
import a3phone.of.com.main.bean.MyMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * 2020/3/7
 */
public class MyCenterPresent extends BaseAppPresent<IModel, BaseIView>  {


    @Override
    protected IModel createModel() {
        return null;
    }




    public List<MultipleItem> getMenuBeans() {
        List<MultipleItem> menuBeans = new ArrayList<>();
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_PWD, 0, R.mipmap.my_center_pwd,  true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_SIGN, 0, R.mipmap.my_center_sign, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_SUGGESTION, 0, R.mipmap.my_center_suggestion,  true)));
//        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_CLEAR, 0, R.mipmap.my_center_clear,  true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_ABOUT_US, 0, R.mipmap.my_center_about, true)));

        return menuBeans;
    }
    public List<MultipleItem> getMenuBeans2() {
        List<MultipleItem> menuBeans = new ArrayList<>();
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_QUIT_CURRENT_ACCOUNT, 0, R.mipmap.my_center_guide,  true)));

        return menuBeans;
    }

}
