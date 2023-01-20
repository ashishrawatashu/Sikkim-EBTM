package in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import java.util.Stack;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.MachineStatusModel;

@Dao
public interface MachineStatusDao {

    @Insert
    void insertRecord(MachineStatusModel machineStatusModel);

    @Query("SELECT EXISTS(SELECT * FROM MachineStatusModel WHERE statusId= :statusId)")
    Boolean is_exist(int statusId);

    @Query("SELECT * FROM MachineStatusModel")
    List<MachineStatusModel> getMachineStatus();

    @Query("UPDATE MachineStatusModel SET statusName = :statusName WHERE statusId =:id")
    void updateMachineStatus(int id, String statusName);

    @Query("DELETE FROM MachineStatusModel WHERE statusId = :statusId")
    void deleteMachineStatus(int statusId);

}
