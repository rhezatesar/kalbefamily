package kalbefamily.crm.kalbe.kalbefamily.Data;

import android.os.Environment;

import java.io.File;

/**
 * Created by Rian Andrivani on 6/22/2017.
 */

public class clsHardCode {
    public String dbName = "KalbeFamily.db";
    public String txtFolderData = Environment.getExternalStorageDirectory()+ File.separator+"Android"+File.separator+"data"+File.separator+"KalbeFamily"+File.separator+"image_Person"+File.separator;
    public String txtFolderAbsen = Environment.getExternalStorageDirectory()+ File.separator+"Android"+File.separator+"data"+File.separator+"KalbeFamily"+File.separator+"image_Person"+File.separator;

    public String linkLogin = "http://10.171.11.70/WebApi2/KF/GetDetailKontak";
    public String linkCheckVersion = "http://10.171.11.70/WebApi2/KF/CheckVersionApp";
    //    public String linkPushData = "http://10.171.11.87/APIEF2/api/PushData/pushData2";
    public String linkSendData = "http://10.171.11.70/WebApi2/KF/UpdateDataKontak";
    public String linkGetDetailCustomer = "http://10.171.11.70/WebApi2/KF/GetDetailKontakCustomer";
    public String linkScanQRCode = "http://10.171.11.70/WebApi2/KF/ScanQRCode";
}
