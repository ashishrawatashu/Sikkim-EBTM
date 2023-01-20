package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConductorDriverTiModel;

@Dao
public interface ConductorDriverTIDao {

    @Insert
    void insertRecord(ConductorDriverTiModel ConductorDriverTiModel);

    @Query("SELECT EXISTS(SELECT * FROM ConductorDriverTiModel WHERE usercode = :userCode)")
    Boolean is_exist(int userCode);

    @Query("SELECT * FROM ConductorDriverTiModel")
    List<ConductorDriverTiModel> getConductorDriverList();

    @Query("DELETE FROM ConductorDriverTiModel WHERE userCode = :userCode")
    void deleteConductorDriverByUserCode(int userCode);

    @Query("SELECT * FROM ConductorDriverTiModel WHERE usercode = :userCode")
    ConductorDriverTiModel getConductorDriverInfoByUserId(String userCode);


    @Query("DELETE FROM ConductorDriverTiModel")
    public void deleteConductorDriverTable();


}
