package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.WaybillModel;

@Dao
public interface WayBillDao {
    @Insert
    void insertRecord(WaybillModel routesStationModel);

    @Query("SELECT EXISTS(SELECT * FROM WaybillModel WHERE waybillid = :waybillid)")
    Boolean is_exist(int waybillid);

    @Query("SELECT * FROM WaybillModel")
    WaybillModel getWayBillData();

    @Query("DELETE FROM WaybillModel WHERE waybillid = :waybillid")
    void deleteWayBillWaybillId(int waybillid);

    @Query("DELETE FROM WaybillModel")
    void deleteWayBillWaybill();

}
