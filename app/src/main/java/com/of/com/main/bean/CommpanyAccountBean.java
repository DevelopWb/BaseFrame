package com.of.com.main.bean;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/14 23:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/14 23:04
 */
public class CommpanyAccountBean implements IPickerViewData {


    public CommpanyAccountBean(String code, String name) {
        Code = code;
        Name = name;
    }

    /**
     * OrgCode : 1001
     * Code : 001
     * Name : 标准账套
     * DBType : SqlServer
     */

    private String OrgCode;
    private String Code;
    private String Name;
    private String DBType;

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String OrgCode) {
        this.OrgCode = OrgCode;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDBType() {
        return DBType;
    }

    public void setDBType(String DBType) {
        this.DBType = DBType;
    }

    @Override
    public String getPickerViewText() {
        return Name;
    }
}
