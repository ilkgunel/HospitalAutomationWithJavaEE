/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.controller.GetClinics;
import com.ilkgunel.hastaneotomasyonu.controller.GetHospitals;
import com.ilkgunel.hastaneotomasyonu.controller.SaveAppointments;
import com.ilkgunel.hastaneotomasyonu.entity.Doktorlar;
import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
import com.ilkgunel.hastaneotomasyonu.facade.DoctorFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author 010533
 */
public class DoctorService {
    
    //@ManagedProperty(value = "#{getClinics}")
    @Autowired
    private GetClinics getClinicsObject;

    //@ManagedProperty(value = "#{getHospitals}")
    @Autowired
    private GetHospitals getHospitalsObject;
    
    @Autowired
    private DoctorFacade doctorFacade;
    
    List<Doktorlar> doctorResults;
    List<String> doctors;
    
    public List<String> getAllDoctorNames(String clinic,String hospital) throws Exception{
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        ClinicService clinicService = (ClinicService) context.getBean("clinicService");
        
        int clinicId=0;
        for(Klinikler k:clinicService.getClinicResults())
        {
            if(k.getKlinikadi().equals(clinic))
            {
                clinicId=k.getId();
                break;
            }
        }

        int hospitalId=0;
        
        HospitalsService hospitalsService = (HospitalsService) context.getBean("hospitalService");
        
        for(Hastaneler h:hospitalsService.getHospitalResults())
        {
            if(h.getHastaneadi().equals(hospital))
            {
                hospitalId=h.getId();
            }
        }
        //doctorResults=new ArrayList<>();
        doctors=new ArrayList<>();
        Map map = new HashMap();
        map.put("clinicid", clinicId);
        map.put("hospitalid",hospitalId);
        doctorResults=doctorFacade.findListByNamedQuery("Doktorlar.findByBransIdAndHastaneID", map);
        
        for(Doktorlar d:doctorResults)
        {
            doctors.add(d.getDoktoradi());
        }
        return  doctors;
    }
}
