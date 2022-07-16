package a3phone.of.com.main.bean.multiBean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/4/15 10:08
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/15 10:08
 */
public class MultipleItem implements MultiItemEntity {
    public static final int ITEM_EDIT_HOR = 1;//  横向排列
    public static final int ITEM_EDIT_VER = 2;//  纵向排列
    public static final int ITEM_SELECT = 3;//  选择
    public static final int ITEM_TIME_START_END = 4;//  时间 开始结束
    public static final int ITEM_NORMAL_RECYCLEVIEW = 5;//

    public static final int ITEM_RADIO = 9;//类型3
    public static final int ITEM_PIC = 10;//类型3
    public static final int ITEM_SIGN = 11;//签字
    public static final int ITEM_NOTICE = 12;//提示
    public static final int ITEM_YEAR = 13;//年度
    public static final int ITEM_EDIT2 = 15;//  key value 类型
    public static final int ITEM_FRAGMENT = 16;//  fragment
    public static final int ITEM_LOCATION = 17;//  定位

    public static final int ITEM_DIVIDER = 21;//  divider
    public static final int ITEM_MYCENTER_MENUS = 22;//  菜单

    private int itemType;
    private Object object;

    public MultipleItem(int itemType, Object object) {
        this.itemType = itemType;
        this.object = object;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
