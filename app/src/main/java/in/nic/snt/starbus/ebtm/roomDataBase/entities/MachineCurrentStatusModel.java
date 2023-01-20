package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MachineCurrentStatusModel {


    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "machineStatusId",defaultValue = "0")
    public String machineStatusId ;

    @ColumnInfo(name = "waybillNo")
    public String waybillNo;

    @ColumnInfo(name = "waybillDateTime")
    public String waybillDateTime;

    @ColumnInfo(name = "currentTripNo")
    public String CurrentTripNo;

    @ColumnInfo(name = "tripStartDateTime")
    public String tripStartDateTime;

    @ColumnInfo(name = "currentStage")
    public String currentStage;

    public String getMachineStatusId() {
        return machineStatusId;
    }

    public void setMachineStatusId(String machineStatusId) {
        this.machineStatusId = machineStatusId;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getWaybillDateTime() {
        return waybillDateTime;
    }

    public void setWaybillDateTime(String waybillDateTime) {
        this.waybillDateTime = waybillDateTime;
    }

    public String getCurrentTripNo() {
        return CurrentTripNo;
    }

    public void setCurrentTripNo(String currentTripNo) {
        CurrentTripNo = currentTripNo;
    }

    public String getTripStartDateTime() {
        return tripStartDateTime;
    }

    public void setTripStartDateTime(String tripStartDateTime) {
        this.tripStartDateTime = tripStartDateTime;
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }
}
