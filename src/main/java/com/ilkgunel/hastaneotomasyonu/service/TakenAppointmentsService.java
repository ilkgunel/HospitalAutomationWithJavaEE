/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
import com.ilkgunel.hastaneotomasyonu.facade.RandevuSaatleriFacade;
import com.ilkgunel.hastaneotomasyonu.facade.TakenAppointmentsFacade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 010533
 */
public class TakenAppointmentsService {
    @Autowired
    private TakenAppointmentsFacade takenAppointmentsFacade;
    
    @Autowired
    private RandevuSaatleriFacade randevuSaatleriFacade;
    
    public List<Takenappointments> getAppointmentsOfPatient(String patientId) throws Exception{
        Map map = new HashMap();
        map.put("patientid", patientId);
        map.put("cancelParameter", false);
        return takenAppointmentsFacade.findListByNamedQuery("TakenAppointments.findByPatineIdAndIsAppointmentCancelled", map);
    }
    
    public String cancelAppointment(int takenAppointmentId){
        String message="";
        try {
            Takenappointments t = takenAppointmentsFacade.find(takenAppointmentId);
            t.setWasappointmentcancelled(true);
            int clokId=t.getClockid();

            Randevusaatleri r = randevuSaatleriFacade.find(clokId);
            r.setSaatalindimi(false);
            r.setTitle("");
            message ="Randevunuz Başarı İle İptal Edildi!";
        } 
        catch (Exception e) {
            System.err.println("Meydana Gelen Hata:"+e);
            message="Randevunun İptali Sırasında Bir Hata Meydana Geldi!";
        }
        return message;
    }
}
