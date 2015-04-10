/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean(name="saveAppointments")
@ViewScoped
public class SaveAppointments implements Serializable{

    private String city;
    String district;
    private String hospital;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        System.out.println("Giden İlçe"+district);
        return district;
    }

    public void setDistrict(String district) {
        System.out.println("Gelen İlçe"+district);
        this.district = district;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    
}
