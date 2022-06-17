package a3phone.of.com.main.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述  分组 分组下的菜单
 *
 * @CreateDate: 2022/6/17 20:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/17 20:38
 */
public class ControlGroupMenuBean {

    private ControlGroupBean  controlGroupBean;
    private List<ControlMenuBean>  controlMenuBeans;

    public ControlGroupMenuBean(ControlGroupBean controlGroupBean, List<ControlMenuBean> controlMenuBeans) {
        this.controlGroupBean = controlGroupBean;
        this.controlMenuBeans = controlMenuBeans;
    }

    public ControlGroupBean getControlGroupBean() {
        return controlGroupBean;
    }

    public void setControlGroupBean(ControlGroupBean controlGroupBean) {
        this.controlGroupBean = controlGroupBean;
    }

    public List<ControlMenuBean> getControlMenuBeans() {
        if (controlMenuBeans == null) {
            return new ArrayList<>();
        }
        return controlMenuBeans;
    }

    public void setControlMenuBeans(List<ControlMenuBean> controlMenuBeans) {
        this.controlMenuBeans = controlMenuBeans;
    }
}
