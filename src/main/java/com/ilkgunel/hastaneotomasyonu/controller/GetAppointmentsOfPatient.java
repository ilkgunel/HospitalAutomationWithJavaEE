/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
import com.ilkgunel.hastaneotomasyonu.service.TakenAppointmentsService;
import javax.faces.bean.ManagedProperty;
import javax.persistence.PersistenceContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean
@SessionScoped
public class GetAppointmentsOfPatient implements Serializable {
    
    @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;
    
    List<Takenappointments> takenAppointmentsOfPatient;
    Boolean cancelButtonRendered;
    Boolean passedText;
    String cancelMessage;
    
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointments;
    
    public void fillList() throws Exception
    {
        ApplicationContext context = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        TakenAppointmentsService takenAppointmentsService = (TakenAppointmentsService) context.getBean("takenAppointmnetsService");
        
        takenAppointmentsOfPatient=takenAppointmentsService.getAppointmentsOfPatient(saveAppointments.comingIdentityNumber);
        if(takenAppointmentsOfPatient!=null){
            System.out.println("takenAppointmentsOfPatient listesi boş değil!");
        }
        else{
            System.out.println("!!!takenAppointmentsOfPatient listesi boş!!!");
        }
    }
    

    public void cancelAppointment()
    {
        System.out.println("Randevu İptal Metoduna Giriş Yapıldı");
        
    	FacesContext fc=FacesContext.getCurrentInstance();
    	Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        int clokId;
		try {
			Takenappointments t=em.find(Takenappointments.class, Integer.parseInt(params.get("takenAppointmentId")));
			t.setWasappointmentcancelled(true);
                        clokId=t.getClockid();
			em.getTransaction().commit();

                        System.out.println("İptal edilecek saat id si:"+clokId);
			Randevusaatleri r=em.find(Randevusaatleri.class,clokId);
			r.setSaatalindimi(false);
			r.setTitle("");
			em.getTransaction().commit();
			
			cancelMessage="Randevunuz Başarı İle İptal Edildi!";
			
		} catch (Exception e) {
			System.err.println("Meydana Gelen Hata:"+e);
			cancelMessage="Randevunun İptali Sırasında Bir Hata Meydana Geldi!";
		}
                System.out.println("Randevu İptal Metoduna Çıkış Yapıldı");
    	
    }
    
    
    public List<Takenappointments> getTakenAppointmentsOfPatient() {
        return takenAppointmentsOfPatient;
    }

    public void setTakenAppointmentsOfPatient(List<Takenappointments> takenAppointmentsOfPatient) {
        this.takenAppointmentsOfPatient = takenAppointmentsOfPatient;
    }
    
    public String getCancelMessage() {
            return cancelMessage;
    }


    public void setCancelMessage(String cancelMessage) {
            this.cancelMessage = cancelMessage;
    }

    public SaveAppointments getSaveAppointments() {
        return saveAppointments;
    }

    public void setSaveAppointments(SaveAppointments saveAppointments) {
        this.saveAppointments = saveAppointments;
    }
}
