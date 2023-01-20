
package in.nic.snt.starbus.ebtm.response.getConcessionResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Concession {

    @SerializedName("CONCESSION_ID")
    @Expose
    private Integer concessionId;
    @SerializedName("CONCESSION_NAME")
    @Expose
    private String concessionName;
    @SerializedName("CONCESSION_ABBR")
    @Expose
    private String concessionAbbr;
    @SerializedName("CONCESSION_PER_FARE")
    @Expose
    private Double concessionPerFare;
    @SerializedName("CONCESSION_PER_TAX")
    @Expose
    private Double concessionPerTax;
    @SerializedName("GENDER_YN")
    @Expose
    private String genderYn;
    @SerializedName("GENDER")
    @Expose
    private String gender;
    @SerializedName("NO_OF_KMS_YN")
    @Expose
    private String noOfKmsYn;
    @SerializedName("NO_OF_KMS")
    @Expose
    private Double noOfKms;
    @SerializedName("AGEGROUP_YN")
    @Expose
    private String agegroupYn;
    @SerializedName("MIN_AGE")
    @Expose
    private Integer minAge;
    @SerializedName("MAX_AGE")
    @Expose
    private Integer maxAge;
    @SerializedName("SERVICETYPE_YN")
    @Expose
    private String servicetypeYn;
    @SerializedName("SERVICE_TYPE")
    @Expose
    private String serviceType;
    @SerializedName("STATE_YN")
    @Expose
    private String stateYn;
    @SerializedName("WITHINSTATE_YN")
    @Expose
    private String withinstateYn;
    @SerializedName("OTHERSTATE_YN")
    @Expose
    private String otherstateYn;
    @SerializedName("ADDITIONALATTENDENT_YN")
    @Expose
    private String additionalattendentYn;
    @SerializedName("ONLINEVERIFICATION_YN")
    @Expose
    private String onlineverificationYn;
    @SerializedName("IDVERIFICATION_YN")
    @Expose
    private String idverificationYn;
    @SerializedName("IDVERIFICATION")
    @Expose
    private String idverification;
    @SerializedName("DOCUMENTVERIFICATION_YN")
    @Expose
    private String documentverificationYn;
    @SerializedName("DOCUMENTVERIFICATION")
    @Expose
    private String documentverification;
    @SerializedName("OTHERCONCESSIONYN")
    @Expose
    private String otherconcessionyn;
    @SerializedName("COUNTT")
    @Expose
    private Integer countt;
    @SerializedName("CONCESSIONLASTDATE")
    @Expose
    private String concessionlastdate;

    public Integer getConcessionId() {
        return concessionId;
    }

    public void setConcessionId(Integer concessionId) {
        this.concessionId = concessionId;
    }

    public String getConcessionName() {
        return concessionName;
    }

    public void setConcessionName(String concessionName) {
        this.concessionName = concessionName;
    }

    public String getConcessionAbbr() {
        return concessionAbbr;
    }

    public void setConcessionAbbr(String concessionAbbr) {
        this.concessionAbbr = concessionAbbr;
    }



    public String getGenderYn() {
        return genderYn;
    }

    public void setGenderYn(String genderYn) {
        this.genderYn = genderYn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNoOfKmsYn() {
        return noOfKmsYn;
    }

    public void setNoOfKmsYn(String noOfKmsYn) {
        this.noOfKmsYn = noOfKmsYn;
    }

    public Double getConcessionPerFare() {
        return concessionPerFare;
    }

    public void setConcessionPerFare(Double concessionPerFare) {
        this.concessionPerFare = concessionPerFare;
    }

    public Double getConcessionPerTax() {
        return concessionPerTax;
    }

    public void setConcessionPerTax(Double concessionPerTax) {
        this.concessionPerTax = concessionPerTax;
    }

    public Double getNoOfKms() {
        return noOfKms;
    }

    public void setNoOfKms(Double noOfKms) {
        this.noOfKms = noOfKms;
    }

    public String getAgegroupYn() {
        return agegroupYn;
    }

    public void setAgegroupYn(String agegroupYn) {
        this.agegroupYn = agegroupYn;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public String getServicetypeYn() {
        return servicetypeYn;
    }

    public void setServicetypeYn(String servicetypeYn) {
        this.servicetypeYn = servicetypeYn;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getStateYn() {
        return stateYn;
    }

    public void setStateYn(String stateYn) {
        this.stateYn = stateYn;
    }

    public String getWithinstateYn() {
        return withinstateYn;
    }

    public void setWithinstateYn(String withinstateYn) {
        this.withinstateYn = withinstateYn;
    }

    public String getOtherstateYn() {
        return otherstateYn;
    }

    public void setOtherstateYn(String otherstateYn) {
        this.otherstateYn = otherstateYn;
    }

    public String getAdditionalattendentYn() {
        return additionalattendentYn;
    }

    public void setAdditionalattendentYn(String additionalattendentYn) {
        this.additionalattendentYn = additionalattendentYn;
    }

    public String getOnlineverificationYn() {
        return onlineverificationYn;
    }

    public void setOnlineverificationYn(String onlineverificationYn) {
        this.onlineverificationYn = onlineverificationYn;
    }

    public String getIdverificationYn() {
        return idverificationYn;
    }

    public void setIdverificationYn(String idverificationYn) {
        this.idverificationYn = idverificationYn;
    }

    public String getIdverification() {
        return idverification;
    }

    public void setIdverification(String idverification) {
        this.idverification = idverification;
    }

    public String getDocumentverificationYn() {
        return documentverificationYn;
    }

    public void setDocumentverificationYn(String documentverificationYn) {
        this.documentverificationYn = documentverificationYn;
    }

    public String getDocumentverification() {
        return documentverification;
    }

    public void setDocumentverification(String documentverification) {
        this.documentverification = documentverification;
    }

    public String getOtherconcessionyn() {
        return otherconcessionyn;
    }

    public void setOtherconcessionyn(String otherconcessionyn) {
        this.otherconcessionyn = otherconcessionyn;
    }

    public Integer getCountt() {
        return countt;
    }

    public void setCountt(Integer countt) {
        this.countt = countt;
    }

    public String getConcessionlastdate() {
        return concessionlastdate;
    }

    public void setConcessionlastdate(String concessionlastdate) {
        this.concessionlastdate = concessionlastdate;
    }

}
