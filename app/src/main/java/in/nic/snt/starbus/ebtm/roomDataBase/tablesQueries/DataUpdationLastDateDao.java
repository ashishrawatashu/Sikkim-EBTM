package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.DataLastDateUpdationDateModel;

@Dao
public interface DataUpdationLastDateDao {
    @Insert
    void insertRecord(DataLastDateUpdationDateModel dataLastDateUpdationDateModel);

    @Query("SELECT EXISTS(SELECT * FROM DataLastDateUpdationDateModel WHERE lastDateUpdationDateId= :lastDateUpdationDateId)")
    Boolean is_exist(int lastDateUpdationDateId);

    @Query("SELECT * FROM DataLastDateUpdationDateModel")
    List<DataLastDateUpdationDateModel> getDataLastDateUpdationDateModel();

    @Query("UPDATE DataLastDateUpdationDateModel SET routeLastUpdate = :routeLastUpdate WHERE lastDateUpdationDateId =:id")
    void updateRouteLastUpdate(int id, String routeLastUpdate);

    @Query("UPDATE DataLastDateUpdationDateModel SET routeStationLastUpdate = :routeStationLastUpdate WHERE lastDateUpdationDateId =:id")
    void updateRouteStationLastUpdate(int id, String routeStationLastUpdate);

    @Query("UPDATE DataLastDateUpdationDateModel SET fareStationLastUpdate = :fareStationLastUpdate WHERE lastDateUpdationDateId =:id")
    void updateFareStationLastUpdate(int id, String fareStationLastUpdate);

    @Query("UPDATE DataLastDateUpdationDateModel SET concessionLastUpdate = :concessionLastUpdate WHERE lastDateUpdationDateId =:id")
    void updateConcessionLastUpdate(int id, String concessionLastUpdate);

    @Query("DELETE FROM DataLastDateUpdationDateModel WHERE lastDateUpdationDateId = :lastDateUpdationDateId")
    void deleteMachineStatus(int lastDateUpdationDateId);

}
