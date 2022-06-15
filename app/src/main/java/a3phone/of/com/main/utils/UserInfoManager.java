package a3phone.of.com.main.utils;

import a3phone.of.disabled.basecomponent.utils.AppUtils;
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
//        Hawk.delete(HawkProperty.SP_KEY_USER);
//        Hawk.delete(HawkProperty.SP_KEY_TOKEN);
//        Hawk.delete(HawkProperty.SP_KEY_UNREAD_COUNT);
    }
    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserBean getUser() {
        return Hawk.get(HawkProperty.SP_KEY_USER);
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



}
