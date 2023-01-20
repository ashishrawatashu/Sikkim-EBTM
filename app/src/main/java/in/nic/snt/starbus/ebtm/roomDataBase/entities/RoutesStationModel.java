package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoutesStationModel {

    @PrimaryKey(autoGenerate = false)
    public int id;

    @ColumnInfo(name = "routeId")
    public int routeId;

    @ColumnInfo(name = "fromStationId")
    public int fromStationId;

    @ColumnInfo(name = "fromStationName")
    public String fromStationName;

    @ColumnInfo(name = "toStationId")
    public int toStationId;

    @ColumnInfo(name = "toStationName")
    public String toStationName;


    @ColumnInfo(name = "Station_seq")
    public int Station_seq;

    @ColumnInfo(name = "Distance_km")
    public int Distance_km;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(int fromStationId) {
        this.fromStationId = fromStationId;
    }

    public String getFromStationName() {
        return fromStationName;
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }

    public int getStation_seq() {
        return Station_seq;
    }

    public void setStation_seq(int station_seq) {
        Station_seq = station_seq;
    }

    public int getDistance_km() {
        return Distance_km;
    }

    public void setDistance_km(int distance_km) {
        Distance_km = distance_km;
    }
}
