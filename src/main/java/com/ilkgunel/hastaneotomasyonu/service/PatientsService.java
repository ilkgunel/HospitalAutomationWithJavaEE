/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.entity.Patients;
import com.ilkgunel.hastaneotomasyonu.facade.PatientFacade;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 010533
 */
public class PatientsService {
    @Autowired
    private PatientFacade patientFacade;
    
    public Patients getPatientInfo(String patientId) {
        Map map = new HashMap();
        map.put("identitynumber",patientId);
        Patients patient = patientFacade.find(patientId);
        return patient;
    }
    
    public String updatePatientInfo(Patients patients){
        String message = "";
        try {
            patientFacade.updateMemberInfo(patients);
            message = "Kayıt Güncellemesi Başarılı!";
        } catch (Exception e) {
            message = "Kayıt Güncellemesi Sırasında Hata Meydana Geldi!\nHata:"+e;
        }
        return message;
    }
}
