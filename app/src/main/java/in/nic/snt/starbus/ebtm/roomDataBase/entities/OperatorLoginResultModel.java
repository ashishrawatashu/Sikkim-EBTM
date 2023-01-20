
package in.nic.snt.starbus.ebtm.roomDataBase.entities;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class OperatorLoginResultModel {

    @ColumnInfo(name = "roleCode")
    @SerializedName("ROLECODE")
    @Expose
    private Integer rolecode;

    @ColumnInfo(name = "userRoleName")
    @SerializedName("USERROLENAME")
    @Expose
    private String userrolename;

    @ColumnInfo(name = "userName")
    @SerializedName("USERNAME")
    @Expose
    private String username;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userCode")
    @SerializedName("USERCODE")
    @Expose
    private String usercode;

    @ColumnInfo(name = "usrPwd")
    @SerializedName("USRPWD")
    @Expose
    private String usrpwd;

    @ColumnInfo(name = "userType")
    @SerializedName("USERTYPE")
    @Expose
    private String usertype;

    @ColumnInfo(name = "depotCode")
    @SerializedName("DEPOTCODE")
    @Expose
    private Integer depotcode;

    @ColumnInfo(name = "depotName")
    @SerializedName("DEPOTNAME")
    @Expose
    private String depotname;

    @ColumnInfo(name = "tripYN")
    @SerializedName("TRIPYN")
    @Expose
    private String tripyn;

    @ColumnInfo(name = "counteId")
    @SerializedName("COUNTEID")
    @Expose
    private Integer counteid;

    @ColumnInfo(name = "msg")
    @SerializedName("MSG")
    @Expose
    private String msg;

    @ColumnInfo(name = "failCount")
    @SerializedName("FAILCOUNT")
    @Expose
    private Integer failcount;

    @ColumnInfo(name = "convertYN")
    @SerializedName("CONVERTYN")
    @Expose
    private String convertyn;

    @ColumnInfo(name = "convertDateTime")
    @SerializedName("CONVERTDATETIME")
    @Expose
    private String convertdatetime;

    @ColumnInfo(name = "lockStatus")
    @SerializedName("LOCKSTATUS")
    @Expose
    private String lockstatus;

    @ColumnInfo(name = "userDesignation")
    @SerializedName("USERDESIGNATION")
    @Expose
    private String userdesignation;

    @ColumnInfo(name = "ofcId")
    @SerializedName("OFCID")
    @Expose
    private Integer ofcid;

    @ColumnInfo(name = "ofcLvlId")
    @SerializedName("OFCLVLID")
    @Expose
    private Integer ofclvlid;

    public Integer getRolecode() {
        return rolecode;
    }

    public void setRolecode(Integer rolecode) {
        this.rolecode = rolecode;
    }

    public String getUserrolename() {
        return userrolename;
    }

    public void setUserrolename(String userrolename) {
        this.userrolename = userrolename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsrpwd() {
        return usrpwd;
    }

    public void setUsrpwd(String usrpwd) {
        this.usrpwd = usrpwd;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Integer getDepotcode() {
        return depotcode;
    }

    public void setDepotcode(Integer depotcode) {
        this.depotcode = depotcode;
    }

    public String getDepotname() {
        return depotname;
    }

    public void setDepotname(String depotname) {
        this.depotname = depotname;
    }

    public String getTripyn() {
        return tripyn;
    }

    public void setTripyn(String tripyn) {
        this.tripyn = tripyn;
    }

    public Integer getCounteid() {
        return counteid;
    }

    public void setCounteid(Integer counteid) {
        this.counteid = counteid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getFailcount() {
        return failcount;
    }

    public void setFailcount(Integer failcount) {
        this.failcount = failcount;
    }

    public String getConvertyn() {
        return convertyn;
    }

    public void setConvertyn(String convertyn) {
        this.convertyn = convertyn;
    }

    public String getConvertdatetime() {
        return convertdatetime;
    }

    public void setConvertdatetime(String convertdatetime) {
        this.convertdatetime = convertdatetime;
    }

    public String getLockstatus() {
        return lockstatus;
    }

    public void setLockstatus(String lockstatus) {
        this.lockstatus = lockstatus;
    }

    public String getUserdesignation() {
        return userdesignation;
    }

    public void setUserdesignation(String userdesignation) {
        this.userdesignation = userdesignation;
    }

    public Integer getOfcid() {
        return ofcid;
    }

    public void setOfcid(Integer ofcid) {
        this.ofcid = ofcid;
    }

    public Integer getOfclvlid() {
        return ofclvlid;
    }

    public void setOfclvlid(Integer ofclvlid) {
        this.ofclvlid = ofclvlid;
    }

}
