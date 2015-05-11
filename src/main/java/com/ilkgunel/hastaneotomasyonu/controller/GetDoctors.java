package com.ilkgunel.hastaneotomasyonu.controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ilkgunel.hastaneotomasyonu.entity.Doktorlar;
import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
import java.util.ArrayList;
import javax.faces.bean.ManagedProperty;
import javax.persistence.TypedQuery;

@ManagedBean
@ViewScoped
public class GetDoctors {
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
    EntityManager em=emf.createEntityManager();
    
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointmentsObject;
    
    @ManagedProperty(value = "#{getClinics}")
    private GetClinics getClinicsObject;
    
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
        TypedQuery<Doktorlar> query=em.createQuery("SELECT d FROM Doktorlar d WHERE d.bransid=:clinicid",Doktorlar.class);
        query.setParameter("clinicid", clinicId);
        doctorResults=new ArrayList<>();
        doctors=new ArrayList<>();
        doctorResults=query.getResultList();
        
        for(Doktorlar d:doctorResults)
        {
            doctors.add(d.getDoktoradi());
        }
    }
}
