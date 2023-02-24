package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class EarningDataModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("ID")
    @Expose
    private Integer id;

    @ColumnInfo(name = "tripCode")
    @SerializedName("TRIPECODE")
    @Expose
    private String tripeCode;

    @ColumnInfo(name = "earnCode")
    @SerializedName("EARNCODE")
    @Expose
    private String earnCode;

    @ColumnInfo(name = "amount")
    @SerializedName("AMOUNT")
    @Expose
    private String amount;

    @ColumnInfo(name = "remark")
    @SerializedName("REMARK")
    @Expose
    private String remark;

    @ColumnInfo(name = "stage")
    @SerializedName("STAGE")
    @Expose
    private String stage;


    @ColumnInfo(name = "latitude")
    @SerializedName("LATITUDE")
    @Expose
    private String latitude;


    @ColumnInfo(name = "longitude")
    @SerializedName("LONGITUDE")
    @Expose
    private String longitude;

    @ColumnInfo(name = "dateTime")
    @SerializedName("DATETIME")
    @Expose
    private String dateTime;

    public EarningDataModel(String tripeCode, String earnCode, String amount, String remark, String stage, String latitude, String longitude, String dateTime) {
        this.tripeCode = tripeCode;
        this.earnCode = earnCode;
        this.amount = amount;
        this.remark = remark;
        this.stage = stage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
    }

    public String getTripeCode() {
        return tripeCode;
    }

    public void setTripeCode(String tripeCode) {
        this.tripeCode = tripeCode;
    }

    public String getEarnCode() {
        return earnCode;
    }

    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getLatitude() {
        return latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
