package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Klinikyerleri;
import com.ilkgunel.hastaneotomasyonu.service.HospitalsService;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@SessionScoped
public class GetClinicPlaces implements Serializable {

    @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;
    
    @Autowired
    private GetHospitals getHospitals;

    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments appointmentObject;

    int hospitalId;
    List<Klinikyerleri> clinicPlaceResults;
    List<String> clinicPlaces;

    public List<String> getClinicPlaces() {
        return clinicPlaces;
    }

    public void setClinicPlaces(List<String> clinicPlaces) {
        this.clinicPlaces = clinicPlaces;
    }

    public List<Klinikyerleri> getClinicPlaceResults() {
        return clinicPlaceResults;
    }

    public void setClinicPlaceResults(List<Klinikyerleri> clinicPlaceResults) {
        this.clinicPlaceResults = clinicPlaceResults;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public GetHospitals getGetHospitals() {
        return getHospitals;
    }

    public void setGetHospitals(GetHospitals getHospitals) {
        this.getHospitals = getHospitals;
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
        
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        HospitalsService hospitalsService = (HospitalsService) context.getBean("hospitalService");
        
        for(Hastaneler h:hospitalsService.getHospitalResults())
        {
            if(h.getHastaneadi().equals(currentHospital))
            {
                setHospitalId(h.getId());
                break;
            }
        }

        System.out.println("Seçilen Hastanenin ID'si"+hospitalId);

        TypedQuery<Klinikyerleri> query=em.createQuery("select  c from Klinikyerleri c where c.hastaneid=:value",Klinikyerleri.class);
        query.setParameter("value",hospitalId);

        /*System.out.println("Döngü Öncesi");
        for(Klinikyerleri k:query.getResultList())
        {
            System.out.println(k.getKlinikyeri());
        }
        System.out.println("Döngü Sonrası");*/

        clinicPlaceResults=query.getResultList();

        for(Klinikyerleri c:clinicPlaceResults)
        {
            System.out.println(c.getKlinikyeri());
            clinicPlaces.add(c.getKlinikyeri());
        }
    }
}