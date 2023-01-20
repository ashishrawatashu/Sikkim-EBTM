package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class TripsModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sNo")
    @Expose
    private Integer serialNo;

    @ColumnInfo(name = "strpId")
    @SerializedName("STRP_ID")
    @Expose
    private Integer strpId;

    @ColumnInfo(name = "frStonId")
    @SerializedName("FR_STON_ID")
    @Expose
    private Integer frStonId;

    @ColumnInfo(name = "frStonName")
    @SerializedName("FR_STON_NAME")
    @Expose
    private String frStonName;

    @ColumnInfo(name = "toStonId")
    @SerializedName("TO_STON_ID")
    @Expose
    private Integer toStonId;

    @ColumnInfo(name = "toStonName")
    @SerializedName("TO_STON_NAME")
    @Expose
    private String toStonName;

    @ColumnInfo(name = "startTime")
    @SerializedName("START_TIME")
    @Expose
    private String startTime;

    @ColumnInfo(name = "endTime")
    @SerializedName("END_TIME")
    @Expose
    private String endTime;

    @ColumnInfo(name = "tripDirection")
    @SerializedName("TRIP_DIRECTION")
    @Expose
    private String tripDirection;

    @ColumnInfo(name = "isOnlineYN")
    @SerializedName("ISONLINEYN")
    @Expose
    private String isonlineyn;

    @ColumnInfo(name = "isMidStationOnlineYN")
    @SerializedName("ISMIDSTATIONONLINEYN")
    @Expose
    private String ismidstationonlineyn;

    @ColumnInfo(name = "routId")
    @SerializedName("ROUT_ID")
    @Expose
    private Integer routId;


    @ColumnInfo(name = "tripStatus",defaultValue = "P")
    @Expose
    private String tripStatus = "P";

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getStrpId() {
        return strpId;
    }

    public void setStrpId(Integer strpId) {
        this.strpId = strpId;
    }

    public Integer getFrStonId() {
        return frStonId;
    }

    public void setFrStonId(Integer frStonId) {
        this.frStonId = frStonId;
    }

    public String getFrStonName() {
        return frStonName;
    }

    public void setFrStonName(String frStonName) {
        this.frStonName = frStonName;
    }

    public Integer getToStonId() {
        return toStonId;
    }

    public void setToStonId(Integer toStonId) {
        this.toStonId = toStonId;
    }

    public String getToStonName() {
        return toStonName;
    }

    public void setToStonName(String toStonName) {
        this.toStonName = toStonName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTripDirection() {
        return tripDirection;
    }

    public void setTripDirection(String tripDirection) {
        this.tripDirection = tripDirection;
    }

    public String getIsonlineyn() {
        return isonlineyn;
    }

    public void setIsonlineyn(String isonlineyn) {
        this.isonlineyn = isonlineyn;
    }

    public String getIsmidstationonlineyn() {
        return ismidstationonlineyn;
    }

    public void setIsmidstationonlineyn(String ismidstationonlineyn) {
        this.ismidstationonlineyn = ismidstationonlineyn;
    }

    public Integer getRoutId() {
        return routId;
    }

    public void setRoutId(Integer routId) {
        this.routId = routId;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }
}
