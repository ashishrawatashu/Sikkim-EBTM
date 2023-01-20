package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.OperatorLoginResultModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.OperatorLoginResultModel;

@Dao
public interface OperatorLoginDetailsDao {
    
    @Insert
    void insertRecord(OperatorLoginResultModel operatorLoginResultModel);

    @Query("SELECT * FROM OperatorLoginResultModel")
    OperatorLoginResultModel getOperatorLoginResult();

    @Query("DELETE FROM OperatorLoginResultModel")
    void deleteWayBillWaybill();


}
