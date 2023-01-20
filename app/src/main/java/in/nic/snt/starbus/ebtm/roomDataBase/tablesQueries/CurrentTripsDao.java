package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;

@Dao
public interface CurrentTripsDao {

    @Insert
    void insertCurrentTrips(CurrentTripsModel currentTripsModel);

    @Query("SELECT EXISTS(SELECT * FROM CurrentTrips WHERE tripNo= :tripNo)")
    Boolean is_exist(int tripNo);

    @Query("SELECT * FROM CurrentTrips")
    List<CurrentTripsModel> getCurrentTripsData();

    @Query("DELETE FROM CurrentTrips WHERE tripNo = :tripNo")
    void deleteTripsBysId(int tripNo);

    @Query("DELETE FROM CurrentTrips")
    void deleteTripsTable();


    @Query("SELECT * FROM CurrentTrips WHERE completeYN= 'N'")
    CurrentTripsModel getIncompleteTripData();

    @Query("UPDATE CurrentTrips SET completeYN = 'Y', completeDateTime =:currentDateTime WHERE tripNo =:currentTripNo")
    void closeTripAndUpdateStatus(int currentTripNo, String currentDateTime);

}
