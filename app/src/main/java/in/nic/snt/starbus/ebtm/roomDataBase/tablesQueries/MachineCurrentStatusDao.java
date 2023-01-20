package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.MachineCurrentStatusModel;

@Dao
public interface MachineCurrentStatusDao {

    @Insert
    void insertRecord(MachineCurrentStatusModel machineCurrentStatusModel);

    @Query("SELECT EXISTS(SELECT * FROM MachineCurrentStatusModel WHERE machineStatusId= :machineStatusId)")
    Boolean is_exist(String machineStatusId);

    @Query("DELETE FROM MachineCurrentStatusModel WHERE machineStatusId = :machineStatusId")
    void deleteMachineStatus(String machineStatusId);

    @Query("SELECT * FROM MachineCurrentStatusModel")
    MachineCurrentStatusModel getMachineCurrentStatus();

    @Query("UPDATE MachineCurrentStatusModel SET machineStatusId = :machineStatus")
    void updateMachineCurrentStatus(int machineStatus);

    @Query("UPDATE MachineCurrentStatusModel SET machineStatusId = :machineStatus , waybillNo =:waybillNo, waybillDateTime = :waybillDateTime")
    void updateMachineCurrentStatusAndWaybillNo(String machineStatus,String waybillNo, String waybillDateTime);

    @Query("UPDATE MachineCurrentStatusModel SET machineStatusId = :machineStatus, tripStartDateTime= :tripStartDateTime")
    void updateMachineCurrentStatus(String machineStatus, String tripStartDateTime);

}
