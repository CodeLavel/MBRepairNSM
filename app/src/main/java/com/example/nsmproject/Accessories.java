package com.example.nsmproject;

public class Accessories {
    private String ac_id,ac_name,ac_zone,ac_status;

    public Accessories() {
    }

    public Accessories(String ac_id, String ac_name, String ac_zone, String ac_status) {
        this.ac_id = ac_id;
        this.ac_name = ac_name;
        this.ac_zone = ac_zone;
        this.ac_status = ac_status;
    }

    public String getAc_id() {
        return ac_id;
    }

    public void setAc_id(String ac_id) {
        this.ac_id = ac_id;
    }

    public String getAc_name() {
        return ac_name;
    }

    public void setAc_name(String ac_name) {
        this.ac_name = ac_name;
    }

    public String getAc_zone() {
        return ac_zone;
    }

    public void setAc_zone(String ac_zone) {
        this.ac_zone = ac_zone;
    }

    public String getAc_status() {
        return ac_status;
    }

    public void setAc_status(String ac_status) {
        this.ac_status = ac_status;
    }
}
