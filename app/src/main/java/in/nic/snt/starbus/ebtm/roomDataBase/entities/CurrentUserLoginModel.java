package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CurrentUserLoginStatus")
public class CurrentUserLoginModel {

    @ColumnInfo(name = "serialNo")
    @PrimaryKey(autoGenerate = false)
    int serialNo;

    @ColumnInfo(name = "userName")
    String userName;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "userRole")
    String userRole;

    @ColumnInfo(name = "loginStatus")
    String loginStatus;


    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}

