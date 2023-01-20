package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentUserLoginModel;

@Dao
public interface CurrentUserLoginStatusDao {


    @Insert
    void insertLoginRecord(CurrentUserLoginModel currentUserLoginModel);

    @Query("UPDATE CurrentUserLoginStatus SET loginStatus = :status")
    void updateUserLoginStatus(String status);

    @Query("SELECT * FROM CurrentUserLoginStatus")
    CurrentUserLoginModel getCurrentUserLoginStatus();

    @Query("DELETE FROM CurrentUserLoginStatus")
    void deleteUserLoginTable();

}
