package com.example.dattamber.insttutorials;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VeeraReddyKonabhai on 9/5/2017.
 */

public class student {
    public Long si;
    public String sName;
    public Long scontact;
    public String sArea;
    public String sAddress;
    public String sEmail;
    public String sEdu;
    public String sCourse;

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    private String stype;

    public Long getScontact() {
        return scontact;
    }

    public void setScontact(Long scontact) {
        this.scontact = scontact;
    }

    public String getsArea() {
        return sArea;
    }

    public void setsArea(String sArea) {
        this.sArea = sArea;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsEdu() {
        return sEdu;
    }

    public void setsEdu(String sEdu) {
        this.sEdu = sEdu;
    }

    public String getsCourse() {
        return sCourse;
    }

    public void setsCourse(String sCourse) {
        this.sCourse = sCourse;
    }

    public String getsName() {

        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    student(Long si,String sName, Long scontact, String sArea, String sAddress, String sEmail, String sEdu, String sCourse)
    {
        this.si=si;
        this.sName=sName;
        this.scontact=scontact;
        this.sArea=sArea;
        this.sAddress=sAddress;
        this.sEmail=sEmail;
        this.sEdu=sEdu;
        this.sCourse=sCourse;
    }

    student()
    {

    }

    public Long getSi() {
        return si;
    }

    public void setSi(Long si) {
        this.si = si;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("si",si);

        result.put("sName", sName);
        result.put("scontact", scontact);
        result.put("sArea", sArea);
        result.put("sAddress",sAddress);
        result.put("sEmail",sEmail);
        result.put("sEdu",sEdu);
        result.put("sCourse",sCourse);
        return result;
    }
}
