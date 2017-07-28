package kalbefamily.crm.kalbe.kalbefamily;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.owater.library.CircleTextView;

import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;

import kalbefamily.crm.kalbe.kalbefamily.BL.clsActivity;
import kalbefamily.crm.kalbe.kalbefamily.Common.clsUserMember;
import kalbefamily.crm.kalbe.kalbefamily.Common.clsUserMemberImage;
import kalbefamily.crm.kalbe.kalbefamily.Data.VolleyResponseListener;
import kalbefamily.crm.kalbe.kalbefamily.Data.VolleyUtils;
import kalbefamily.crm.kalbe.kalbefamily.Repo.clsUserMemberImageRepo;
import kalbefamily.crm.kalbe.kalbefamily.Repo.clsUserMemberRepo;

/**
 * Created by Rian Andrivani on 7/19/2017.
 */

public class FragmentInfoContact extends Fragment {
    View v;
    TextView tvUsername, tvAlamat, tvMember;
    Context context;
    CircleTextView ctvStatus;
    List<clsUserMember> dataMember = null;
    clsUserMemberRepo repoUserMember = null;
    clsUserMemberImageRepo imageRepo = null;
    private String txtMember;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_kontak_detail,container,false);
        context = getActivity().getApplicationContext();
        tvUsername = (TextView) v.findViewById(R.id.tvUsername);
        tvAlamat = (TextView) v.findViewById(R.id.tvAlamat);
        tvMember = (TextView) v.findViewById(R.id.tvMember);
//        ctvStatus = (CircleTextView) v.findViewById(R.id.status);
//        clsUserLoginData data = new clsUserLoginRepo(context).getDataLogin(context);
//        clsAbsenData dataAbsen = new clsAbsenDataRepo(context).getDataCheckinActive(context);
        try {
            repoUserMember = new clsUserMemberRepo(context);
            dataMember = (List<clsUserMember>) repoUserMember.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tvUsername.setText(dataMember.get(0).getTxtNama().toString());
        tvAlamat.setText(dataMember.get(0).getTxtAlamat().toString());
        if (dataMember.get(0).getTxtEmail().equals("")) {
            tvMember.setText(dataMember.get(0).getTxtMemberId().toString());
        } else {
            tvMember.setText(dataMember.get(0).getTxtEmail().toString());
        }

        UserMember();
//        if (data != null){
//            tvUsername.setText(data.getTxtName().toString());
//            tvEmail.setText(data.getTxtEmail().toString());
//        }
//        if (dataAbsen!=null){
//            ctvStatus.setText("Checkin");
//        }else{
//            ctvStatus.setText("Checkout");
//        }

        return v;
    }

    public void UserMember() {
        final ProgressDialog Dialog = new ProgressDialog(getActivity());
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        clsUserMemberRepo repo = new clsUserMemberRepo(context.getApplicationContext());
        try {
            dataMember = (List<clsUserMember>) repo.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        txtMember = dataMember.get(0).getTxtMemberId();
        String strLinkAPI = "http://10.171.10.27/WebApi2/KF/GetDetailKontak";
//        String nameRole = selectedRole;
        JSONObject resJson = new JSONObject();

        try {
            resJson.put("txtMemberIdOrTelpId", txtMember);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final String mRequestBody = "[" + resJson.toString() + "]";

        new VolleyUtils().makeJsonObjectRequest(getActivity(), strLinkAPI, mRequestBody, "Updating Data, Please wait !", new VolleyResponseListener() {
            @Override
            public void onError(String response) {
                new clsActivity().showCustomToast(context.getApplicationContext(), response, false);
            }

            @Override
            public void onResponse(String response, Boolean status, String strErrorMsg) {
                if (response != null) {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response);
                        JSONObject jsn = jsonObject1.getJSONObject("validJson");
                        String warn = jsn.getString("TxtMessage");
                        String result = jsn.getString("IntResult");

                        if (result.equals("1")) {
                            JSONArray jsonDataUserMember = jsn.getJSONArray("ListOfObjectData");
                            for(int i=0; i < jsonDataUserMember.length(); i++) {
                                JSONObject jsonobject = jsonDataUserMember.getJSONObject(i);
                                String txtKontakId = jsonobject.getString("TxtKontakId");
                                String memberID = jsonobject.getString("TxtMemberId");
                                String txtNama = jsonobject.getString("TxtNama");
                                String txtAlamat = jsonobject.getString("TxtAlamat");
                                String txtJenisKelamin = jsonobject.getString("TxtJenisKelamin");
                                String txtEmail = jsonobject.getString("TxtEmail");
                                String txtTelp = jsonobject.getString("TxtTelp");
                                String txtNoKTP = jsonobject.getString("TxtNoKTP");
                                String txtNamaKeluarga = jsonobject.getString("TxtNamaKeluarga");
                                String txtNamaPanggilan = jsonobject.getString("TxtNamaPanggilan");

                                clsUserMember dataUser = new clsUserMember();
                                dataUser.setTxtKontakId(txtKontakId);
                                dataUser.setTxtMemberId(memberID);
                                dataUser.setTxtNama(txtNama);
                                dataUser.setTxtAlamat(txtAlamat);
                                dataUser.setTxtJenisKelamin(txtJenisKelamin);
                                dataUser.setTxtEmail(txtEmail);
                                dataUser.setTxtNoTelp(txtTelp);
                                dataUser.setTxtNoKTP(txtNoKTP);
                                dataUser.setTxtNamaKeluarga(txtNamaKeluarga);
                                dataUser.setTxtNamaPanggilan(txtNamaPanggilan);

                                repoUserMember = new clsUserMemberRepo(context.getApplicationContext());
//
                                int h = 0;
                                h = repoUserMember.createOrUpdate(dataUser);
                                if(h > -1) {
                                    Log.d("Data info", "Data member Updated");
//                                    status = true;
                                }

                                JSONArray jsonDataUserMemberImage = jsonobject.getJSONArray("ListtkontakImage");
                                for(int j=0; j < jsonDataUserMemberImage.length(); j++) {
                                    JSONObject jsonobjectImage = jsonDataUserMemberImage.getJSONObject(j);
                                    String txtGuiID = jsonobjectImage.getString("TxtDataID");
                                    String txtKontakIDImage = jsonobjectImage.getString("TxtKontakID");
                                    String txtImageName = jsonobjectImage.getString("TxtImageName");
                                    String txtType = jsonobjectImage.getString("TxtType");

                                    clsUserMemberImage dataImage = new clsUserMemberImage();
                                    dataImage.setTxtGuiId(txtGuiID);
                                    dataImage.setTxtHeaderId(txtKontakIDImage);
                                    dataImage.setTxtPosition(txtType);

                                    String url = String.valueOf(jsonobjectImage.get("TxtPath"));

                                    byte[] logoImage = getLogoImage(url);

                                    if (logoImage != null) {
                                        dataImage.setTxtImg(logoImage);
                                    }

                                    imageRepo = new clsUserMemberImageRepo(context.getApplicationContext());

                                    int k = 0;
                                    k = imageRepo.createOrUpdate(dataImage);
                                    if(k > -1) {
                                        Log.d("Data info", "Data Image's Member Updated");
//                                    status = true;
                                    }
                                }
                            }
                            new clsActivity().showCustomToast(context.getApplicationContext(), "Update Data, Success", true);
                        } else {
                            new clsActivity().showCustomToast(context.getApplicationContext(), warn, false);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
//                if(!status){
//                    new clsMainActivity().showCustomToast(getApplicationContext(), strErrorMsg, false);
//                }
            }
        });
    }

    private byte[] getLogoImage(String url) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();
            String contentType = ucon.getHeaderField("Content-Type");
            boolean image = contentType.startsWith("image/");

            if (image) {
                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                ByteArrayBuffer baf = new ByteArrayBuffer(500);
                int current = 0;
                while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
                }

                return baf.toByteArray();
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }
        return null;
    }

}
