
package in.nic.snt.starbus.ebtm.response.getWayBillDetailsResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConductorDriverTiModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.WaybillModel;

public class GetWaybillDetailsResponse {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("Waybill")
    @Expose
    private List<WaybillModel> waybillModel = null;

    @SerializedName("Conductor1")
    @Expose
    private List<ConductorDriverTiModel> conductor = null;

    @SerializedName("Conductor2")
    @Expose
    private List<ConductorDriverTiModel> conductor2 = null;

    @SerializedName("Driver1")
    @Expose
    private List<ConductorDriverTiModel> driver = null;

    @SerializedName("Driver2")
    @Expose
    private List<ConductorDriverTiModel> driver2 = null;

    @SerializedName("TIs")
    @Expose
    private List<ConductorDriverTiModel> tIs = null;

    @SerializedName("Trips")
    @Expose
    private List<TripsModel> trips = null;

    @SerializedName("msg")
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<WaybillModel> getWaybill() {
        return waybillModel;
    }

    public void setWaybill(List<WaybillModel> waybillModel) {
        this.waybillModel = waybillModel;
    }

    public List<ConductorDriverTiModel> getConductor() {
        return conductor;
    }

    public void setConductor(List<ConductorDriverTiModel> conductor) {
        this.conductor = conductor;
    }

    public List<ConductorDriverTiModel> getConductor2() {
        return conductor2;
    }

    public void setConductor2(List<ConductorDriverTiModel> conductor2) {
        this.conductor2 = conductor2;
    }

    public List<ConductorDriverTiModel> getDriver() {
        return driver;
    }

    public void setDriver(List<ConductorDriverTiModel> driver) {
        this.driver = driver;
    }

    public List<ConductorDriverTiModel> getDriver2() {
        return driver2;
    }

    public void setDriver2(List<ConductorDriverTiModel> driver2) {
        this.driver2 = driver2;
    }

    public List<ConductorDriverTiModel> gettIs() {
        return tIs;
    }

    public void settIs(List<ConductorDriverTiModel> tIs) {
        this.tIs = tIs;
    }

    public List<TripsModel> getTrips() {
        return trips;
    }

    public void setTrips(List<TripsModel> trips) {
        this.trips = trips;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
