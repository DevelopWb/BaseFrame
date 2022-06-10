package com.juntai.wisdom.project.mine;


import com.juntai.disabled.basecomponent.mvp.BasePresenter;
import com.juntai.disabled.basecomponent.mvp.IModel;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.bean.MultipleItem;
import com.juntai.wisdom.project.bean.MyMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * Create by zhangzhenlong
 * 2020/3/7
 * email:954101549@qq.com
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
