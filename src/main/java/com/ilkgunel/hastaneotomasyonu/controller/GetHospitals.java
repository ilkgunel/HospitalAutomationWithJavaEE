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
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import javax.annotation.Resource;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean(name="getHospitals")
@SessionScoped
public class GetHospitals implements  Serializable{
    
    String mesaj="İlkay Günel";

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    @Resource
    private GetDistricts districtsObject;
    
    @Resource
    private SaveAppointments saveAppointments;
    
    private List<String> hospitals;
    private List<Hastaneler> hospitalResults;
    
    private int districtId;
    
    public void fillList()
    {
        //ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        //SaveAppointments saveAppointments=(SaveAppointments) context.getBean("saveAppointments");
        //GetDistricts districtsObject=(GetDistricts) context.getBean("getDistricts");

        hospitals=new ArrayList<>();
        hospitalResults=new ArrayList<>();
        String currentDistrict=saveAppointments.district;
        for(Ilceler i:districtsObject.districtResults)
        {
            if(currentDistrict.equals(i.getIlce()))
            {
                setDistrictId(i.getId());
                break;
            }
        }
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
        EntityManager em=emf.createEntityManager();
        TypedQuery<Hastaneler> query=em.createQuery("SELECT h FROM Hastaneler h WHERE h.ilceid=:value",Hastaneler.class);
        query.setParameter("value", districtId);
        hospitalResults=query.getResultList();
        
        for(Hastaneler h:hospitalResults)
        {
            hospitals.add(h.getHastaneadi());
        }
        
    }

    public List<String> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<String> hospitals) {
        this.hospitals = hospitals;
    }

    public List<Hastaneler> getHospitalResults() {
        return hospitalResults;
    }

    public void setHospitalResults(List<Hastaneler> hospitalResults) {
        this.hospitalResults = hospitalResults;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    /*public GetDistricts getDistrictsObject() {
        return districtsObject;
    }

    public void setDistrictsObject(GetDistricts districtsObject) {
        this.districtsObject = districtsObject;
    }

    public SaveAppointments getSaveAppointments() {
        return saveAppointments;
    }

    /*public void setSaveAppointments(SaveAppointments saveAppointments) {
        this.saveAppointments = saveAppointments;
    }*/

}
