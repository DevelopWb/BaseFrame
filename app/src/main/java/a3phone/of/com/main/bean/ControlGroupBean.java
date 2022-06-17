package a3phone.of.com.main.bean;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * @Author: tobato
 * @Description: &#x4f5c;&#x7528;&#x63cf;&#x8ff0;
 * @CreateDate: 2022/6/17 20:35
 * @UpdateUser: &#x66f4;&#x65b0;&#x8005;
 * @UpdateDate: 2022/6/17 20:35
 */
public class ControlGroupBean implements IPickerViewData {

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
        return RowState == null ? "" : RowState;
    }

    public void setRowState(String rowState) {
        RowState = rowState == null ? "" : rowState;
    }

    public String getGUID() {
        return GUID == null ? "" : GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID == null ? "" : GUID;
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

    @Override
    public String getPickerViewText() {
        return NAME;
    }
}
