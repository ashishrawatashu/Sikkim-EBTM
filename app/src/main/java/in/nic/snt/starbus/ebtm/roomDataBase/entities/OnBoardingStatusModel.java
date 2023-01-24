package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "OnBoardingStatus")
public class OnBoardingStatusModel {


    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "userType")
    String userType;


    @ColumnInfo(name = "loginStatus")
    String loginStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
}
