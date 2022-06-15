package com.of.com.main.mine;


import com.of.disabled.basecomponent.mvp.BasePresenter;
import com.of.disabled.basecomponent.mvp.IModel;
import com.of.com.main.R;
import com.of.com.main.bean.MultipleItem;
import com.of.com.main.bean.MyMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * 2020/3/7
 */
public class MyCenterPresent extends BasePresenter<IModel, MyCenterContract.ICenterView> implements MyCenterContract.ICenterPresent {
    @Override
    protected IModel createModel() {
        return null;
    }




    public List<MultipleItem> getMenuBeans() {
        List<MultipleItem> menuBeans = new ArrayList<>();
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_PWD, 0, R.mipmap.ic_launcher,  true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_SIGN, 0, R.mipmap.ic_launcher, true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_SUGGESTION, 0, R.mipmap.ic_launcher,  true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_CLEAR, 0, R.mipmap.ic_launcher,  true)));

        return menuBeans;
    }
    public List<MultipleItem> getMenuBeans2() {
        List<MultipleItem> menuBeans = new ArrayList<>();
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_ADVISER, 0, R.mipmap.ic_launcher,  true)));
        menuBeans.add(new MultipleItem(MultipleItem.ITEM_MYCENTER_MENUS, new MyMenuBean(MyMenuBean.MENU_MODIFY_CLEAR, 0, R.mipmap.ic_launcher, true)));

        return menuBeans;
    }

}
