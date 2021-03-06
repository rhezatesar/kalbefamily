package kalbefamily.crm.kalbe.kalbefamily.Common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Rian Andrivani on 7/17/2017.
 */
@DatabaseTable
public class clsUserMember implements Serializable {
    public String getTxtKontakId() {
        return txtKontakId;
    }

    public void setTxtKontakId(String txtKontakId) {
        this.txtKontakId = txtKontakId;
    }

    public String getTxtMemberId() {
        return txtMemberId;
    }

    public void setTxtMemberId(String txtMemberId) {
        this.txtMemberId = txtMemberId;
    }

    public String getTxtNama() {
        return txtNama;
    }

    public void setTxtNama(String txtNama) {
        this.txtNama = txtNama;
    }

    public String getTxtAlamat() {
        return txtAlamat;
    }

    public void setTxtAlamat(String txtAlamat) {
        this.txtAlamat = txtAlamat;
    }

    public String getTxtJenisKelamin() {
        return txtJenisKelamin;
    }

    public void setTxtJenisKelamin(String txtJenisKelamin) {
        this.txtJenisKelamin = txtJenisKelamin;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtNoTelp() {
        return txtNoTelp;
    }

    public void setTxtNoTelp(String txtNoTelp) {
        this.txtNoTelp = txtNoTelp;
    }

    public String getTxtNoKTP() {
        return txtNoKTP;
    }

    public void setTxtNoKTP(String txtNoKTP) {
        this.txtNoKTP = txtNoKTP;
    }

    public String getTxtNamaBelakang() {
        return txtNamaBelakang;
    }

    public void setTxtNamaBelakang(String txtNamaBelakang) {
        this.txtNamaBelakang = txtNamaBelakang;
    }

    public String getTxtNamaPanggilan() {
        return txtNamaPanggilan;
    }

    public void setTxtNamaPanggilan(String txtNamaPanggilan) {
        this.txtNamaPanggilan = txtNamaPanggilan;
    }

    public String getTxtNamaDepan() {
        return txtNamaDepan;
    }

    public void setTxtNamaDepan(String txtNamaDepan) {
        this.txtNamaDepan = txtNamaDepan;
    }

    public String getTxtBasePoin() {
        return txtBasePoin;
    }

    public void setTxtBasePoin(String txtBasePoin) {
        this.txtBasePoin = txtBasePoin;
    }

    public String getTxtTglAwal() {
        return txtTglAwal;
    }

    public void setTxtTglAwal(String txtTglAwal) {
        this.txtTglAwal = txtTglAwal;
    }

    public String getTxtTglBerlaku() {
        return txtTglBerlaku;
    }

    public void setTxtTglBerlaku(String txtTglBerlaku) {
        this.txtTglBerlaku = txtTglBerlaku;
    }

    @DatabaseField(id = true,columnName = "txtKontakId")
    public String txtKontakId;
    @DatabaseField(columnName = "txtMemberId")
    public String txtMemberId;
    @DatabaseField(columnName = "txtNama")
    public String txtNama;
    @DatabaseField(columnName = "txtAlamat")
    public String txtAlamat;
    @DatabaseField(columnName = "txtJenisKelamin")
    public String txtJenisKelamin;
    @DatabaseField(columnName = "txtEmail")
    public String txtEmail;
    @DatabaseField(columnName = "txtNoTelp")
    public String txtNoTelp;
    @DatabaseField(columnName = "txtNoKTP")
    public String txtNoKTP;
    @DatabaseField(columnName = "txtNamaBelakang")
    public String txtNamaDepan;
    @DatabaseField(columnName = "txtNamaDepan")
    public String txtNamaBelakang;
    @DatabaseField(columnName = "txtNamaPanggilan")
    public String txtNamaPanggilan;
    @DatabaseField(columnName = "txtBasePoin")
    public String txtBasePoin;
    @DatabaseField(columnName = "txtTglAwal")
    public String txtTglAwal;
    @DatabaseField(columnName = "txtTglBerlaku")
    public String txtTglBerlaku;

    public String Property_txtKontakId = "txtKontakID";
    public String Property_txtMemberId = "txtMemberId";
    public String Property_txtNamaDepan = "txtNamaDepan";
    public String Property_txtAlamat = "txtAlamat";
    public String Property_txtJenisKelamin = "txtJenisKelamin";
    public String Property_txtEmail = "txtEmail";
    public String Property_txtNoTelp = "txtTelp";
    public String Property_txtNoKTP = "txtNoKTP";
    public String Property_txtNamaBelakang = "txtNamaBelakang";
    public String Property_txtNamaPanggilan = "txtNamaPanggilan";
    public String Property_intBasePoin = "txtBasePoin";
    public String Property_ListOfUserMember = "ListOfObjectData";
    public String Property_All=Property_txtKontakId +","+
            Property_txtMemberId +","+
            Property_txtNamaDepan +","+
            Property_txtAlamat+","+
            Property_txtJenisKelamin+","+
            Property_txtEmail+","+
            Property_txtNoTelp+","+
            Property_txtNoKTP+","+
            Property_txtNamaBelakang +","+
            Property_txtNamaPanggilan+","+
            Property_intBasePoin;

    public clsUserMember() {

    }
}
