
package in.nic.snt.starbus.ebtm.response.getFareStationsResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FareStation {

    @SerializedName("ROUT_ID")
    @Expose
    private Integer routId;
    @SerializedName("SRTP_ID")
    @Expose
    private Integer srtpId;
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
    @SerializedName("TOTAL_DIST_KM")
    @Expose
    private Double totalDistKm;
    @SerializedName("FINAL_FARE")
    @Expose
    private Double finalFare;
    @SerializedName("CONCESSION_FARE")
    @Expose
    private Double concessionFare;
    @SerializedName("CONCESSION_TAX")
    @Expose
    private Double concessionTax;
    @SerializedName("TOLL_CHARGES")
    @Expose
    private Double tollCharges;
    @SerializedName("PARKING_CHARGES")
    @Expose
    private Double parkingCharges;
    @SerializedName("COUNTT")
    @Expose
    private Integer countt;
    @SerializedName("FARESTATIONLASTDATE")
    @Expose
    private String farestationlastdate;

    public Integer getRoutId() {
        return routId;
    }

    public void setRoutId(Integer routId) {
        this.routId = routId;
    }

    public Integer getSrtpId() {
        return srtpId;
    }

    public void setSrtpId(Integer srtpId) {
        this.srtpId = srtpId;
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

    public Double getTotalDistKm() {
        return totalDistKm;
    }

    public void setTotalDistKm(Double totalDistKm) {
        this.totalDistKm = totalDistKm;
    }

    public Double getFinalFare() {
        return finalFare;
    }

    public void setFinalFare(Double finalFare) {
        this.finalFare = finalFare;
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

    public Double getTollCharges() {
        return tollCharges;
    }

    public void setTollCharges(Double tollCharges) {
        this.tollCharges = tollCharges;
    }

    public Double getParkingCharges() {
        return parkingCharges;
    }

    public void setParkingCharges(Double parkingCharges) {
        this.parkingCharges = parkingCharges;
    }

    public Integer getCountt() {
        return countt;
    }

    public void setCountt(Integer countt) {
        this.countt = countt;
    }

    public String getFarestationlastdate() {
        return farestationlastdate;
    }

    public void setFarestationlastdate(String farestationlastdate) {
        this.farestationlastdate = farestationlastdate;
    }

}
