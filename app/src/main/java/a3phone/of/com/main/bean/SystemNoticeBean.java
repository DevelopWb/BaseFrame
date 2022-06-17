package a3phone.of.com.main.bean;

/**
 * @Author: tobato
 * @Description: 作用描述 系统公告
 * @CreateDate: 2022/6/17 21:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/17 21:10
 */
public class SystemNoticeBean {


    /**
     * RowState : Unchanged
     * GUID : DBFF8FAE-4C36-4BA6-BE87-3E7217868156
     * NOTICETYPE : 通知
     * TITLE : 紧急通知：下午到财务领福利
     */

    private String RowState;
    private String GUID;
    private String NOTICETYPE;
    private String TITLE;

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

    public String getNOTICETYPE() {
        return NOTICETYPE;
    }

    public void setNOTICETYPE(String NOTICETYPE) {
        this.NOTICETYPE = NOTICETYPE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
}
