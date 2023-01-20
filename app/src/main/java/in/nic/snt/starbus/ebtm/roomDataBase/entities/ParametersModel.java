package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ParametersModel {

    @PrimaryKey(autoGenerate = false)
    public int depotCode;

    @ColumnInfo(name = "routeCounts")
    public String routeCounts;

    @ColumnInfo(name = "routeUpdate")
    public String routeUpdate;

    @ColumnInfo(name = "routeStationCounts")
    public String routeStationCounts;

    @ColumnInfo(name = "routeStationUpdate")
    public String routeStationUpdate;

    @ColumnInfo(name = "fareStationCounts")
    public String fareStationCounts;

    @ColumnInfo(name = "fareStationUpdate")
    public String fareStationUpdate;

    @ColumnInfo(name = "tiCounts")
    public String tiCounts;

    @ColumnInfo(name = "tiUpdate")
    public String tiUpdate;

    @ColumnInfo(name = "concessionCounts")
    public String concessionCounts;

    @ColumnInfo(name = "concessionUpdate")
    public String concessionUpdate;


//    public ParametersModel(int depotCode, String routeCounts, String routeUpdate, String routeStationCounts, String routeStationUpdate, String fareStationCounts, String fareStationUpdate, String tiCounts, String tiUpdate, String concessionCounts, String concessionUpdate) {
//        this.depotCode = depotCode;
//        this.routeCounts = routeCounts;
//        this.routeUpdate = routeUpdate;
//        this.routeStationCounts = routeStationCounts;
//        this.routeStationUpdate = routeStationUpdate;
//        this.fareStationCounts = fareStationCounts;
//        this.fareStationUpdate = fareStationUpdate;
//        this.tiCounts = tiCounts;
//        this.tiUpdate = tiUpdate;
//        this.concessionCounts = concessionCounts;
//        this.concessionUpdate = concessionUpdate;
//    }

    public int getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(int depotCode) {
        this.depotCode = depotCode;
    }

    public String getRouteCounts() {
        return routeCounts;
    }

    public void setRouteCounts(String routeCounts) {
        this.routeCounts = routeCounts;
    }

    public String getRouteUpdate() {
        return routeUpdate;
    }

    public void setRouteUpdate(String routeUpdate) {
        this.routeUpdate = routeUpdate;
    }

    public String getRouteStationCounts() {
        return routeStationCounts;
    }

    public void setRouteStationCounts(String routeStationCounts) {
        this.routeStationCounts = routeStationCounts;
    }

    public String getRouteStationUpdate() {
        return routeStationUpdate;
    }

    public void setRouteStationUpdate(String routeStationUpdate) {
        this.routeStationUpdate = routeStationUpdate;
    }

    public String getFareStationCounts() {
        return fareStationCounts;
    }

    public void setFareStationCounts(String fareStationCounts) {
        this.fareStationCounts = fareStationCounts;
    }

    public String getFareStationUpdate() {
        return fareStationUpdate;
    }

    public void setFareStationUpdate(String fareStationUpdate) {
        this.fareStationUpdate = fareStationUpdate;
    }

    public String getTiCounts() {
        return tiCounts;
    }

    public void setTiCounts(String tiCounts) {
        this.tiCounts = tiCounts;
    }

    public String getTiUpdate() {
        return tiUpdate;
    }

    public void setTiUpdate(String tiUpdate) {
        this.tiUpdate = tiUpdate;
    }

    public String getConcessionCounts() {
        return concessionCounts;
    }

    public void setConcessionCounts(String concessionCounts) {
        this.concessionCounts = concessionCounts;
    }

    public String getConcessionUpdate() {
        return concessionUpdate;
    }

    public void setConcessionUpdate(String concessionUpdate) {
        this.concessionUpdate = concessionUpdate;
    }
}
