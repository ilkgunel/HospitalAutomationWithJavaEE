/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.controller.SaveAppointments;
import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;
import com.ilkgunel.hastaneotomasyonu.facade.AvailableAppointmentsFacade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author 010533
 */
public class AvailableAppointmentsService {
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private AvailableAppointmentsFacade availableAppointmentsFacade;
    
    @Autowired
    private HospitalsService hospitalsService;
    
    @Autowired
    private ClinicService clinicService;
    
    List<Uygunrandevular> availableAppointments;
    List<Object[]> doctorAndTimeList;
    List<Randevusaatleri> appointmentClockResults;
    
    public List<Uygunrandevular> getAllAvaliableAppointments(String hospitalName,String clinic,String clicPlace){
        int hospitalid = 0;
        for (Hastaneler h:hospitalsService.getHospitalResults())
        {
            
            if(h.getHastaneadi().equals(hospitalName))
            {
                hospitalid=h.getId();
                break;
            }
        }
        
        int clinicId=0;
        for(Klinikler k:clinicService.getClinicResults())
        {
            if(k.getKlinikadi().equals(clinic))
            {
                clinicId=k.getId();
                break;
            }
        }
        
        TypedQuery<Uygunrandevular> query=em.createQuery("SELECT u FROM Uygunrandevular AS u WHERE u.hastaneid=:hospitalid AND u.klinikid=:clinicid AND u.klinikyeri=:clinicplace "
                + "AND u.tarih = (select min(uu.tarih) from Uygunrandevular uu where uu.doktorid = u.doktorid)",Uygunrandevular.class);
        
        query.setParameter("hospitalid",hospitalid);
        query.setParameter("clinicid", clinicId);
        query.setParameter("clinicplace", clicPlace);
        
        availableAppointments=query.getResultList();
        
        return availableAppointments;
    }
    
    public Boolean[] changeRenderingStates(SaveAppointments saveAppointments)
    {
        TypedQuery<Object[]> doctorAndTimeQuery = em.createQuery("SELECT u.doktoradi,FUNCTION('DATE',u.tarih),u.uygunrandevuid FROM Uygunrandevular AS u WHERE u.doktorid=:doctorid ORDER BY u.tarih ASC",Object[].class);
        doctorAndTimeQuery.setParameter("doctorid", saveAppointments.getSelectedAppointment().getDoktorid());
        doctorAndTimeList=new ArrayList<>();
        doctorAndTimeList=doctorAndTimeQuery.getResultList();
        
        TypedQuery<Randevusaatleri> query=em.createQuery("SELECT c FROM Randevusaatleri c WHERE c.doktorid=:doctorid",Randevusaatleri.class);
        System.out.println("Seçilen Randevunun ID'si"+saveAppointments.getSelectedAppointment().getUygunrandevuid());
        query.setParameter("doctorid", saveAppointments.getSelectedAppointment().getUygunrandevuid());
        appointmentClockResults=new ArrayList<>();
        appointmentClockResults=query.getResultList();
        
        Boolean[] array = new Boolean[2];
        array[0]=false;
        array[1]=true;
        return array;
    }

    public List<Object[]> getDoctorAndTimeList() {
        return doctorAndTimeList;
    }

    public List<Randevusaatleri> getAppointmentClockResults() {
        return appointmentClockResults;
    }
}
