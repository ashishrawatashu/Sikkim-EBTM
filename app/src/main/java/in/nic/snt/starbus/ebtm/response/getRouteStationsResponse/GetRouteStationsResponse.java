
package in.nic.snt.starbus.ebtm.response.getRouteStationsResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class GetRouteStationsResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("RouteStations")
    @Expose
    private List<RouteStation> routeStations = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RouteStation> getRouteStations() {
        return routeStations;
    }

    public void setRouteStations(List<RouteStation> routeStations) {
        this.routeStations = routeStations;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
