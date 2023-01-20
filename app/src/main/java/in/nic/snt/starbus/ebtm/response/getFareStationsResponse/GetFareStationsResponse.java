
package in.nic.snt.starbus.ebtm.response.getFareStationsResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetFareStationsResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("FareStations")
    @Expose
    private List<FareStation> fareStations = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<FareStation> getFareStations() {
        return fareStations;
    }

    public void setFareStations(List<FareStation> fareStations) {
        this.fareStations = fareStations;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
