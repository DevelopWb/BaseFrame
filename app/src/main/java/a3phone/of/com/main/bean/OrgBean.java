package a3phone.of.com.main.bean;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * @Author: tobato
 * @Description: 作用描述 组织机构艾
 * @CreateDate: 2022/6/19 8:45
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/19 8:45
 */
public class OrgBean implements IPickerViewData {


    /**
     * RowState : Unchanged
     * GUID : 2dba854d-ff5f-4130-ae44-713edacf69d7
     * CODE : 03
     * NAME : 组织机构三
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
