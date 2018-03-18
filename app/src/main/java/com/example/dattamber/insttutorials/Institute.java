package com.example.dattamber.insttutorials;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VeeraReddyKonabhai on 9/5/2017.
 */

public class Institute {

    private Long iid;
    private String iName;
    private Long iphnumber;
    private String iarea;
    private String iaddress;
    private String iemail;
    private String ipwd;
    private String icourse1,icourse2,icourse3,icourse4;
    private String iaadhar;
    private String iaddprf;

    public String getIcourse1() {
        return icourse1;
    }

    public void setIcourse1(String icourse1) {
        this.icourse1 = icourse1;
    }

    public String getIcourse2() {
        return icourse2;
    }

    public void setIcourse2(String icourse2) {
        this.icourse2 = icourse2;
    }

    public String getIcourse3() {
        return icourse3;
    }

    public void setIcourse3(String icourse3) {
        this.icourse3 = icourse3;
    }

    public String getIcourse4() {
        return icourse4;
    }

    public void setIcourse4(String icourse4) {
        this.icourse4 = icourse4;
    }



    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getIaadhar() {
        return iaadhar;
    }

    public void setIaadhar(String iaadhar) {
        this.iaadhar = iaadhar;
    }

    public String getIaddprf() {
        return iaddprf;
    }

    public void setIaddprf(String iaddprf) {
        this.iaddprf = iaddprf;
    }



    public Institute(Long id,String iName,Long iphnumber,String iarea,String iaddress,String iemail,String icourse1,String icourse2,String icourse3,String icourse4,String iaadhar,String iaddprf){
        this.iid=id;
        this.iName=iName;
        this.iphnumber=iphnumber;
        this.iarea=iarea;
        this.iaddress=iaddress;
        this.iemail=iemail;
      //  this.ipwd=ipwd;
        this.icourse1=icourse1;
        this.icourse2=icourse2;
        this.icourse3=icourse3;
        this.icourse4=icourse4;
        this.iaadhar=iaadhar;
        this.iaddprf=iaddprf;
    }
    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public Long getiphnumber() {
        return iphnumber;
    }

    public void setiphnumber(Long iphnumber) {
        this.iphnumber = iphnumber;
    }

    public String getIarea() {
        return iarea;
    }

    public void setIarea(String iarea) {
        this.iarea = iarea;
    }

    public String getIaddress() {
        return iaddress;
    }

    public void setIaddress(String iaddress) {
        this.iaddress = iaddress;
    }

    public String getIemail() {
        return iemail;
    }

    public void setIemail(String iemail) {
        this.iemail = iemail;
    }

    public String getIpwd() {
        return ipwd;
    }

    public void setIpwd(String ipwd) {
        this.ipwd = ipwd;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("iid",iid);
        result.put("iName", iName);
        result.put("iphnumber",iphnumber);
        result.put("iarea", iarea);
        result.put("iaddress",iaddress);
        result.put("iemail",iemail);
        result.put("icourse1",icourse1);
        result.put("icourse2",icourse2);
        result.put("icourse3",icourse3);
        result.put("icourse4",icourse4);
        result.put("iaadhar",iaadhar);
        result.put("iaddprf",iaddprf);
        return result;
    }

Institute(){

}


}
