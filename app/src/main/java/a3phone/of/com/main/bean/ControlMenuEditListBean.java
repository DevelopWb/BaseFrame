package a3phone.of.com.main.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.ArrayList;
import java.util.List;

import a3phone.of.disabled.basecomponent.base.BaseResult;

/**
 * @Author: tobato
 * @Description: 作用描述  控制台首页App菜单
 * @CreateDate: 2022/6/16 19:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/16 19:50
 */
public class ControlMenuEditListBean extends BaseResult {

    /**
     * GROUPLIST : {"Rows":[{"RowState":"Unchanged","GUID":"0dddd497-ed4c-40d5-a451-87db6f78b390","CODE":"001","NAME":"常用"},{"RowState":"Unchanged","GUID":"881a9dba-bc1a-4ef0-9a21-b8e558519f5e","CODE":"003","NAME":"其他"},{"RowState":"Unchanged","GUID":"f67e2796-d53d-4bc5-819d-a08b1c321d3c","CODE":"002","NAME":"业务"}]}
     * APPLIST : {"Rows":[{"RowState":"Unchanged","APPGUID":"7c20bf38-a4ef-4b59-b1e3-717c270bbe0a","CODE":"OF001","NAME":"销售订单","IMG":"dingdan.png","GROUPGUID":"0dddd497-ed4c-40d5-a451-87db6f78b390","DETAILNO":"1","HANDLERTYPE":"UNCHANGE"},{"RowState":"Unchanged","APPGUID":"f3808743-3e5a-4ed4-b21a-feb3dc332e43","CODE":"OF016","NAME":"用车申请","IMG":"","GROUPGUID":"881a9dba-bc1a-4ef0-9a21-b8e558519f5e","DETAILNO":"16","HANDLERTYPE":"UNCHANGE"}]}
     * GROUPAPPLIST : {"Rows":[{"RowState":"Unchanged","APPGUID":"7c20bf38-a4ef-4b59-b1e3-717c270bbe0a","CODE":"OF001","NAME":"销售订单","IMG":"dingdan.png","GROUPGUID":"0dddd497-ed4c-40d5-a451-87db6f78b390","DETAILNO":"1","HANDLERTYPE":"UNCHANGE"},{"RowState":"Unchanged","APPGUID":"f3808743-3e5a-4ed4-b21a-feb3dc332e43","CODE":"OF016","NAME":"用车申请","IMG":"","GROUPGUID":"881a9dba-bc1a-4ef0-9a21-b8e558519f5e","DETAILNO":"16","HANDLERTYPE":"UNCHANGE"}]}
     */

    private GROUPLISTBean GROUPLIST;
    /**
     * 用户没有的APP
     */
    private APPLISTBean APPLIST;
    /**
     * 用户拥有的所有的APP
     */
    private GROUPAPPLISTBean GROUPAPPLIST;

    public GROUPLISTBean getGROUPLIST() {
        return GROUPLIST;
    }

    public void setGROUPLIST(GROUPLISTBean GROUPLIST) {
        this.GROUPLIST = GROUPLIST;
    }

    public APPLISTBean getAPPLIST() {
        return APPLIST;
    }

    public void setAPPLIST(APPLISTBean APPLIST) {
        this.APPLIST = APPLIST;
    }

    public GROUPAPPLISTBean getGROUPAPPLIST() {
        return GROUPAPPLIST;
    }

    public void setGROUPAPPLIST(GROUPAPPLISTBean GROUPAPPLIST) {
        this.GROUPAPPLIST = GROUPAPPLIST;
    }

    public static class GROUPLISTBean {
        private List<RowsBean> Rows;

        public List<RowsBean> getRows() {
            return Rows;
        }

        public void setRows(List<RowsBean> Rows) {
            this.Rows = Rows;
        }

        public static class RowsBean implements IPickerViewData {
            /**
             * RowState : Unchanged
             * GUID : 0dddd497-ed4c-40d5-a451-87db6f78b390
             * CODE : 001
             * NAME : 常用
             */

            private String RowState;
            private String GUID;
            private String CODE;
            private String NAME;

            public String getRowState() {
                return RowState;
            }

            public void setRowState(String RowState) {
                this.RowState = RowState;
            }

            public String getGUID() {
                return GUID;
            }

            public void setGUID(String GUID) {
                this.GUID = GUID;
            }

            public String getCODE() {
                return CODE;
            }

            public void setCODE(String CODE) {
                this.CODE = CODE;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            @Override
            public String getPickerViewText() {
                return NAME;
            }
        }
    }

    public static class APPLISTBean {
        private List<ControlMenuBean> Rows;

        public List<ControlMenuBean> getRows() {
            if (Rows == null) {
                return new ArrayList<>();
            }
            return Rows;
        }

        public void setRows(List<ControlMenuBean> rows) {
            Rows = rows;
        }
    }

    public static class GROUPAPPLISTBean {
        private List<ControlMenuBean> Rows;

        public List<ControlMenuBean> getRows() {
            if (Rows == null) {
                return new ArrayList<>();
            }
            return Rows;
        }

        public void setRows(List<ControlMenuBean> rows) {
            Rows = rows;
        }
    }
}
