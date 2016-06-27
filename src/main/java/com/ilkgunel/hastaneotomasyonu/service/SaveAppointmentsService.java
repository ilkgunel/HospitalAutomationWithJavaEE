/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.controller.GetAvaliableAppointments;
import com.ilkgunel.hastaneotomasyonu.controller.SaveAppointments;
import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;
import com.ilkgunel.hastaneotomasyonu.facade.AbstractFacade;
import com.ilkgunel.hastaneotomasyonu.facade.RandevuSaatleriFacade;
import com.ilkgunel.hastaneotomasyonu.facade.TakenAppointmentsFacade;
import com.ilkgunel.hastaneotomasyonu.facade.UygunRandevularFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author 010533
 */
public class SaveAppointmentsService{
    @Autowired
    private SavePatientsService savePatientsService;
    
    @Autowired
    RandevuSaatleriFacade randevuSaatleriFacade;
    
    @Autowired
    UygunRandevularFacade uygunRandevularFacade;
    
    @Autowired
    TakenAppointmentsFacade takenAppointmentsFacade;
    
    public void saveAppointmentToDb(Takenappointments takenappointments) throws Exception{
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        List<Randevusaatleri> appointmentClockResults =new ArrayList<>();
        String hour="";
        int randevuid=0;
        
        appointmentClockResults = randevuSaatleriFacade.findListByNamedQuery("Randevusaatleri.findAll");
        for (Randevusaatleri r  : appointmentClockResults) {
            if(r.getSaatid()==takenappointments.getClockid())
            {	
                System.out.println("Saatin ID'si:"+takenappointments.getClockid());
                hour=r.getSaat();
                randevuid=r.getRandevuid();
                break;
            }
        }
        
        takenappointments.setHour(hour);
        //TypedQuery<Uygunrandevular> appointmentdIdQuery=em.createQuery("SELECT u FROM Uygunrandevular u WHERE u.uygunrandevuid=:appointmentid",Uygunrandevular.class);
        List<Uygunrandevular> appointmentIdResults =new ArrayList<>();
        Map parameters = new HashMap();
        parameters.put("uygunrandevuid", randevuid);
       // appointmentdIdQuery.setParameter("appointmentid", randevuid);
        appointmentIdResults=uygunRandevularFacade.findListByNamedQuery("Uygunrandevular.findByUygunrandevuid", parameters);
        
        for(Uygunrandevular u:appointmentIdResults)
        {
            takenappointments.setDate(u.getTarih());
        }
        
        
            /*Query updateQuery=em.createQuery("UPDATE Randevusaatleri r SET r.saatalindimi=TRUE,r.title='DOLU' WHERE r.saatid=:clockId");
            updateQuery.setParameter("clockId", Integer.parseInt(clockId));
            int updateCount = query.executeUpdate();	
            if (updateCount > 0) {
                System.out.println("Done...");
            }*/
            //Randevusaatleri r=em.find(Randevusaatleri.class, Integer.parseInt(clockId));
            Randevusaatleri r = randevuSaatleriFacade.find(takenappointments.getClockid());
            r.setSaatalindimi(true);
            r.setTitle("DOLU");
            //em.getTransaction().commit();
            
            String operationResult = takenAppointmentsFacade.create(takenappointments);
            
        

        
        GetAvaliableAppointments getAvaliableAppointments=(GetAvaliableAppointments) context.getBean("getAvaliableAppointments");
        getAvaliableAppointments.changeRenderingStates();
    }
}
