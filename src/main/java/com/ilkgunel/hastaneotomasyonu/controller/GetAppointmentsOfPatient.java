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
import javax.faces.bean.ManagedProperty;

import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
import com.ilkgunel.hastaneotomasyonu.service.TakenAppointmentsService;
import javax.faces.bean.ViewScoped;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean
@ViewScoped
public class GetAppointmentsOfPatient implements Serializable {
    
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
    }
    

    public void cancelAppointment()
    {
        ApplicationContext context = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        TakenAppointmentsService takenAppointmentsService = (TakenAppointmentsService) context.getBean("takenAppointmnetsService");
        
        FacesContext fc=FacesContext.getCurrentInstance();
    	Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        cancelMessage = takenAppointmentsService.cancelAppointment(Integer.parseInt(params.get("takenAppointmentId")));
 
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
