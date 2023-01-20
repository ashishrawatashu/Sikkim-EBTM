package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RouteFareModel;

@Dao
public interface RouteFareDao {

    @Insert
    void insertRecord(RouteFareModel routeFareModel);

    @Query("SELECT EXISTS(SELECT * FROM RouteFareModel WHERE routeId= :routeId)")
    Boolean is_exist(int routeId);

    @Query("SELECT * FROM RouteFareModel")
    List<RouteFareModel> getRouteFare();


    @Query("DELETE FROM RouteFareModel WHERE routeId = :routeId")
    void deleteRouteFareByRouteId(int routeId);

    @Query("DELETE FROM RouteFareModel")
    void deleteRouteFareTable();

}
