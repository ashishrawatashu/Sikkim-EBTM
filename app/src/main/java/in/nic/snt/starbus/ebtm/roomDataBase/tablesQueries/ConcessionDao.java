package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;

@Dao
public interface ConcessionDao {

    @Insert
    void insertRecord(ConcessionsModel concessionsModel);

    @Query("SELECT EXISTS(SELECT * FROM ConcessionsModel WHERE conId= :conId)")
    Boolean is_exist(int conId);

    @Query("SELECT * FROM ConcessionsModel")
    List<ConcessionsModel> getConcessionsData();

    @Query("DELETE FROM ConcessionsModel WHERE conId = :conId")
    void deleteUserBysId(int conId);

    @Query("DELETE FROM ConcessionsModel")
    void deleteConcessionTable();


}
