package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;


@Dao
public interface TripsDao {

    @Insert
    void insertRecord(TripsModel TripsModel);

    @Query("SELECT EXISTS(SELECT * FROM TripsModel WHERE strpId= :strpId)")
    Boolean is_exist(int strpId);

    @Query("SELECT * FROM TripsModel")
    List<TripsModel> getTrips();

    @Query("SELECT * FROM TripsModel WHERE strpId = :strpId")
    TripsModel getTripByStrId(String strpId);

    @Query("DELETE FROM TripsModel WHERE strpId = :strpId")
    void deleteTripsByStrId(int strpId);

    @Query("UPDATE TripsModel SET tripStatus = 'D' WHERE tripStatus = 'P' and sNo<:sno")
    void disablePeviousTrips(int sno);

    @Query("UPDATE TripsModel SET tripStatus = :status WHERE sNo=:sno")
    void updateTripStatus(int sno, String status);

    @Query("UPDATE TripsModel SET tripStatus = 'D' WHERE tripStatus ='P'")
    void updateAllPendingTrips();

    @Query("DELETE FROM TripsModel")
    public void deleteTripsTable();


}
