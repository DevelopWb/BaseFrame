package com.juntai.wisdom.project.mine;


import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.bean.MultipleItem;
import com.juntai.wisdom.project.bean.MyMenuBean;
import com.juntai.wisdom.project.home_page.HomePageContract;

import java.util.ArrayList;
import java.util.List;

public class MyCenterPresent extends BasePresenter<IModel, BaseIView> implements MyCenterContract.ICenterPresent {
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
