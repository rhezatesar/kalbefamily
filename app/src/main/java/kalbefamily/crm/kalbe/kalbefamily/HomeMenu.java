package kalbefamily.crm.kalbe.kalbefamily;

import org.apache.http.util.ByteArrayBuffer;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;

import jim.h.common.android.lib.zxing.config.ZXingLibConfig;
import jim.h.common.android.lib.zxing.integrator.IntentIntegrator;
import jim.h.common.android.lib.zxing.integrator.IntentResult;
import kalbefamily.crm.kalbe.kalbefamily.BL.clsActivity;
import kalbefamily.crm.kalbe.kalbefamily.Common.clsUserMember;
import kalbefamily.crm.kalbe.kalbefamily.Common.clsUserMemberImage;
import kalbefamily.crm.kalbe.kalbefamily.Data.DatabaseHelper;
import kalbefamily.crm.kalbe.kalbefamily.Data.DatabaseManager;
import kalbefamily.crm.kalbe.kalbefamily.Data.VolleyResponseListener;
import kalbefamily.crm.kalbe.kalbefamily.Data.VolleyUtils;
import kalbefamily.crm.kalbe.kalbefamily.Repo.clsUserMemberImageRepo;
import kalbefamily.crm.kalbe.kalbefamily.Repo.clsUserMemberRepo;


/**
 * Created by Rian Andrivani on 7/19/2017.
 */

public class HomeMenu extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private TextView tvUsername, tvEmail;
    PackageInfo pInfo = null;
    int selectedId;
    Boolean isSubMenu = false;
    List<clsUserMember> dataMember = null;

    private GoogleApiClient client;
    clsActivity _clsMainActivity = new clsActivity();
    private ZXingLibConfig zxingLibConfig;

    private String txtMember;
    clsUserMemberRepo repoUserMember = null;
    clsUserMemberImageRepo imageRepo = null;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Do you want to exit?");

        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedId = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color_theme));
        }
        try {
            pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main_contact);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

//        UserMember();

        FragmentInfoContact ContactFragment = new FragmentInfoContact();
        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
        fragmentTransactionHome.replace(R.id.frame, ContactFragment);
        fragmentTransactionHome.commit();
        selectedId = 99;

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View vwHeader = navigationView.getHeaderView(0);
        tvUsername = (TextView) vwHeader.findViewById(R.id.username);
        tvEmail = (TextView) vwHeader.findViewById(R.id.email);
        clsUserMemberRepo repo = new clsUserMemberRepo(getApplicationContext());
        try {
            dataMember = (List<clsUserMember>) repo.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tvUsername.setText(_clsMainActivity.greetings() + dataMember.get(0).getTxtNama());
        if (dataMember.get(0).getTxtEmail().equals("")) {
            tvEmail.setText(dataMember.get(0).getTxtMemberId().toString());
        } else {
            tvEmail.setText(dataMember.get(0).getTxtEmail().toString());
        }

        Menu header = navigationView.getMenu();
        SubMenu subMenuVersion = header.addSubMenu(R.id.groupVersion, 0, 3, "Version");
        try {
            subMenuVersion.add(getPackageManager().getPackageInfo(getPackageName(), 0).versionName + " \u00a9 KN-IT").setIcon(R.drawable.ic_android).setEnabled(false);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.logoutContact:
                        LayoutInflater layoutInflater = LayoutInflater.from(HomeMenu.this);
                        final View promptView = layoutInflater.inflate(R.layout.confirm_data, null);

                        final TextView _tvConfirm = (TextView) promptView.findViewById(R.id.tvTitle);
                        final TextView _tvDesc = (TextView) promptView.findViewById(R.id.tvDesc);
                        _tvDesc.setVisibility(View.INVISIBLE);
                        _tvConfirm.setText("Log Out Application ?");
                        _tvConfirm.setTextSize(18);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeMenu.this);
                        alertDialogBuilder.setView(promptView);
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        logout();
//                                        new clsActivity().showCustomToast(getApplicationContext(), "Logout, Success", true);
//                                        Toast.makeText(getApplicationContext(), "Logout, Success", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        final AlertDialog alertD = alertDialogBuilder.create();
                        alertD.show();
                        return true;
                    case R.id.contact:
                        toolbar.setTitle("Home");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentInfoContact ContactFragment = new FragmentInfoContact();
                        FragmentTransaction fragmentTransactionHome = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionHome.replace(R.id.frame, ContactFragment);
                        fragmentTransactionHome.commit();
                        selectedId = 99;

                        return true;
                    case R.id.personalData:
                        toolbar.setTitle("Personal data");

                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                        FragmentPersonalData fragmentPersonalData = new FragmentPersonalData();
                        FragmentTransaction fragmentTransactionPersonalData = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionPersonalData.replace(R.id.frame, fragmentPersonalData);
                        fragmentTransactionPersonalData.commit();
                        selectedId = 99;

                        return true;
                    case R.id.scanQrCode:
//                        IntentIntegrator.initiateScan(HomeMenu.this, zxingLibConfig);

                        Intent intent = new Intent(getApplicationContext(), QrCodeActivity.class);
                        finish();
                        startActivity(intent);

                        return true;
                    case R.id.rewardCard:
//                        toolbar.setTitle("Reward Card");
//
//                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

//                        FragmentRewardCard fragmentRewardCard = new FragmentRewardCard();
//                        FragmentTransaction fragmentTransactionRewardCard= getSupportFragmentManager().beginTransaction();
//                        fragmentTransactionRewardCard.replace(R.id.frame, fragmentRewardCard);
//                        fragmentTransactionRewardCard.commit();
//                        selectedId = 99;

                        Intent intentReward = new Intent(getApplicationContext(), RewardCardActivity.class);
                        startActivity(intentReward);

                        return true;

                }
                return false;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        toolbar.setTitle(item.getTitle());

        Class<?> fragmentClass;
        try {
            Fragment fragment;

            fragmentClass = Class.forName("kalbefamily.crm.kalbe.kalbefamily" + String.valueOf(item.getTitle()).replaceAll("\\s+", ""));
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();

            selectedId=id;

            if (!isSubMenu) isSubMenu = true;
            else isSubMenu = false;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return super.onOptionsItemSelected(item);
    }

    public void UserMember() {
        final ProgressDialog Dialog = new ProgressDialog(HomeMenu.this);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        clsUserMemberRepo repo = new clsUserMemberRepo(getApplicationContext());
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

        new VolleyUtils().makeJsonObjectRequest(HomeMenu.this, strLinkAPI, mRequestBody, "Updating Data, Please wait !", new VolleyResponseListener() {
            @Override
            public void onError(String response) {
                new clsActivity().showCustomToast(getApplicationContext(), response, false);
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

                                repoUserMember = new clsUserMemberRepo(getApplicationContext());
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

                                    imageRepo = new clsUserMemberImageRepo(getApplicationContext());

                                    int k = 0;
                                    k = imageRepo.createOrUpdate(dataImage);
                                    if(k > -1) {
                                        Log.d("Data info", "Data Image's Member Updated");
//                                    status = true;
                                    }
                                }
                            }
                            new clsActivity().showCustomToast(getApplicationContext(), "Update Data, Success", true);
                        } else {
                            new clsActivity().showCustomToast(getApplicationContext(), warn, false);
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

    private void logout() {
        Intent intent = new Intent(HomeMenu.this, FlashActivity.class);
        DatabaseHelper helper = DatabaseManager.getInstance().getHelper();
        helper.clearDataAfterLogout();
        finish();
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean status = false;

        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanResult.getContents() == null && scanResult.getFormatName() == null) {
                return;
            }
            final String result = scanResult.getContents();
            if (result != null) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), HomeMenu.class);
                finish();
                startActivity(intent);
//                new clsActivity().showCustomToast(context.getApplicationContext(), result, true);
            }
        }
    }
}