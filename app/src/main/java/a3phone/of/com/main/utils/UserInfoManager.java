package a3phone.of.com.main.utils;

import a3phone.of.com.main.bean.UserBean;

import com.orhanobut.hawk.Hawk;

/**
 * @Author: tobato
 * @Description: 作用描述  用户信息管理类
 * @CreateDate: 2020/12/19 14:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/12/19 14:04
 */
public class UserInfoManager {
    /**
     * 退出登录清理缓存配置
     */
    public static void clearUserData() {
        Hawk.delete(HawkProperty.USER_BEAN_KEY);
        Hawk.delete(HawkProperty.USER_ACCOUNT_CODE);
        Hawk.delete(HawkProperty.USER_PWD);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUser() {
        return Hawk.get(HawkProperty.USER_BEAN_KEY);
    }

    /**
     * 判定用户是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        if (getUser() == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 账属
     * @return
     */
    public static String getAccountCode() {
        if (!Hawk.contains(HawkProperty.USER_ACCOUNT_CODE)) {
            return "";
        }else {
       return Hawk.get(HawkProperty.USER_ACCOUNT_CODE);
        }
    }
    /**
     * 会话id
     * @return
     */
    public static String getSessionId() {
       return getUser()==null?"":getUser().getSessionID();
    }
    /**
     * getUserName
     * @return
     */
    public static String getUserName() {
       return getUser()==null?"":getUser().getUserName();
    }
    /**
     * getUserName
     * @return
     */
    public static String getOrgName() {
       return getUser()==null?"":getUser().getOrgName();
    }
    /**
     * getUserName
     * @return
     */
    public static String getUserHeadImage() {
       return getUser()==null?"":getUser().getHEADPHOTO();
    }
    /**
     * getUserName
     * @return
     */
    public static String getUserPwd() {
        if (!Hawk.contains(HawkProperty.USER_PWD)) {
            return "";
        }else {
            return Hawk.get(HawkProperty.USER_PWD);
        }
    }

    public static String getServerAddr(){
        if (!Hawk.contains(HawkProperty.CURRENT_SERVICE_ADDRS)) {
            return "http://59.110.154.247:8088";
        }else {
            return Hawk.get(HawkProperty.CURRENT_SERVICE_ADDRS);
        }
    }

    /**
     * 获取图片全路径
     * @param picName
     * @return
     */
    public  static String  getImageAbPath(String picName){
        return String.format("%s/UpLoadFiles/BillAttachFiles/%s",getServerAddr(),picName);
    }
    /**
     * 获取图片全路径
     * @param picName
     * @return
     */
    public  static String  getAppImageAbPath(String picName){
        return String.format("%s/AppImage/%s",getServerAddr(),picName);
    }

}
