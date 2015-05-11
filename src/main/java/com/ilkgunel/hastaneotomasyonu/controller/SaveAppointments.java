/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;

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
    String clinic;
    String clinicPlace;
    String doctor;

    Uygunrandevular selectedAppointment;

    public Uygunrandevular getSelectedAppointment() {
        return selectedAppointment;
    }

    public void setSelectedAppointment(Uygunrandevular selectedAppointment) {
        this.selectedAppointment = selectedAppointment;
    }

    public String getClinicPlace() { return clinicPlace; }

    public void setClinicPlace(String clinicPlace) { this.clinicPlace = clinicPlace; }

    public String getClinic() {return clinic;}

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospital() {
        return hospital;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    
    
}
