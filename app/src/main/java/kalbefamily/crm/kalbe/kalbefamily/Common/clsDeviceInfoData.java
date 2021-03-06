package kalbefamily.crm.kalbe.kalbefamily.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Rian Andrivani on 7/4/2017.
 */
@DatabaseTable
public class clsDeviceInfoData implements Serializable {
    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getIntMApplication() {
        return intMApplication;
    }

    public void setIntMApplication(String intMApplication) {
        this.intMApplication = intMApplication;
    }

    public String getTxtNameApp() {
        return txtNameApp;
    }

    public void setTxtNameApp(String txtNameApp) {
        this.txtNameApp = txtNameApp;
    }

    public String getBitActive() {
        return bitActive;
    }

    public void setBitActive(String bitActive) {
        this.bitActive = bitActive;
    }

    public String getTxtVersion() {
        return txtVersion;
    }

    public void setTxtVersion(String txtVersion) {
        this.txtVersion = txtVersion;
    }

    public String getTxtType() {
        return txtType;
    }

    public void setTxtType(String txtType) {
        this.txtType = txtType;
    }

    public String getTxtDevice() {
        return txtDevice;
    }

    public void setTxtDevice(String txtDevice) {
        this.txtDevice = txtDevice;
    }

    public String getTxtModel() {
        return txtModel;
    }

    public void setTxtModel(String txtModel) {
        this.txtModel = txtModel;
    }

    public String getTxtUserId() {
        return txtUserId;
    }

    public void setTxtUserId(String txtUserId) {
        this.txtUserId = txtUserId;
    }

    public String getTxtGUI() {
        return txtGUI;
    }

    public void setTxtGUI(String txtGUI) {
        this.txtGUI = txtGUI;
    }

    public String getTxtLink() {
        return txtLink;
    }

    public void setTxtLink(String txtLink) {
        this.txtLink = txtLink;
    }

    @DatabaseField(columnName = "txtGUI")
    public String txtGUI;
    @DatabaseField(columnName = "idDevice")
    public String idDevice;
    @DatabaseField(id = true,columnName = "intMApplication")
    public String intMApplication;
    @DatabaseField(columnName = "txtNameApp")
    public String txtNameApp;
    @DatabaseField(columnName = "bitActive")
    public String bitActive;
    @DatabaseField(columnName = "txtVersion")
    public String txtVersion;
    @DatabaseField(columnName = "txtType")
    public String txtType;
    @DatabaseField(columnName = "txtDevice")
    public String txtDevice;
    @DatabaseField(columnName = "txtModel")
    public String txtModel;
    @DatabaseField(columnName = "txtUserId")
    public String txtUserId;
    @DatabaseField(columnName = "txtLink")
    public String txtLink;

    public clsDeviceInfoData() {

    }
}
