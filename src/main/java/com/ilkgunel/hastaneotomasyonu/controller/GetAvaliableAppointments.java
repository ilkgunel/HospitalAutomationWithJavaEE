package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;
import com.ilkgunel.hastaneotomasyonu.service.AvailableAppointmentsService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
//Çalışan Sınıf Budur
@ManagedBean(name = "getAvaliableAppointments")
@ViewScoped
public class GetAvaliableAppointments implements Serializable{
    
    List<Object[]> doctorAndTimeList;
    List<Randevusaatleri> appointmentClockResults;
    
    boolean renderingTakingAppointmentInfo=true;
    boolean renderingClocks=false;
    boolean renderingDataTable=false;
    int hospitalid;
    
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointmentsObjectInAvaliableAppointments;

    public boolean isRenderingTakingAppointmentInfo() {
        return renderingTakingAppointmentInfo;
    }

    public void setRenderingTakingAppointmentInfo(boolean renderingTakingAppointmentInfo) {
        this.renderingTakingAppointmentInfo = renderingTakingAppointmentInfo;
    }

    public boolean isRenderingClocks() {
        return renderingClocks;
    }

    public void setRenderingClocks(boolean renderingClocks) {
        this.renderingClocks = renderingClocks;
    }
    

    public boolean isRenderingDataTable() {
        return renderingDataTable;
    }

    public void setRenderingDataTable(boolean renderingDataTable) {
        this.renderingDataTable = renderingDataTable;
    }

    public List<Object[]> getDoctorAndTimeList() {
        return doctorAndTimeList;
    }

    public void setDoctorAndTimeList(List<Object[]> doctorAndTimeList) {
        this.doctorAndTimeList = doctorAndTimeList;
    }

    public List<Randevusaatleri> getAppointmentClockResults() {
        return appointmentClockResults;
    }

    public void setAppointmentClockResults(List<Randevusaatleri> appointmentClockResults) {
        this.appointmentClockResults = appointmentClockResults;
    }

    public SaveAppointments getSaveAppointmentsObjectInAvaliableAppointments() {
        return saveAppointmentsObjectInAvaliableAppointments;
    }

    public void setSaveAppointmentsObjectInAvaliableAppointments(SaveAppointments saveAppointmentsObjectInAvaliableAppointments) {
        this.saveAppointmentsObjectInAvaliableAppointments = saveAppointmentsObjectInAvaliableAppointments;
    }
    
    public List<Uygunrandevular> fillList()
    {
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        
        AvailableAppointmentsService availableAppointmentsService = (AvailableAppointmentsService) context.getBean("availableAppointmentsService");
        
        setRenderingDataTable(true);
        
        return availableAppointmentsService.getAllAvaliableAppointments(saveAppointmentsObjectInAvaliableAppointments.getHospital(),
                                                                        saveAppointmentsObjectInAvaliableAppointments.clinic, 
                                                                        saveAppointmentsObjectInAvaliableAppointments.clinicPlace);
    }

    public void changeRenderingStates()
    {

        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        AvailableAppointmentsService availableAppointmentsService = (AvailableAppointmentsService) context.getBean("availableAppointmentsService");
        
        Boolean[] array = availableAppointmentsService.changeRenderingStates(saveAppointmentsObjectInAvaliableAppointments);
        
        setDoctorAndTimeList(availableAppointmentsService.getDoctorAndTimeList());
        setAppointmentClockResults(availableAppointmentsService.getAppointmentClockResults());
        
        setRenderingTakingAppointmentInfo(array[0]);
        setRenderingClocks(array[1]);
        
    }
}

