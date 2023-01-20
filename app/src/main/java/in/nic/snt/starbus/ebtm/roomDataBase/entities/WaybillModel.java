
package in.nic.snt.starbus.ebtm.roomDataBase.entities;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class WaybillModel {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "waybillId")
    @SerializedName("WAYBILLID")
    @Expose
    private String waybillid;

    @ColumnInfo(name = "updatedBy")
    @SerializedName("UPDATEDBY")
    @Expose
    private String updatedby;

    @ColumnInfo(name = "departureTime")
    @SerializedName("DEPARTURETIME")
    @Expose
    private String departuretime;

    @ColumnInfo(name = "dsvcId")
    @SerializedName("DSVC_ID")
    @Expose
    private Integer dsvcId;

    @ColumnInfo(name = "deprtDate")
    @SerializedName("DEPRTDATE")
    @Expose
    private String deprtdate;

    @ColumnInfo(name = "deprtTime")
    @SerializedName("DEPRTTIME")
    @Expose
    private String deprttime;

    @ColumnInfo(name = "updationDate")
    @SerializedName("UPDATIONDATE")
    @Expose
    private String updationdate;

    @ColumnInfo(name = "dutyDate")
    @SerializedName("DUTYDATE")
    @Expose
    private String dutydate;

    @ColumnInfo(name = "serviceId")
    @SerializedName("SERVICEID")
    @Expose
    private Integer serviceid;

    @ColumnInfo(name = "depot")
    @SerializedName("DEPOT")
    @Expose
    private String depot;

    @ColumnInfo(name = "route")
    @SerializedName("ROUTE")
    @Expose
    private String route;

    @ColumnInfo(name = "busNo")
    @SerializedName("BUSNO")
    @Expose
    private String busno;

    @ColumnInfo(name = "odometerReading")
    @SerializedName("ODOMETERREADING")
    @Expose
    private String odometerreading;

    @ColumnInfo(name = "insuranceNo")
    @SerializedName("INSURANCENO")
    @Expose
    private String insuranceno;

    @ColumnInfo(name = "insuranceValidity")
    @SerializedName("INSURANCEVALIDITY")
    @Expose
    private String insurancevalidity;

    @ColumnInfo(name = "pollutionCertificateNo")
    @SerializedName("POLLUTIONCERTIFICATENO")
    @Expose
    private String pollutioncertificateno;

    @ColumnInfo(name = "pollutionValidity")
    @SerializedName("POLLUTIONVALIDITY")
    @Expose
    private String pollutionvalidity;

    @ColumnInfo(name = "fitnessCertificateNo")
    @SerializedName("FITNESSCERTIFICATENO")
    @Expose
    private String fitnesscertificateno;

    @ColumnInfo(name = "fitnessValidity")
    @SerializedName("FITNESSVALIDITY")
    @Expose
    private String fitnessvalidity;

    @ColumnInfo(name = "driver1EmpCode")
    @SerializedName("DRIVER1EMPCODE")
    @Expose
    private String driver1empcode;

    @ColumnInfo(name = "driver1Name")
    @SerializedName("DRIVER1NAME")
    @Expose
    private String driver1name;

    @ColumnInfo(name = "driver1LicenseNo")
    @SerializedName("DRIVER1LICENSENO")
    @Expose
    private String driver1licenseno;

    @ColumnInfo(name = "driver1LicenseValidity")
    @SerializedName("DRIVER1LICENSEVALIDITY")
    @Expose
    private String driver1licensevalidity;

    @ColumnInfo(name = "driver2EmpCode")
    @SerializedName("DRIVER2EMPCODE")
    @Expose
    private String driver2empcode;

    @ColumnInfo(name = "driver2Name")
    @SerializedName("DRIVER2NAME")
    @Expose
    private String driver2name;

    @ColumnInfo(name = "driver2LicenseNo")
    @SerializedName("DRIVER2LICENSENO")
    @Expose
    private String driver2licenseno;

    @ColumnInfo(name = "driver2LicenseValidity")
    @SerializedName("DRIVER2LICENSEVALIDITY")
    @Expose
    private String driver2licensevalidity;

    @ColumnInfo(name = "conductor1EmpCode")
    @SerializedName("CONDUCTOR1EMPCODE")
    @Expose
    private String conductor1empcode;

    @ColumnInfo(name = "conductor1Name")
    @SerializedName("CONDUCTOR1NAME")
    @Expose
    private String conductor1name;

    @ColumnInfo(name = "conductor1LicenseNo")
    @SerializedName("CONDUCTOR1LICENSENO")
    @Expose
    private String conductor1licenseno;

    @ColumnInfo(name = "cond1LicenseValidity")
    @SerializedName("COND1LICENSEVALIDITY")
    @Expose
    private String cond1licensevalidity;

    @ColumnInfo(name = "conductor2EmpCode")
    @SerializedName("CONDUCTOR2EMPCODE")
    @Expose
    private String conductor2empcode;

    @ColumnInfo(name = "conductor2Name")
    @SerializedName("CONDUCTOR2NAME")
    @Expose
    private String conductor2name;

    @ColumnInfo(name = "conductor2LicenseNo")
    @SerializedName("CONDUCTOR2LICENSENO")
    @Expose
    private String conductor2licenseno;

    @ColumnInfo(name = "lastOdometerReading")
    @SerializedName("LASTODOMETERREADING")
    @Expose
    private Integer lastodometerreading;

    @ColumnInfo(name = "cond2LicenseValidity")
    @SerializedName("COND2LICENSEVALIDITY")
    @Expose
    private String cond2licensevalidity;

    @ColumnInfo(name = "targetIncome")
    @SerializedName("TARGETINCOME")
    @Expose
    private String targetincome;

    @ColumnInfo(name = "targetDieselAverage")
    @SerializedName("TARGETDIESELAVERAGE")
    @Expose
    private String targetdieselaverage;

    @ColumnInfo(name = "scheduleKM")
    @SerializedName("SCHEDULEKM")
    @Expose
    private String schedulekm;

    @ColumnInfo(name = "changeStation")
    @SerializedName("CHANGESTATION")
    @Expose
    private String changestation;

    @ColumnInfo(name = "serviceName")
    @SerializedName("SERVICENAME")
    @Expose
    private String servicename;

    @ColumnInfo(name = "permitNo")
    @SerializedName("PERMITNO")
    @Expose
    private String permitno;

    @ColumnInfo(name = "permitValidity")
    @SerializedName("PERMITVALIDITY")
    @Expose
    private String permitvalidity;

    @ColumnInfo(name = "dutySlipDateTime")
    @SerializedName("DUTYSLIPDATETIME")
    @Expose
    private String dutyslipdatetime;

    @ColumnInfo(name = "dutySlipGeneratedBy")
    @SerializedName("DUTYSLIPGENERATEDBY")
    @Expose
    private String dutyslipgeneratedby;

    @ColumnInfo(name = "routId")
    @SerializedName("ROUT_ID")
    @Expose
    private Integer routId;

    @ColumnInfo(name = "bustTypeId")
    @SerializedName("BUSTYPEID")
    @Expose
    private String bustypeid;

    @ColumnInfo(name = "busType")
    @SerializedName("BUSTYPE")
    @Expose
    private String bustype;

    @ColumnInfo(name = "serviceTypeId")
    @SerializedName("SERVICETYPEID")
    @Expose
    private Integer servicetypeid;

    @ColumnInfo(name = "serviceType")
    @SerializedName("SERVICETYPE")
    @Expose
    private String servicetype;

    @ColumnInfo(name = "officeName")
    @SerializedName("OFFICENAME")
    @Expose
    private String officename;

    public String getWaybillid() {
        return waybillid;
    }

    public void setWaybillid(String waybillid) {
        this.waybillid = waybillid;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public Integer getDsvcId() {
        return dsvcId;
    }

    public void setDsvcId(Integer dsvcId) {
        this.dsvcId = dsvcId;
    }

    public String getDeprtdate() {
        return deprtdate;
    }

    public void setDeprtdate(String deprtdate) {
        this.deprtdate = deprtdate;
    }

    public String getDeprttime() {
        return deprttime;
    }

    public void setDeprttime(String deprttime) {
        this.deprttime = deprttime;
    }

    public String getUpdationdate() {
        return updationdate;
    }

    public void setUpdationdate(String updationdate) {
        this.updationdate = updationdate;
    }

    public String getDutydate() {
        return dutydate;
    }

    public void setDutydate(String dutydate) {
        this.dutydate = dutydate;
    }

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public String getOdometerreading() {
        return odometerreading;
    }

    public void setOdometerreading(String odometerreading) {
        this.odometerreading = odometerreading;
    }

    public String getInsuranceno() {
        return insuranceno;
    }

    public void setInsuranceno(String insuranceno) {
        this.insuranceno = insuranceno;
    }

    public String getInsurancevalidity() {
        return insurancevalidity;
    }

    public void setInsurancevalidity(String insurancevalidity) {
        this.insurancevalidity = insurancevalidity;
    }

    public String getPollutioncertificateno() {
        return pollutioncertificateno;
    }

    public void setPollutioncertificateno(String pollutioncertificateno) {
        this.pollutioncertificateno = pollutioncertificateno;
    }

    public String getPollutionvalidity() {
        return pollutionvalidity;
    }

    public void setPollutionvalidity(String pollutionvalidity) {
        this.pollutionvalidity = pollutionvalidity;
    }

    public String getFitnesscertificateno() {
        return fitnesscertificateno;
    }

    public void setFitnesscertificateno(String fitnesscertificateno) {
        this.fitnesscertificateno = fitnesscertificateno;
    }

    public String getFitnessvalidity() {
        return fitnessvalidity;
    }

    public void setFitnessvalidity(String fitnessvalidity) {
        this.fitnessvalidity = fitnessvalidity;
    }

    public String getDriver1empcode() {
        return driver1empcode;
    }

    public void setDriver1empcode(String driver1empcode) {
        this.driver1empcode = driver1empcode;
    }

    public String getDriver1name() {
        return driver1name;
    }

    public void setDriver1name(String driver1name) {
        this.driver1name = driver1name;
    }

    public String getDriver1licenseno() {
        return driver1licenseno;
    }

    public void setDriver1licenseno(String driver1licenseno) {
        this.driver1licenseno = driver1licenseno;
    }

    public String getDriver1licensevalidity() {
        return driver1licensevalidity;
    }

    public void setDriver1licensevalidity(String driver1licensevalidity) {
        this.driver1licensevalidity = driver1licensevalidity;
    }

    public String getDriver2empcode() {
        return driver2empcode;
    }

    public void setDriver2empcode(String driver2empcode) {
        this.driver2empcode = driver2empcode;
    }

    public String getDriver2name() {
        return driver2name;
    }

    public void setDriver2name(String driver2name) {
        this.driver2name = driver2name;
    }

    public String getDriver2licenseno() {
        return driver2licenseno;
    }

    public void setDriver2licenseno(String driver2licenseno) {
        this.driver2licenseno = driver2licenseno;
    }

    public String getDriver2licensevalidity() {
        return driver2licensevalidity;
    }

    public void setDriver2licensevalidity(String driver2licensevalidity) {
        this.driver2licensevalidity = driver2licensevalidity;
    }

    public String getConductor1empcode() {
        return conductor1empcode;
    }

    public void setConductor1empcode(String conductor1empcode) {
        this.conductor1empcode = conductor1empcode;
    }

    public String getConductor1name() {
        return conductor1name;
    }

    public void setConductor1name(String conductor1name) {
        this.conductor1name = conductor1name;
    }

    public String getConductor1licenseno() {
        return conductor1licenseno;
    }

    public void setConductor1licenseno(String conductor1licenseno) {
        this.conductor1licenseno = conductor1licenseno;
    }

    public String getCond1licensevalidity() {
        return cond1licensevalidity;
    }

    public void setCond1licensevalidity(String cond1licensevalidity) {
        this.cond1licensevalidity = cond1licensevalidity;
    }

    public String getConductor2empcode() {
        return conductor2empcode;
    }

    public void setConductor2empcode(String conductor2empcode) {
        this.conductor2empcode = conductor2empcode;
    }

    public String getConductor2name() {
        return conductor2name;
    }

    public void setConductor2name(String conductor2name) {
        this.conductor2name = conductor2name;
    }

    public String getConductor2licenseno() {
        return conductor2licenseno;
    }

    public void setConductor2licenseno(String conductor2licenseno) {
        this.conductor2licenseno = conductor2licenseno;
    }

    public Integer getLastodometerreading() {
        return lastodometerreading;
    }

    public void setLastodometerreading(Integer lastodometerreading) {
        this.lastodometerreading = lastodometerreading;
    }

    public String getCond2licensevalidity() {
        return cond2licensevalidity;
    }

    public void setCond2licensevalidity(String cond2licensevalidity) {
        this.cond2licensevalidity = cond2licensevalidity;
    }

    public String getTargetincome() {
        return targetincome;
    }

    public void setTargetincome(String targetincome) {
        this.targetincome = targetincome;
    }

    public String getTargetdieselaverage() {
        return targetdieselaverage;
    }

    public void setTargetdieselaverage(String targetdieselaverage) {
        this.targetdieselaverage = targetdieselaverage;
    }

    public String getSchedulekm() {
        return schedulekm;
    }

    public void setSchedulekm(String schedulekm) {
        this.schedulekm = schedulekm;
    }

    public String getChangestation() {
        return changestation;
    }

    public void setChangestation(String changestation) {
        this.changestation = changestation;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getPermitno() {
        return permitno;
    }

    public void setPermitno(String permitno) {
        this.permitno = permitno;
    }

    public String getPermitvalidity() {
        return permitvalidity;
    }

    public void setPermitvalidity(String permitvalidity) {
        this.permitvalidity = permitvalidity;
    }

    public String getDutyslipdatetime() {
        return dutyslipdatetime;
    }

    public void setDutyslipdatetime(String dutyslipdatetime) {
        this.dutyslipdatetime = dutyslipdatetime;
    }

    public String getDutyslipgeneratedby() {
        return dutyslipgeneratedby;
    }

    public void setDutyslipgeneratedby(String dutyslipgeneratedby) {
        this.dutyslipgeneratedby = dutyslipgeneratedby;
    }

    public Integer getRoutId() {
        return routId;
    }

    public void setRoutId(Integer routId) {
        this.routId = routId;
    }

    public String getBustypeid() {
        return bustypeid;
    }

    public void setBustypeid(String bustypeid) {
        this.bustypeid = bustypeid;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public Integer getServicetypeid() {
        return servicetypeid;
    }

    public void setServicetypeid(Integer servicetypeid) {
        this.servicetypeid = servicetypeid;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    public String getOfficename() {
        return officename;
    }

    public void setOfficename(String officename) {
        this.officename = officename;
    }

}
