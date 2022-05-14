package com.juntai.disabled.basecomponent.eventbus;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/5/6 14:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/6 14:10
 */
public class EventBusObject {

    /**
     * 更改购物车商品属性  数量或者规格
     */
    public final static  String CHANGE_SHOP_CART_PROPERTY_AMOUNT = "change_cart_property_amount";
    /**
     * 获取商品详情接口
     */
    public final static  String GET_COMMODITY_DETAIL_INFO = "GET_COMMODITY_DETAIL_INFO";
    /**
     * 购物车 全选按钮的状态
     */
    public final static  String CHANGE_SELECT_ALL_BUTTON_STATUS = "CHANGE_SELECT_ALL_BUTTON_STATUS";
    /**
     * 更改商品排列方式
     */
    public final static  String CHANGE_COMMODITY_ARRAY_TYPE = "CHANGE_COMMODITY_ARRAY_TYPE";
    /**
     * 首页切换module
     */
    public final static  String HOME_PAGE_DISPLAY_MODE = "HOME_PAGE_DISPLAY_MODE";


    /**
     * 账户被顶之后
     */
    public final static  String RE_LOAD = "reload";
    /**
     * 当获取到定位信息
     */
    public final static  String ON_LOCATION_RECEIVED = "onLocationReceived";










    private String eventKey;

    private Object eventObj;

    public EventBusObject(String eventKey, Object eventObj) {
        this.eventKey = eventKey;
        this.eventObj = eventObj;
    }

    public String getEventKey() {
        return eventKey == null ? "" : eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey == null ? "" : eventKey;
    }

    public Object getEventObj() {
        return eventObj;
    }

    public void setEventObj(Object eventObj) {
        this.eventObj = eventObj;
    }
}
