package kalbefamily.crm.kalbe.kalbefamily.Repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kalbefamily.crm.kalbe.kalbefamily.Common.clsUserMember;
import kalbefamily.crm.kalbe.kalbefamily.Data.DatabaseHelper;
import kalbefamily.crm.kalbe.kalbefamily.Data.DatabaseManager;

/**
 * Created by Rian Andrivani on 7/17/2017.
 */

public class clsUserMemberRepo implements crud {
    private DatabaseHelper helper;
    public clsUserMemberRepo(Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }
    @Override
    public int create(Object item) {
        int index = -1;
        clsUserMember object = (clsUserMember) item;
        try {
            index = helper.getUserMemberDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) {
        int index = -1;
        clsUserMember object = (clsUserMember) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getUserMemberDao().createOrUpdate(object);
            index = status.getNumLinesChanged();
//            index = 1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int update(Object item) {
        int index = -1;
        clsUserMember object = (clsUserMember) item;
        try {
            index = helper.getUserMemberDao().update(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int delete(Object item) {
        int index = -1;
        clsUserMember object = (clsUserMember) item;
        try {
            index = helper.getUserMemberDao().delete(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public Object findById(int id) throws SQLException {
        clsUserMember item = null;
        try{
            item = helper.getUserMemberDao().queryForId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    public Object findByIdString(String id) throws SQLException {
        List<clsUserMember> item = null;
        try{
            item = helper.getUserMemberDao().queryBuilder().where().eq("txtKontakId", id).query();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<?> findAll() throws SQLException {
        List<clsUserMember> items = null;
        try{
            items = helper.getUserMemberDao().queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    public List<clsUserMember> getAllDataToSendData(Context context){
        QueryBuilder<clsUserMember, Integer> queryBuilder = null;
        List<clsUserMember> data = null;
        try {
            data = (List<clsUserMember>) helper.getUserMemberDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clsUserMember dt = new clsUserMember();
        List<clsUserMember> listData = new ArrayList<>();

        if (data.size() > 0) {
            try {
                queryBuilder = helper.getUserMemberDao().queryBuilder();
                listData = queryBuilder.query();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listData;
    }

//    public clsUserMember getDataLogin(Context context){
//        clsUserMemberRepo repo = new clsUserMemberRepo(context);
//        clsUserMember dataLogin =new clsUserMember();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();
//        String now = dateFormat.format(cal.getTime()).toString();
////        if(repo.CheckLoginNow()){
//        List<clsUserMember> listData= null;
//        try {
//            listData = (List<clsUserMember>) repo.findAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        for (clsUserMember data : listData){
//            if (data.dtLastLogin.equals(now)){
//                dataLogin = data;
//            }
//        }
//        return dataLogin;
//    }
}
