package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.OnBoardingStatusModel;

@Dao
public interface OnBoardingStatusDao {

    @Insert
    void insertRecord(OnBoardingStatusModel onBoardingStatusModel);


    @Query("UPDATE OnBoardingStatus SET userType ='O', loginStatus = :isLoginYN WHERE id=:id")
    void updateOperator(int id, String isLoginYN);


    @Query("UPDATE OnBoardingStatus SET userType ='C', loginStatus = :isLoginYN WHERE id=2")
    void updateConductor(String isLoginYN);

    @Query("Select * From OnBoardingStatus Where id = :id")
    OnBoardingStatusModel getUserBoardingStatus(int id);


    @Query("SELECT EXISTS(SELECT * FROM OnBoardingStatus WHERE id= :id)")
    Boolean is_exist(int id);


}
