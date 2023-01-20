
package in.nic.snt.starbus.ebtm.response.getRouteResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRouteResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("Route")
    @Expose
    private List<Route> route = null;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
