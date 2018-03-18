package com.example.dattamber.insttutorials;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by VeeraReddyKonabhai on 9/5/2017.
 */

public class Tutuor {
    public String tname;
    public long tid;
    public long tphno;
    public String tarea;
    public String tadd;
    public String temail;
    public String taadhar;
    public String tcerti;
    public Map<String,String> tsubcourse;

    public Tutuor(String tname, long tid, long tphno, String tarea, String tadd, String temail, String taadhar, String tcerti, Map<String, String> tsubcourse) {
        this.tname = tname;
        this.tid = tid;
        this.tphno = tphno;
        this.tarea = tarea;
        this.tadd = tadd;
        this.temail = temail;
        this.taadhar = taadhar;
        this.tcerti = tcerti;
        this.tsubcourse = tsubcourse;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public long getTphno() {
        return tphno;
    }

    public void setTphno(long tphno) {
        this.tphno = tphno;
    }

    public String getTcerti() {
        return tcerti;
    }

    public void setTcerti(String tcerti) {
        this.tcerti = tcerti;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getTadd() {
        return tadd;
    }

    public void setTadd(String tadd) {
        this.tadd = tadd;
    }

    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail;
    }

    public String getTaadhar() {
        return taadhar;
    }

    public void setTaadhar(String taadhar) {
        this.taadhar = taadhar;
    }

    public Map<String, String> getTsubcourse() {
        return tsubcourse;
    }

    public void setTsubcourse(Map<String, String> tsubcourse) {
        this.tsubcourse = tsubcourse;
    }
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("tid", tid);
        result.put("tname", tname);
        result.put("tarea", tarea);
        result.put("tadd",tadd);
        result.put("taadhar",taadhar);
        result.put("temail",temail);
        result.put("tsubcourse",tsubcourse);
        result.put("tcerti",tcerti);
        result.put("tphno",tphno);
        return result;
    }

    Tutuor(){

    }
}
