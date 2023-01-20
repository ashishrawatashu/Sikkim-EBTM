package in.nic.snt.starbus.ebtm.roomDataBase.entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class DataLastDateUpdationDateModel {

    @PrimaryKey(autoGenerate = false)
    public int lastDateUpdationDateId;

    @ColumnInfo(name = "routeLastUpdate")
    public String routeLastUpdate;

    @ColumnInfo(name = "routeStationLastUpdate")
    public String routeStationLastUpdate;

    @ColumnInfo(name = "fareStationLastUpdate")
    public String fareStationLastUpdate;

    @ColumnInfo(name = "concessionLastUpdate")
    public String concessionLastUpdate;

    public int getLastDateUpdationDateId() {
        return lastDateUpdationDateId;
    }

    public void setLastDateUpdationDateId(int lastDateUpdationDateId) {
        this.lastDateUpdationDateId = lastDateUpdationDateId;
    }

    public String getRouteLastUpdate() {
        return routeLastUpdate;
    }

    public void setRouteLastUpdate(String routeLastUpdate) {
        this.routeLastUpdate = routeLastUpdate;
    }

    public String getRouteStationLastUpdate() {
        return routeStationLastUpdate;
    }

    public void setRouteStationLastUpdate(String routeStationLastUpdate) {
        this.routeStationLastUpdate = routeStationLastUpdate;
    }

    public String getFareStationLastUpdate() {
        return fareStationLastUpdate;
    }

    public void setFareStationLastUpdate(String fareStationLastUpdate) {
        this.fareStationLastUpdate = fareStationLastUpdate;
    }

    public String getConcessionLastUpdate() {
        return concessionLastUpdate;
    }

    public void setConcessionLastUpdate(String concessionLastUpdate) {
        this.concessionLastUpdate = concessionLastUpdate;
    }

}
