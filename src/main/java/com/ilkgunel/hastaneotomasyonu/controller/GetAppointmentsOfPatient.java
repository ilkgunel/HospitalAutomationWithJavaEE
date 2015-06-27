/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean
@SessionScoped
public class GetAppointmentsOfPatient implements Serializable {
    List<Takenappointments> takenAppointmentsOfPatient;
    Boolean cancelButtonRendered;
    Boolean passedText;
    String cancelMessage;
    
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
    EntityManager em=emf.createEntityManager();
    
    
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointments;
    
    
    public void fillList()
	{
    	DateFormat appointmentDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	DateFormat nowDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	
		takenAppointmentsOfPatient=new ArrayList<>();
		TypedQuery<Takenappointments> query=em.createQuery("SELECT t FROM Takenappointments t WHERE t.patientid=:patientid AND t.wasappointmentcancelled=:cancelParameter",Takenappointments.class);
		query.setParameter("patientid", saveAppointments.comingIdentityNumber);
		query.setParameter("cancelParameter", false);
		
		for(Takenappointments t:query.getResultList())
		{
			try {
				String dateStr = t.getDate().toString();
				DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				Date date = (Date)formatter.parse(dateStr);
				System.out.println(date);        

				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
				System.out.println("formatedDate : " + formatedDate);    
				
				Date date12=new Date();
				
				
				if((formatedDate.compareTo(nowDateFormat.format(date12)))>0)
				{
					System.out.println("Randevu Tarihi Şu anki tarihten sonra");
					t.setDatepassed(false);
				}
				else {
					System.out.println("Randevu Tarihi Şu anki tarihten önce");
					t.setDatepassed(true);
				}

			} catch (Exception e) {
				System.err.println("Meydana Gelen Hata:"+e);
			}

		}
		takenAppointmentsOfPatient=query.getResultList();
	}
    

    public void cancelAppointment()
    {
        System.out.println("Randevu İptal Metoduna Giriş Yapıldı");
        
    	FacesContext fc=FacesContext.getCurrentInstance();
    	Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        int clokId;
		try {
			em.getTransaction().begin();
			Takenappointments t=em.find(Takenappointments.class, Integer.parseInt(params.get("takenAppointmentId")));
			t.setWasappointmentcancelled(true);
                        clokId=t.getClockid();
			em.getTransaction().commit();
			
			em.getTransaction().begin();
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
    
    public SaveAppointments getSaveAppointments() {
		return saveAppointments;
	}
    
    public void setSaveAppointments(SaveAppointments saveAppointments) {
		this.saveAppointments = saveAppointments;
	}


	public String getCancelMessage() {
		return cancelMessage;
	}


	public void setCancelMessage(String cancelMessage) {
		this.cancelMessage = cancelMessage;
	}
    
}
