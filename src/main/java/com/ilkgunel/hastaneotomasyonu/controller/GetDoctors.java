package com.ilkgunel.hastaneotomasyonu.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import com.ilkgunel.hastaneotomasyonu.entity.Doktorlar;
import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
import com.ilkgunel.hastaneotomasyonu.service.DoctorService;
import com.ilkgunel.hastaneotomasyonu.service.HospitalsService;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;

@Component
@ManagedBean
@SessionScoped
public class GetDoctors implements Serializable{
   @ManagedProperty(value = "#{saveAppointments}")
   private SaveAppointments saveAppointments;
   
    public  List<String> fillList() throws Exception{
        ApplicationContext context = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        DoctorService doctorService = (DoctorService) context.getBean("doctorService");
        return doctorService.getAllDoctorNames(saveAppointments.getClinic(),saveAppointments.getHospital());
    }

    public SaveAppointments getSaveAppointments() {
        return saveAppointments;
    }

    public void setSaveAppointments(SaveAppointments saveAppointments) {
        this.saveAppointments = saveAppointments;
    }
}
