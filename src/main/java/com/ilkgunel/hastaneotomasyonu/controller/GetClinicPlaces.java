package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.KlinikyerleriEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class GetClinicPlaces implements Serializable {

    @ManagedProperty(value = "#{getHospitals}")
    private GetHospitals hospitalObject;

    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments appointmentObject;

    int hospitalId;
    List<KlinikyerleriEntity> clinicPlaceResults;
    List<String> clinicPlaces;

    public List<String> getClinicPlaces() {
        return clinicPlaces;
    }

    public void setClinicPlaces(List<String> clinicPlaces) {
        this.clinicPlaces = clinicPlaces;
    }

    public List<KlinikyerleriEntity> getClinicPlaceResults() {
        return clinicPlaceResults;
    }

    public void setClinicPlaceResults(List<KlinikyerleriEntity> clinicPlaceResults) {
        this.clinicPlaceResults = clinicPlaceResults;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public GetHospitals getHospitalObject() {
        return hospitalObject;
    }

    public void setHospitalObject(GetHospitals hospitalObject) {
        this.hospitalObject = hospitalObject;
    }

    public SaveAppointments getAppointmentObject() {
        return appointmentObject;
    }

    public void setAppointmentObject(SaveAppointments appointmentObject) {
        this.appointmentObject = appointmentObject;
    }

    public void fillList()
    {
        System.out.println("Fill List Metodu Çağırıldı");

        clinicPlaceResults=new ArrayList<>();
        clinicPlaces=new ArrayList<>();

        String currentHospital=appointmentObject.getHospital();

        System.out.println("Seçilen Klinik:"+appointmentObject.getClinic());

        for(Hastaneler h:hospitalObject.getHospitalResults())
        {
            if(h.getHastaneadi().equals(currentHospital))
            {
                setHospitalId(h.getId());
                break;
            }
        }

        System.out.println("Seçilen Hastanenin ID'si"+hospitalId);

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("HospitalAutomation");
        EntityManager em=emf.createEntityManager();

        TypedQuery<KlinikyerleriEntity> query=em.createQuery("select  c from KlinikyerleriEntity c where c.hastaneid=:value",KlinikyerleriEntity.class);
        query.setParameter("value",hospitalId);


        System.out.println("Döngü Öncesi");
        for(KlinikyerleriEntity k:query.getResultList())
        {
            System.out.println(k.getKlinikyeri());
        }
        System.out.println("Döngü Sonrası");


        clinicPlaceResults=query.getResultList();

        for(KlinikyerleriEntity c:clinicPlaceResults)
        {
            System.out.println(c.getKlinikyeri());
            clinicPlaces.add(c.getKlinikyeri());
        }
    }
}












