/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
import com.ilkgunel.hastaneotomasyonu.facade.ClinicFacade;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 010533
 */
public class ClinicService {
     List<Klinikler> clinicResults;
     List<String> clinics;
     
     @Autowired
     private ClinicFacade clinicFacade;
     
    public List<String> getAllClinicNames() throws Exception{
        clinics=new ArrayList<>();
        clinicResults=clinicFacade.findListByNamedQuery("Klinikler.findAll");
        for (Klinikler k:clinicResults)
        {
            clinics.add(k.getKlinikadi());
        }
        return clinics;
    }

    public List<Klinikler> getClinicResults() {
        return clinicResults;
    }

    public List<String> getClinics() {
        return clinics;
    }
}
