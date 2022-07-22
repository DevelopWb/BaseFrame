package a3phone.of.com.main.bean.multiBean;

import android.text.TextUtils;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2021/4/21 9:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/4/21 9:35
 */
public class TextKeyValueBean {

    private  String key;
    private  String value;
    private String hint;
    private int type;//0代表高度固定的edittext  1代表高度不固定的edittext
    private boolean isImportant;//是否必填
    private boolean valueGravityToRight;//value靠右
    //是否是详情
    private boolean isDetail;

    public TextKeyValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public TextKeyValueBean(String key, String value,  int type, boolean isImportant, boolean isDetail) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.isImportant = isImportant;
        this.isDetail = isDetail;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key == null ? "" : key;
    }

    public String getValue() {
        return TextUtils.isEmpty(value) ?isDetail?"暂无": "" : value;
    }

    public void setValue(String value) {
        this.value = value == null ? "" : value;
    }

    public String getHint() {
        return TextUtils.isEmpty(hint) ?isDetail?"暂无": "请输入" : hint;
    }

    public void setHint(String hint) {
        this.hint = hint == null ? "" : hint;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public boolean isValueGravityToRight() {
        return valueGravityToRight;
    }

    public void setValueGravityToRight(boolean valueGravityToRight) {
        this.valueGravityToRight = valueGravityToRight;
    }
}