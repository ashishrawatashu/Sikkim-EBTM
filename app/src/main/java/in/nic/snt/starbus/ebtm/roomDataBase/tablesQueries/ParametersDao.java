package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ParametersModel;

@Dao
public interface ParametersDao {

    @Insert
    void insertRecord(ParametersModel parametersModel);

    @Query("SELECT EXISTS(SELECT * FROM ParametersModel WHERE depotCode= :depotCode)")
    Boolean is_exist(int depotCode);

    @Query("SELECT * FROM ParametersModel")
    List<ParametersModel> getParameters();


    @Query("DELETE FROM ParametersModel WHERE depotCode = :depotCode")
    void deleteParameters(int depotCode);

}
