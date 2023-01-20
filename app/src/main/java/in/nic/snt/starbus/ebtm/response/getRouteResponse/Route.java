
package in.nic.snt.starbus.ebtm.response.getRouteResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("ROUT_ID")
    @Expose
    private Integer routId;
    @SerializedName("ROUTE_NAME_EN")
    @Expose
    private String routeNameEn;
    @SerializedName("COUNTT")
    @Expose
    private Integer countt;
    @SerializedName("ROUTELASTDATE")
    @Expose
    private String routelastdate;

    public Integer getRoutId() {
        return routId;
    }

    public void setRoutId(Integer routId) {
        this.routId = routId;
    }

    public String getRouteNameEn() {
        return routeNameEn;
    }

    public void setRouteNameEn(String routeNameEn) {
        this.routeNameEn = routeNameEn;
    }

    public Integer getCountt() {
        return countt;
    }

    public void setCountt(Integer countt) {
        this.countt = countt;
    }

    public String getRoutelastdate() {
        return routelastdate;
    }

    public void setRoutelastdate(String routelastdate) {
        this.routelastdate = routelastdate;
    }

}
