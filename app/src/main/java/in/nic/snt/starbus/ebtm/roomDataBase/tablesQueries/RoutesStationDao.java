package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesStationModel;

@Dao
public interface RoutesStationDao {

    @Insert
    void insertRecord(RoutesStationModel routesStationModel);

    @Query("SELECT EXISTS(SELECT * FROM RoutesStationModel WHERE routeId= :routeId)")
    Boolean is_exist(int routeId);

    @Query("SELECT * FROM RoutesStationModel")
    List<RoutesStationModel> getRoutesStation();

    @Query("SELECT * FROM RoutesStationModel WHERE routeId = :routeId")
    List<RoutesStationModel> getRoutesStationByRouteId(int routeId);

    @Query("DELETE FROM RoutesStationModel WHERE routeId = :routeId")
    void deleteRoutesStationByRouteId(int routeId);

    @Query("DELETE FROM RoutesStationModel")
    void deleteRoutesStation();

}
