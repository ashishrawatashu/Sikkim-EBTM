package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CurrentTrips")
public class CurrentTripsModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tripNo")
    int tripNo;

    @ColumnInfo(name = "strpId")
    int strpId;

    @ColumnInfo(name = "tripDirection")
    String tripDirection;

    @ColumnInfo(name = "routeId")
    int routeId;


    @ColumnInfo(name = "fromStationId")
    int fromStationId;


    @ColumnInfo(name = "toStationId")
    int toStationId;


    @ColumnInfo(name = "withWaybillYn")
    String withWaybillYn;


    @ColumnInfo(name = "completeYN")
    String completeYN;


    @ColumnInfo(name = "startDateTime")
    String startDateTime;


    @ColumnInfo(name = "completeDateTime")
    String completeDateTime;


    public int getTripNo() {
        return tripNo;
    }

    public void setTripNo(int tripNo) {
        this.tripNo = tripNo;
    }

    public int getStrpId() {
        return strpId;
    }

    public void setStrpId(int strpId) {
        this.strpId = strpId;
    }

    public String getTripDirection() {
        return tripDirection;
    }

    public void setTripDirection(String tripDirection) {
        this.tripDirection = tripDirection;
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

    public int getToStationId() {
        return toStationId;
    }

    public void setToStationId(int toStationId) {
        this.toStationId = toStationId;
    }

    public String getWithWaybillYn() {
        return withWaybillYn;
    }

    public void setWithWaybillYn(String withWaybillYn) {
        this.withWaybillYn = withWaybillYn;
    }

    public String getCompleteYN() {
        return completeYN;
    }

    public void setCompleteYN(String completeYN) {
        this.completeYN = completeYN;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getCompleteDateTime() {
        return completeDateTime;
    }

    public void setCompleteDateTime(String completeDateTime) {
        this.completeDateTime = completeDateTime;
    }
}
