package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.service.ClinicPlacesService;
import com.ilkgunel.hastaneotomasyonu.service.HospitalsService;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@SessionScoped
public class GetClinicPlaces implements Serializable {

    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments appointmentObject;

    int hospitalId;
    
    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }
    

    public SaveAppointments getAppointmentObject() {
        return appointmentObject;
    }

    public void setAppointmentObject(SaveAppointments appointmentObject) {
        this.appointmentObject = appointmentObject;
    }

    public List<String> fillList() throws Exception
    {

        String currentHospital=appointmentObject.getHospital();

        System.out.println("Seçilen Klinik:"+appointmentObject.getClinic());
        
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        HospitalsService hospitalsService = (HospitalsService) context.getBean("hospitalService");
        ClinicPlacesService clinicPlacesService = (ClinicPlacesService) context.getBean("clinicPlacesService");
        
        
        for(Hastaneler h:hospitalsService.getHospitalResults())
        {
            if(h.getHastaneadi().equals(currentHospital))
            {
                setHospitalId(h.getId());
                break;
            }
        }
        
        return clinicPlacesService.getAllClinicNames(hospitalId);
    }
}