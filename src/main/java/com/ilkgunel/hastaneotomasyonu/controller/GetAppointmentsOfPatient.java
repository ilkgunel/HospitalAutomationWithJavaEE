/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.TypedQuery;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean
@SessionScoped
public class GetAppointmentsOfPatient implements Serializable {
    List<Takenappointments> takenAppointmentsOfPatient;
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
    EntityManager em=emf.createEntityManager();
    
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointments;
    
    
    public void fillList1()
	{
		takenAppointmentsOfPatient=new ArrayList<>();
		TypedQuery<Takenappointments> query=em.createQuery("SELECT t FROM Takenappointments t WHERE t.patientid=:patientid",Takenappointments.class);
		query.setParameter("patientid", saveAppointments.comingIdentityNumber);
		takenAppointmentsOfPatient=query.getResultList();
		
		for (Takenappointments t:takenAppointmentsOfPatient) {
			System.out.println(t.getDoctorid());
			System.out.println(t.getClinicplace());
		}
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
}
