package in.nic.snt.starbus.ebtm.roomDataBase.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ConcessionsModel {

    @PrimaryKey(autoGenerate = false)
    public int conId;

    @ColumnInfo(name = "conName")
    public String conName;

    @ColumnInfo(name = "conAbbr")
    public String conAbbr;

    @ColumnInfo(name = "concession_per_fare")
    public Double concession_per_fare;

    @ColumnInfo(name = "concession_per_tax")
    public Double concession_per_tax;


    @ColumnInfo(name = "gender_yn")
    public String gender_yn;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "no_of_kms_yn")
    public String no_of_kms_yn;

   @ColumnInfo(name = "no_of_kms")
    public Double no_of_kms;

   @ColumnInfo(name = "agegroup_yn")
    public String agegroup_yn;

   @ColumnInfo(name = "min_age")
    public Integer min_age;

   @ColumnInfo(name = "max_age")
    public Integer max_age;

   @ColumnInfo(name = "servicetype_yn")
    public String servicetype_yn;

   @ColumnInfo(name = "service_type")
    public String service_type;

   @ColumnInfo(name = "state_yn")
    public String state_yn;

   @ColumnInfo(name = "withinstate_yn")
    public String withinstate_yn;

   @ColumnInfo(name = "otherstate_yn")
    public String otherstate_yn;

   @ColumnInfo(name = "additionalattendent_yn")
    public String additionalattendent_yn;

   @ColumnInfo(name = "onlineverification_yn")
    public String onlineverification_yn;

   @ColumnInfo(name = "idverification_yn")
    public String idverification_yn;


   @ColumnInfo(name = "idverification")
    public String idverification;


   @ColumnInfo(name = "documentverification_yn")
    public String documentverification_yn;

   @ColumnInfo(name = "documentverification")
    public String documentverification;


   @ColumnInfo(name = "otherconcessionyn")
    public String otherconcessionyn;


    public ConcessionsModel(int conId, String conName, String conAbbr, Double concession_per_fare, Double concession_per_tax, String gender_yn, String gender, String no_of_kms_yn, Double no_of_kms, String agegroup_yn, Integer min_age, Integer max_age, String servicetype_yn, String service_type, String state_yn, String withinstate_yn, String otherstate_yn, String additionalattendent_yn, String onlineverification_yn, String idverification_yn, String idverification, String documentverification_yn, String documentverification, String otherconcessionyn) {
        this.conId = conId;
        this.conName = conName;
        this.conAbbr = conAbbr;
        this.concession_per_fare = concession_per_fare;
        this.concession_per_tax = concession_per_tax;
        this.gender_yn = gender_yn;
        this.gender = gender;
        this.no_of_kms_yn = no_of_kms_yn;
        this.no_of_kms = no_of_kms;
        this.agegroup_yn = agegroup_yn;
        this.min_age = min_age;
        this.max_age = max_age;
        this.servicetype_yn = servicetype_yn;
        this.service_type = service_type;
        this.state_yn = state_yn;
        this.withinstate_yn = withinstate_yn;
        this.otherstate_yn = otherstate_yn;
        this.additionalattendent_yn = additionalattendent_yn;
        this.onlineverification_yn = onlineverification_yn;
        this.idverification_yn = idverification_yn;
        this.idverification = idverification;
        this.documentverification_yn = documentverification_yn;
        this.documentverification = documentverification;
        this.otherconcessionyn = otherconcessionyn;
    }


    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConAbbr() {
        return conAbbr;
    }

    public void setConAbbr(String conAbbr) {
        this.conAbbr = conAbbr;
    }

    public Double getConcession_per_fare() {
        return concession_per_fare;
    }

    public void setConcession_per_fare(Double concession_per_fare) {
        this.concession_per_fare = concession_per_fare;
    }

    public Double getConcession_per_tax() {
        return concession_per_tax;
    }

    public void setConcession_per_tax(Double concession_per_tax) {
        this.concession_per_tax = concession_per_tax;
    }

    public String getGender_yn() {
        return gender_yn;
    }

    public void setGender_yn(String gender_yn) {
        this.gender_yn = gender_yn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNo_of_kms_yn() {
        return no_of_kms_yn;
    }

    public void setNo_of_kms_yn(String no_of_kms_yn) {
        this.no_of_kms_yn = no_of_kms_yn;
    }

    public Double getNo_of_kms() {
        return no_of_kms;
    }

    public void setNo_of_kms(Double no_of_kms) {
        this.no_of_kms = no_of_kms;
    }

    public String getAgegroup_yn() {
        return agegroup_yn;
    }

    public void setAgegroup_yn(String agegroup_yn) {
        this.agegroup_yn = agegroup_yn;
    }

    public Integer getMin_age() {
        return min_age;
    }

    public void setMin_age(Integer min_age) {
        this.min_age = min_age;
    }

    public Integer getMax_age() {
        return max_age;
    }

    public void setMax_age(Integer max_age) {
        this.max_age = max_age;
    }

    public String getServicetype_yn() {
        return servicetype_yn;
    }

    public void setServicetype_yn(String servicetype_yn) {
        this.servicetype_yn = servicetype_yn;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getState_yn() {
        return state_yn;
    }

    public void setState_yn(String state_yn) {
        this.state_yn = state_yn;
    }

    public String getWithinstate_yn() {
        return withinstate_yn;
    }

    public void setWithinstate_yn(String withinstate_yn) {
        this.withinstate_yn = withinstate_yn;
    }

    public String getOtherstate_yn() {
        return otherstate_yn;
    }

    public void setOtherstate_yn(String otherstate_yn) {
        this.otherstate_yn = otherstate_yn;
    }

    public String getAdditionalattendent_yn() {
        return additionalattendent_yn;
    }

    public void setAdditionalattendent_yn(String additionalattendent_yn) {
        this.additionalattendent_yn = additionalattendent_yn;
    }

    public String getOnlineverification_yn() {
        return onlineverification_yn;
    }

    public void setOnlineverification_yn(String onlineverification_yn) {
        this.onlineverification_yn = onlineverification_yn;
    }

    public String getIdverification_yn() {
        return idverification_yn;
    }

    public void setIdverification_yn(String idverification_yn) {
        this.idverification_yn = idverification_yn;
    }

    public String getIdverification() {
        return idverification;
    }

    public void setIdverification(String idverification) {
        this.idverification = idverification;
    }

    public String getDocumentverification_yn() {
        return documentverification_yn;
    }

    public void setDocumentverification_yn(String documentverification_yn) {
        this.documentverification_yn = documentverification_yn;
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
}
