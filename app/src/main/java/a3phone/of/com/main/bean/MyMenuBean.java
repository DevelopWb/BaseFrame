package a3phone.of.com.main.bean;

import java.io.Serializable;

/**
 * Describe:个人中心菜单数据
 * Create by tobato
 * 2020/3/7
 * email:954101549@qq.com
 */
public class MyMenuBean implements Serializable {

    public static final  String MENU_MODIFY_PWD = "修改密码";
    public static final  String MENU_MODIFY_SIGN = "电子签名设置";
    public static final  String MENU_MODIFY_SUGGESTION = "意见反馈";
    public static final  String MENU_MODIFY_CLEAR = "清空缓存";
    public static final  String MENU_QUIT_CURRENT_ACCOUNT = "退出登录";
    public static final  String MENU_MODIFY_ABOUT_US = "关于我们";

    private String name;
    private int number;
    private int imageId;
    //分割线
    private boolean hasEndLine;

    public MyMenuBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public MyMenuBean(String name, int number, int imageId, boolean hasEndLine) {
        this.name = name;
        this.number = number;
        this.imageId = imageId;
        this.hasEndLine = hasEndLine;
    }


    public MyMenuBean(String name, int imageId, boolean hasEndLine) {
        this.name = name;
        this.imageId = imageId;
        this.hasEndLine = hasEndLine;
    }

    public boolean isHasEndLine() {
        return hasEndLine;
    }

    public void setHasEndLine(boolean hasEndLine) {
        this.hasEndLine = hasEndLine;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
