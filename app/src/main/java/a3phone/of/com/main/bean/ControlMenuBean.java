package a3phone.of.com.main.bean;

/**
 * @Author: tobato
 * @Description: 作用描述  控制台菜单
 * @CreateDate: 2022/6/17 6:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/17 6:21
 */
public class ControlMenuBean {

    public static String ADD = "ADD";
    public static String DELETE = "DELETE";
    public static String EDIT = "EDIT";
    public static String UNCHANGE = "UNCHANGE";


    /**
     * RowState : Unchanged
     * APPGUID : 7c20bf38-a4ef-4b59-b1e3-717c270bbe0a
     * CODE : OF001
     * NAME : 销售订单
     * IMG : dingdan.png
     * GROUPGUID : 0dddd497-ed4c-40d5-a451-87db6f78b390
     * DETAILNO : 1
     * HANDLERTYPE : UNCHANGE
     */

    private String RowState;
    private String APPGUID;
    private String CODE;
    private String NAME;
    private String IMG;
    private String GROUPGUID;
    private String DETAILNO;
    private String HANDLERTYPE;

    public ControlMenuBean(String NAME, String IMG) {
        this.NAME = NAME;
        this.IMG = IMG;
    }

    public String getRowState() {
        return RowState == null ? "" : RowState;
    }

    public void setRowState(String rowState) {
        RowState = rowState == null ? "" : rowState;
    }

    public String getAPPGUID() {
        return APPGUID == null ? "" : APPGUID;
    }

    public void setAPPGUID(String APPGUID) {
        this.APPGUID = APPGUID == null ? "" : APPGUID;
    }

    public String getCODE() {
        return CODE == null ? "" : CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE == null ? "" : CODE;
    }

    public String getNAME() {
        return NAME == null ? "" : NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME == null ? "" : NAME;
    }

    public String getIMG() {
        return IMG == null ? "" : IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG == null ? "" : IMG;
    }

    public String getGROUPGUID() {
        return GROUPGUID == null ? "" : GROUPGUID;
    }

    public void setGROUPGUID(String GROUPGUID) {
        this.GROUPGUID = GROUPGUID == null ? "" : GROUPGUID;
    }

    public String getDETAILNO() {
        return DETAILNO == null ? "" : DETAILNO;
    }

    public void setDETAILNO(String DETAILNO) {
        this.DETAILNO = DETAILNO == null ? "" : DETAILNO;
    }

    public String getHANDLERTYPE() {
        return HANDLERTYPE == null ? "" : HANDLERTYPE;
    }

    public void setHANDLERTYPE(String HANDLERTYPE) {
        this.HANDLERTYPE = HANDLERTYPE == null ? "" : HANDLERTYPE;
    }
}
