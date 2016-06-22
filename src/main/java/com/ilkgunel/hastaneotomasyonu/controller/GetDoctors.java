package com.ilkgunel.hastaneotomasyonu.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

import com.ilkgunel.hastaneotomasyonu.entity.Doktorlar;
import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
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
    
    @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;
    
    //@ManagedProperty(value = "#{saveAppointments}")
    @Autowired
    private SaveAppointments saveAppointmentsObject;
    
    //@ManagedProperty(value = "#{getClinics}")
    @Autowired
    private GetClinics getClinicsObject;

    //@ManagedProperty(value = "#{getHospitals}")
    @Autowired
    private GetHospitals getHospitalsObject;
    
    List<Doktorlar> doctorResults;
    List<String> doctors;

    public List<String> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<String> doctors) {
        this.doctors = doctors;
    }

    public SaveAppointments getSaveAppointmentsObject() {
        return saveAppointmentsObject;
    }

    public void setSaveAppointmentsObject(SaveAppointments saveAppointmentsObject) {
        this.saveAppointmentsObject = saveAppointmentsObject;
    }

    public GetClinics getGetClinicsObject() {
        return getClinicsObject;
    }

    public void setGetClinicsObject(GetClinics getClinicsObject) {
        this.getClinicsObject = getClinicsObject;
    }

    public GetHospitals getGetHospitalsObject() {
        return getHospitalsObject;
    }

    public void setGetHospitalsObject(GetHospitals getHospitalsObject) {
        this.getHospitalsObject = getHospitalsObject;
    }

    public  void fillList() {
        int clinicId=0;
        for(Klinikler k:getClinicsObject.clinicResults)
        {
            if(k.getKlinikadi().equals(saveAppointmentsObject.clinic))
            {
                clinicId=k.getId();
                break;
            }
        }

        int hospitalId=0;
        
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        HospitalsService hospitalsService = (HospitalsService) context.getBean("hospitalService");
        
        for(Hastaneler h:hospitalsService.getHospitalResults())
        {
            if(h.getHastaneadi().equals(saveAppointmentsObject.getHospital()))
            {
                hospitalId=h.getId();
            }
        }
        TypedQuery<Doktorlar> query=em.createQuery("SELECT d FROM Doktorlar d WHERE d.bransid=:clinicid AND d.hastaneid=:hospitalid",Doktorlar.class);
        query.setParameter("clinicid", clinicId);
        query.setParameter("hospitalid",hospitalId);
        //doctorResults=new ArrayList<>();
        doctors=new ArrayList<>();
        doctorResults=query.getResultList();
        
        for(Doktorlar d:doctorResults)
        {
            doctors.add(d.getDoktoradi());
        }
    }
}
