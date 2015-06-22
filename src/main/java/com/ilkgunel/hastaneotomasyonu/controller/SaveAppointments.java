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
import java.util.Map;
import javax.faces.context.FacesContext;
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
    private String clockId;
    Uygunrandevular selectedAppointment;
    
    String operationResult;
    
    int randevuid=0;
    String hour="";
    
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
        takenappointmentsObject.setDoctorid(selectedAppointment.getDoktorid());
        takenappointmentsObject.setPatientid(comingIdentityNumber);
        takenappointmentsObject.setHospitalname(hospital);
        takenappointmentsObject.setClinicname(clinic);
        takenappointmentsObject.setClinicplace(clinicPlace);
        takenappointmentsObject.setClockid(Integer.parseInt(clockId));
        
        TypedQuery<Randevusaatleri> appointmentClockQuery=em.createQuery("SELECT c FROM Randevusaatleri c",Randevusaatleri.class);
        List<Randevusaatleri> appointmentClockResults =new ArrayList<>();
        
        appointmentClockResults=appointmentClockQuery.getResultList();
        for (Randevusaatleri r  : appointmentClockResults) {
            if(r.getSaatid()==Integer.parseInt(clockId))
            {	
                System.out.println("Saatin ID'si:"+clockId);
                hour=r.getSaat();
                randevuid=r.getRandevuid();
                break;
            }
        }
        
        takenappointmentsObject.setHour(hour);
        TypedQuery<Uygunrandevular> appointmentdIdQuery=em.createQuery("SELECT u FROM Uygunrandevular u WHERE u.uygunrandevuid=:appointmentid",Uygunrandevular.class);
        List<Uygunrandevular> appointmentIdResults =new ArrayList<>();
        appointmentdIdQuery.setParameter("appointmentid", randevuid);
        appointmentIdResults=appointmentdIdQuery.getResultList();
        
        for(Uygunrandevular u:appointmentIdResults)
        {
        	takenappointmentsObject.setDate(u.getTarih());
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

    public String getClockId() {
        return clockId;
    }

    public void setClockId(String clockId) {
        this.clockId = clockId;
        
    }
    
}
