package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class ExpenseDataModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "tripCode")
    private String tripeCode;

    @ColumnInfo(name = "expenseCode")
    private String expenseCode;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "remark")
    private String remark;

    @ColumnInfo(name = "stage")
    private String stage;


    @ColumnInfo(name = "latitude")
    private String latitude;


    @ColumnInfo(name = "longitude")

    private String longitude;

    @ColumnInfo(name = "dateTime")
    private String dateTime;

    public ExpenseDataModel(String tripeCode, String expenseCode, String amount, String remark, String stage, String latitude, String longitude, String dateTime) {
        this.tripeCode = tripeCode;
        this.expenseCode = expenseCode;
        this.amount = amount;
        this.remark = remark;
        this.stage = stage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTripeCode() {
        return tripeCode;
    }

    public void setTripeCode(String tripeCode) {
        this.tripeCode = tripeCode;
    }

    public String getExpenseCode() {
        return expenseCode;
    }

    public void setExpenseCode(String expenseCode) {
        this.expenseCode = expenseCode;
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
