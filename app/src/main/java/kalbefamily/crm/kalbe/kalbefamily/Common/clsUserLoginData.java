package kalbefamily.crm.kalbe.kalbefamily.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Rian Andrivani on 7/4/2017.
 */
@DatabaseTable
public class clsUserLoginData implements Serializable{
    public Integer getIdUserLogin() {
        return idUserLogin;
    }

    public void setIdUserLogin(Integer idUserLogin) {
        this.idUserLogin = idUserLogin;
    }

    public String getTxtGUI() {
        return txtGUI;
    }

    public void setTxtGUI(String txtGUI) {
        this.txtGUI = txtGUI;
    }

    public String getTxtNameApp() {
        return txtNameApp;
    }

    public void setTxtNameApp(String txtNameApp) {
        this.txtNameApp = txtNameApp;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getTxtUserID() {
        return txtUserID;
    }

    public void setTxtUserID(String txtUserID) {
        this.txtUserID = txtUserID;
    }

    public String getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName = txtUserName;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtEmpId() {
        return txtEmpId;
    }

    public void setTxtEmpId(String txtEmpId) {
        this.txtEmpId = txtEmpId;
    }

    public String getDtLastLogin() {
        return dtLastLogin;
    }

    public void setDtLastLogin(String dtLastLogin) {
        this.dtLastLogin = dtLastLogin;
    }

    public String getTxtDeviceId() {
        return txtDeviceId;
    }

    public void setTxtDeviceId(String txtDeviceId) {
        this.txtDeviceId = txtDeviceId;
    }

    public String getDtLogOut() {
        return dtLogOut;
    }

    public void setDtLogOut(String dtLogOut) {
        this.dtLogOut = dtLogOut;
    }

    @DatabaseField(id = true,columnName = "clsUserLogin")
    public Integer idUserLogin;
    @DatabaseField(columnName = "txtGUI")
    public String txtGUI;
    @DatabaseField(columnName = "txtNameApp")
    public String txtNameApp;
    @DatabaseField(columnName = "employeeId")
    public String employeeId;
    @DatabaseField(columnName = "txtUserId")
    public String txtUserID;
    @DatabaseField(columnName = "txtUserName")
    public String txtUserName;
    @DatabaseField(columnName = "txtName")
    public String txtName;
    @DatabaseField(columnName = "txtEmail")
    public String txtEmail;
    @DatabaseField(columnName = "txtEmpId")
    public String txtEmpId;
    @DatabaseField(columnName = "dtLastLogin")
    public String dtLastLogin;
    @DatabaseField(columnName = "txtDeviceId")
    public String txtDeviceId;
    @DatabaseField(columnName = "dtLogout")
    public String dtLogOut;

    public clsUserLoginData() {

    }
}
