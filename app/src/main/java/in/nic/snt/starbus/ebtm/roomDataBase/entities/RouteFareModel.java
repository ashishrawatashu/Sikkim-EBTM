package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class RouteFareModel {

    @PrimaryKey(autoGenerate = false)
    public int id;

    @ColumnInfo(name = "routeId")
    public Integer routeId;

    @ColumnInfo(name = "srtpId")
    public Integer srtpId;

    @ColumnInfo(name = "fromStationId")
    public Integer fromStationId;

    @ColumnInfo(name = "fromStationName")
    public String fromStationName;

    @ColumnInfo(name = "toStationId")
    public Integer toStationId;

    @ColumnInfo(name = "toStationName")
    public String toStationName;

    @ColumnInfo(name = "Distance_km")
    public Double Distance_km;

    @ColumnInfo(name = "totalFare")
    public Double totalFare;

    @ColumnInfo(name = "concessionFare")
    public Double concessionFare;

    @ColumnInfo(name = "concessionTax")
    public Double concessionTax;

    @ColumnInfo(name = "tollCharge")
    public Double tollCharge;

    @ColumnInfo(name = "parkingCharge")
    public Double parkingCharge;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getSrtpId() {
        return srtpId;
    }

    public void setSrtpId(Integer srtpId) {
        this.srtpId = srtpId;
    }

    public Integer getFromStationId() {
        return fromStationId;
    }

    public void setFromStationId(Integer fromStationId) {
        this.fromStationId = fromStationId;
    }

    public String getFromStationName() {
        return fromStationName;
    }

    public void setFromStationName(String fromStationName) {
        this.fromStationName = fromStationName;
    }

    public Integer getToStationId() {
        return toStationId;
    }

    public void setToStationId(Integer toStationId) {
        this.toStationId = toStationId;
    }

    public String getToStationName() {
        return toStationName;
    }

    public void setToStationName(String toStationName) {
        this.toStationName = toStationName;
    }

    public Double getDistance_km() {
        return Distance_km;
    }

    public void setDistance_km(Double distance_km) {
        Distance_km = distance_km;
    }

    public Double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(Double totalFare) {
        this.totalFare = totalFare;
    }

    public Double getConcessionFare() {
        return concessionFare;
    }

    public void setConcessionFare(Double concessionFare) {
        this.concessionFare = concessionFare;
    }

    public Double getConcessionTax() {
        return concessionTax;
    }

    public void setConcessionTax(Double concessionTax) {
        this.concessionTax = concessionTax;
    }

    public Double getTollCharge() {
        return tollCharge;
    }

    public void setTollCharge(Double tollCharge) {
        this.tollCharge = tollCharge;
    }

    public Double getParkingCharge() {
        return parkingCharge;
    }

    public void setParkingCharge(Double parkingCharge) {
        this.parkingCharge = parkingCharge;
    }
}
