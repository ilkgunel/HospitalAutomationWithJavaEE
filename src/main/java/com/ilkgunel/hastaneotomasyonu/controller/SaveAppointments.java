/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean
@SessionScoped
public class SaveAppointments implements Serializable{

    String comingIdentityNumber;
    String comingPassword;
    
    String clinic;
    private String city;
    String district;
    private String hospital;
    String clinicPlace;
    String doctor;
    String clockId;
    Uygunrandevular selectedAppointment;
    
    String operationResult;
    
    public Uygunrandevular getSelectedAppointment() {
        return selectedAppointment;
    }

    public void setSelectedAppointment(Uygunrandevular selectedAppointment) {
        this.selectedAppointment = selectedAppointment;
    }

    public String getClinicPlace() 
    { 
        
        return clinicPlace; 
    }

    public void setClinicPlace(String clinicPlace) 
    { 
        this.clinicPlace = clinicPlace; 
    }

    public String getClinic() 
    {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getCity() 
    { 
        return city; 
    }

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
    
    public String getClockId() {
        return clockId;
    }

    public void setClockId(String clockId) {
        this.clockId = clockId;
    }
    
    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public String getComingIdentityNumber() {
        return comingIdentityNumber;
    }

    public void setComingIdentityNumber(String comingIdentityNumber) {
        this.comingIdentityNumber = comingIdentityNumber;
    }

    public String getComingPassword() {
        return comingPassword;
    }

    public void setComingPassword(String comingPassword) {
        this.comingPassword = comingPassword;
    }
    
    public void saveToDb(ActionEvent event)
    {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
        EntityManager em=emf.createEntityManager();
        
        Takenappointments takenappointmentsObject=new Takenappointments();
        System.out.println(selectedAppointment.getDoktorid());
        takenappointmentsObject.setDoctorid(selectedAppointment.getDoktorid());
        System.out.println(comingIdentityNumber);
        takenappointmentsObject.setPatientid(comingIdentityNumber);
        System.out.println(selectedAppointment.getTarih());
        takenappointmentsObject.setDate(selectedAppointment.getTarih());
        System.out.println(hospital);
        takenappointmentsObject.setHospitalname(hospital);
        System.out.println(clinic);
        takenappointmentsObject.setClinicname(clinic);
        System.out.println(clinicPlace);
        takenappointmentsObject.setClinicplace(clinicPlace);
        System.out.println("");
        
        TypedQuery<Randevusaatleri> query=em.createQuery("SELECT c FROM Randevusaatleri c",Randevusaatleri.class);
        List<Randevusaatleri> appointmentClockResults =new ArrayList<>();
        appointmentClockResults=query.getResultList();
        for (Randevusaatleri r  : appointmentClockResults) {
            if(r.getSaatid()==Integer.parseInt(clockId));
                takenappointmentsObject.setHour(r.getSaat());
        }
        
        try {
            em.getTransaction().begin();
            /*Query updateQuery=em.createQuery("UPDATE Randevusaatleri r SET r.saatalindimi=TRUE,r.title='DOLU' WHERE r.saatid=:clockId");
            updateQuery.setParameter("clockId", Integer.parseInt(clockId));
            int updateCount = query.executeUpdate();	
            if (updateCount > 0) {
                System.out.println("Done...");
            }*/
            Randevusaatleri r=em.find(Randevusaatleri.class, Integer.parseInt(clockId));
            r.setSaatalindimi(true);
            r.setTitle("DOLU");
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(takenappointmentsObject);
            em.getTransaction().commit();
            operationResult="Randevuz Sisteme Kaydedildi,Seçtiğiniz Gün ve Saatte 15 Dakika Erken Geliniz Lütfen";
        } catch (Exception e) {
            System.out.println("Meydana Gelen Hata:"+e);
            operationResult="Randevunun Kaydı Sırasında Bir Hata Meydana Geldi!";
        }
        
    }
    
}
