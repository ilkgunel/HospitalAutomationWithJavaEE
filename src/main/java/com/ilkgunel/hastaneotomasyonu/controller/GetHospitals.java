/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import java.util.List;
import java.util.ArrayList;

import com.ilkgunel.hastaneotomasyonu.entity.Ilceler;
import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.service.DistrictService;
import com.ilkgunel.hastaneotomasyonu.service.HospitalsService;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean(name="getHospitals")
@ViewScoped
public class GetHospitals implements  Serializable{

//    @ManagedProperty(value = "#{getDistricts}")
//    private GetDistricts districtsObject;
    
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointments;
    
    
    private int districtId;
    String currentDistrict;
    
    public List<String> fillList() throws Exception
    {
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        HospitalsService hospitalsService = (HospitalsService) context.getBean("hospitalService");
        return hospitalsService.getAllHospitalNames(saveAppointments.currentDistrict);
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getCurrentDistrict() {
        return currentDistrict;
    }

    public void setCurrentDistrict(String currentDistrict) {
        this.currentDistrict = currentDistrict;
    }

    public SaveAppointments getSaveAppointments() {
        return saveAppointments;
    }

    public void setSaveAppointments(SaveAppointments saveAppointments) {
        this.saveAppointments = saveAppointments;
    }
}
