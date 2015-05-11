package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
//Çalışan Sınıf Budur
@ManagedBean(name = "getAvaliableAppointments")
@ViewScoped
public class GetAvaliableAppointments implements Serializable{
    
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("HospitalAutomation");
    EntityManager em=emf.createEntityManager();
        
    List<Uygunrandevular> availableAppointments;
    List<Object[]> doctorAndTimeList;
    boolean renderingTakingAppointmentInfo=true;
    boolean renderingClocks=false;
    boolean renderingDataTable=false;
    int hospitalid;
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointmentsObject;

    @ManagedProperty(value = "#{getHospitals}")
    private GetHospitals getHospitalsObject;
    
    @ManagedProperty(value = "#{getClinics}")
    private GetClinics getClinicsObject;

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

    public SaveAppointments getSaveAppointmentsObject() {
        return saveAppointmentsObject;
    }

    public void setSaveAppointmentsObject(SaveAppointments saveAppointmentsObject) {
        this.saveAppointmentsObject = saveAppointmentsObject;
    }

    public boolean isRenderingDataTable() {
        return renderingDataTable;
    }

    public void setRenderingDataTable(boolean renderingDataTable) {
        this.renderingDataTable = renderingDataTable;
    }

    public GetHospitals getGetHospitalsObject() {
        return getHospitalsObject;
    }

    public void setGetHospitalsObject(GetHospitals getHospitalsObject) {
        this.getHospitalsObject = getHospitalsObject;
    }

    public List<Uygunrandevular> getAvailableAppointments() {
        return availableAppointments;
    }

    public void setAvailableAppointments(List<Uygunrandevular> availableAppointments) {
        this.availableAppointments = availableAppointments;
    }

    public List<Object[]> getDoctorAndTimeList() {
        return doctorAndTimeList;
    }

    public void setDoctorAndTimeList(List<Object[]> doctorAndTimeList) {
        this.doctorAndTimeList = doctorAndTimeList;
    }

    public GetClinics getGetClinicsObject() {
        return getClinicsObject;
    }

    public void setGetClinicsObject(GetClinics getClinicsObject) {
        this.getClinicsObject = getClinicsObject;
    }
    

    public void fillList()
    {
        availableAppointments=new ArrayList<>();

        

        for (Hastaneler h:getHospitalsObject.getHospitalResults())
        {
            if(h.getHastaneadi().equals(saveAppointmentsObject.getHospital()))
            {
                hospitalid=h.getId();
                break;
            }
        }
        
        int clinicId=0;
        for(Klinikler k:getClinicsObject.clinicResults)
        {
            if(k.getKlinikadi().equals(saveAppointmentsObject.clinic))
            {
                clinicId=k.getId();
                break;
            }
        }
        
        TypedQuery<Uygunrandevular> query=em.createQuery("SELECT U FROM Uygunrandevular U WHERE U.hastaneid=:hospitalid AND U.klinikid=:clinicid AND U.klinikyeri=:clinicplace "
                + "AND u.tarih = (select min(uu.tarih) from Uygunrandevular uu where uu.doktorid = u.doktorid)",Uygunrandevular.class);
        
        
        query.setParameter("hospitalid",hospitalid);
        query.setParameter("clinicid", clinicId);
        query.setParameter("clinicplace", saveAppointmentsObject.clinicPlace);

        availableAppointments=query.getResultList();

        setRenderingDataTable(true);

    }

    public void changeRenderingStates()
    {
        setRenderingTakingAppointmentInfo(false);
        setRenderingClocks(true);
        
        TypedQuery<Object[]> doctorAndTimeQuery = em.createQuery("SELECT u.doktoradi,u.tarih FROM Uygunrandevular AS u WHERE u.doktorid=:doctorid ORDER BY u.tarih ASC",Object[].class);
        doctorAndTimeQuery.setParameter("doctorid", saveAppointmentsObject.selectedAppointment.getDoktorid());
        
        doctorAndTimeList=new ArrayList<>();
        doctorAndTimeList=doctorAndTimeQuery.getResultList();
    }
}

