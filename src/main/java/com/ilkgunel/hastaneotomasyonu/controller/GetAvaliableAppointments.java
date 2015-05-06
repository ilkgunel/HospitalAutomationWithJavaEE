package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
//Çalışan Sınıf Budur
@ManagedBean(name = "getAvaliableAppointments")
@ViewScoped
public class GetAvaliableAppointments {
    List<Uygunrandevular> availableAppointments;
    boolean renderingTakingAppointmentInfo=true;
    boolean renderingClocks=false;
    boolean renderingDataTable=false;
    int hospitalid;
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointmentsObject;

    @ManagedProperty(value = "#{getHospitals}")
    private GetHospitals getHospitalsObject;

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

    public void fillList()
    {
        availableAppointments=new ArrayList<>();

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("HospitalAutomation");
        EntityManager em=emf.createEntityManager();

        for (Hastaneler h:getHospitalsObject.getHospitalResults())
        {
            if(h.getHastaneadi().equals(saveAppointmentsObject.getHospital()))
                hospitalid=h.getId();
            break;
        }

        TypedQuery<Uygunrandevular> query=em.createQuery("SELECT u FROM Uygunrandevular u WHERE u.hastaneid=:hospitalid",Uygunrandevular.class);
        query.setParameter("hospitalid",hospitalid);

        availableAppointments=query.getResultList();

        setRenderingDataTable(true);

    }

    public void changeRenderingStates()
    {
        System.out.println("Rendering Değiştirme Metodu Çalıştı!!!\n");
        setRenderingTakingAppointmentInfo(false);
        setRenderingClocks(true);
    }
}

