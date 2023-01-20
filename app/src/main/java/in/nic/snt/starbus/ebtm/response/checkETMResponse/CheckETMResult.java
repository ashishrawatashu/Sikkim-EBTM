
package in.nic.snt.starbus.ebtm.response.checkETMResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckETMResult {

    @SerializedName("appActiveYN")
    @Expose
    private String appActiveYN;
    @SerializedName("versionSameYN")
    @Expose
    private String versionSameYN;
    @SerializedName("ETMRegistrationYN")
    @Expose
    private String eTMRegistrationYN;
    @SerializedName("officeId")
    @Expose
    private String officeId;
    @SerializedName("officeIdSameYN")
    @Expose
    private String officeIdSameYN;
    @SerializedName("routeUpdatedYN")
    @Expose
    private String routeUpdatedYN;
    @SerializedName("routeStationUpdatedYN")
    @Expose
    private String routeStationUpdatedYN;
    @SerializedName("fareStationUpdatedYN")
    @Expose
    private String fareStationUpdatedYN;
    @SerializedName("concessionUpdatedYN")
    @Expose
    private String concessionUpdatedYN;

    public String getAppActiveYN() {
        return appActiveYN;
    }

    public void setAppActiveYN(String appActiveYN) {
        this.appActiveYN = appActiveYN;
    }

    public String getVersionSameYN() {
        return versionSameYN;
    }

    public void setVersionSameYN(String versionSameYN) {
        this.versionSameYN = versionSameYN;
    }

    public String getETMRegistrationYN() {
        return eTMRegistrationYN;
    }

    public void setETMRegistrationYN(String eTMRegistrationYN) {
        this.eTMRegistrationYN = eTMRegistrationYN;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getOfficeIdSameYN() {
        return officeIdSameYN;
    }

    public void setOfficeIdSameYN(String officeIdSameYN) {
        this.officeIdSameYN = officeIdSameYN;
    }

    public String getRouteUpdatedYN() {
        return routeUpdatedYN;
    }

    public void setRouteUpdatedYN(String routeUpdatedYN) {
        this.routeUpdatedYN = routeUpdatedYN;
    }

    public String getRouteStationUpdatedYN() {
        return routeStationUpdatedYN;
    }

    public void setRouteStationUpdatedYN(String routeStationUpdatedYN) {
        this.routeStationUpdatedYN = routeStationUpdatedYN;
    }

    public String getFareStationUpdatedYN() {
        return fareStationUpdatedYN;
    }

    public void setFareStationUpdatedYN(String fareStationUpdatedYN) {
        this.fareStationUpdatedYN = fareStationUpdatedYN;
    }

    public String getConcessionUpdatedYN() {
        return concessionUpdatedYN;
    }

    public void setConcessionUpdatedYN(String concessionUpdatedYN) {
        this.concessionUpdatedYN = concessionUpdatedYN;
    }

}
