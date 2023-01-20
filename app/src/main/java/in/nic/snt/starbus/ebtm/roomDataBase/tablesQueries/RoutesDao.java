package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesModel;

@Dao
public interface RoutesDao {


    @Insert
    void insertRecord(RoutesModel routesModel);

    @Query("SELECT EXISTS(SELECT * FROM RoutesModel WHERE routeId= :routeId)")
    Boolean is_exist(int routeId);

    @Query("SELECT * FROM RoutesModel")
    List<RoutesModel> getRoutes();


    @Query("SELECT * FROM RoutesModel where routeName like '%' ||:routeName|| '%'")
    List<RoutesModel> getRoutes_search(String routeName);


    @Query("DELETE FROM RoutesModel WHERE routeId = :routeId")
    void deleteRoutesById(int routeId);

    @Query("DELETE FROM RoutesModel")
    public void deleteRoutesTable();



}
