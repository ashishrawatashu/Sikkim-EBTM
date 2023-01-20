
package in.nic.snt.starbus.ebtm.response.getRouteStationsResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RouteStation {

    @SerializedName("ROUT_ID")
    @Expose
    private Integer routId;
    @SerializedName("FR_STON_ID")
    @Expose
    private Integer frStonId;
    @SerializedName("FR_STON_NAME")
    @Expose
    private String frStonName;
    @SerializedName("TO_STON_ID")
    @Expose
    private Integer toStonId;
    @SerializedName("TO_STON_NAME")
    @Expose
    private String toStonName;
    @SerializedName("STATION_SEQ")
    @Expose
    private Integer stationSeq;
    @SerializedName("COUNTT")
    @Expose
    private Integer countt;
    @SerializedName("ROUTESTATIONLASTDATE")
    @Expose
    private String routestationlastdate;

    public Integer getRoutId() {
        return routId;
    }

    public void setRoutId(Integer routId) {
        this.routId = routId;
    }

    public Integer getFrStonId() {
        return frStonId;
    }

    public void setFrStonId(Integer frStonId) {
        this.frStonId = frStonId;
    }

    public String getFrStonName() {
        return frStonName;
    }

    public void setFrStonName(String frStonName) {
        this.frStonName = frStonName;
    }

    public Integer getToStonId() {
        return toStonId;
    }

    public void setToStonId(Integer toStonId) {
        this.toStonId = toStonId;
    }

    public String getToStonName() {
        return toStonName;
    }

    public void setToStonName(String toStonName) {
        this.toStonName = toStonName;
    }

    public Integer getStationSeq() {
        return stationSeq;
    }

    public void setStationSeq(Integer stationSeq) {
        this.stationSeq = stationSeq;
    }

    public Integer getCountt() {
        return countt;
    }

    public void setCountt(Integer countt) {
        this.countt = countt;
    }

    public String getRoutestationlastdate() {
        return routestationlastdate;
    }

    public void setRoutestationlastdate(String routestationlastdate) {
        this.routestationlastdate = routestationlastdate;
    }

}
