package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceContext;
//Çalışan Sınıf Budur
@ManagedBean(name = "getAvaliableAppointments")
@SessionScoped
public class GetAvaliableAppointments implements Serializable{
    
    @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;
    
    
    /*EntityManagerFactory emf= Persistence.createEntityManagerFactory("HospitalAutomation");
    EntityManager em=emf.createEntityManager();*/
        
    List<Uygunrandevular> availableAppointments;
    List<Object[]> doctorAndTimeList;
    List<Randevusaatleri> appointmentClockResults;
    boolean renderingTakingAppointmentInfo=true;
    boolean renderingClocks=false;
    boolean renderingDataTable=false;
    int hospitalid;
    //@ManagedProperty(value = "#{saveAppointments}")
    //private SaveAppointments saveAppointmentsObjectInAvaliableAppointments;

    /*@ManagedProperty(value = "#{getHospitals}")
    private GetHospitals getHospitalsObject;*/
    
    //@ManagedProperty(value = "#{getClinics}")
    //private GetClinics getClinicsObject;

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
    

    public boolean isRenderingDataTable() {
        return renderingDataTable;
    }

    public void setRenderingDataTable(boolean renderingDataTable) {
        this.renderingDataTable = renderingDataTable;
    }

    /*public GetHospitals getGetHospitalsObject()
    {
        return getHospitalsObject;
    }

    public void setGetHospitalsObject(GetHospitals getHospitalsObject)
    {
        this.getHospitalsObject = getHospitalsObject;
    }*/

    public List<Uygunrandevular> getAvailableAppointments() {
        return availableAppointments;
    }

    public void setAvailableAppointments(List<Uygunrandevular> availableAppointments) {
        this.availableAppointments = availableAppointments;
    }

    public List<Object[]> getDoctorAndTimeList() {
        return doctorAndTimeList;
    }

    public void setDoctorAndTimeList(List<Object[]> doctorAndTimeList) {
        this.doctorAndTimeList = doctorAndTimeList;
    }

   /* public GetClinics getGetClinicsObject() {
        return getClinicsObject;
    }

    public void setGetClinicsObject(GetClinics getClinicsObject) {
        this.getClinicsObject = getClinicsObject;
    }*/

    public List<Randevusaatleri> getAppointmentClockResults() {
        return appointmentClockResults;
    }

    public void setAppointmentClockResults(List<Randevusaatleri> appointmentClockResults) {
        this.appointmentClockResults = appointmentClockResults;
    }
    
    public void fillList()
    {
        availableAppointments=new ArrayList<>();

        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        GetHospitals getHospitalsObject=(GetHospitals) context.getBean("getHospitals");
        SaveAppointments saveAppointmentsObjectInAvaliableAppointments=(SaveAppointments) context.getBean("saveAppointments");
        GetClinics getClinicsObject=(GetClinics) context.getBean("getClinics");

        for (Hastaneler h:getHospitalsObject.getHospitalResults())
        {
            if(h.getHastaneadi().equals(saveAppointmentsObjectInAvaliableAppointments.getHospital()))
            {
                hospitalid=h.getId();
                break;
            }
        }
        
        int clinicId=0;
        for(Klinikler k:getClinicsObject.clinicResults)
        {
            if(k.getKlinikadi().equals(saveAppointmentsObjectInAvaliableAppointments.clinic))
            {
                clinicId=k.getId();
                break;
            }
        }
        
        TypedQuery<Uygunrandevular> query=em.createQuery("SELECT u FROM Uygunrandevular AS u WHERE u.hastaneid=:hospitalid AND u.klinikid=:clinicid AND u.klinikyeri=:clinicplace "
                + "AND u.tarih = (select min(uu.tarih) from Uygunrandevular uu where uu.doktorid = u.doktorid)",Uygunrandevular.class);
        
        
        query.setParameter("hospitalid",hospitalid);
        query.setParameter("clinicid", clinicId);
        query.setParameter("clinicplace", saveAppointmentsObjectInAvaliableAppointments.clinicPlace);

        availableAppointments=query.getResultList();

        setRenderingDataTable(true);

    }

    public void changeRenderingStates()
    {

        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        SaveAppointments saveAppointmentsObjectInAvaliableAppointments=(SaveAppointments) context.getBean("saveAppointments");


        TypedQuery<Object[]> doctorAndTimeQuery = em.createQuery("SELECT u.doktoradi,FUNCTION('DATE',u.tarih),u.uygunrandevuid FROM Uygunrandevular AS u WHERE u.doktorid=:doctorid ORDER BY u.tarih ASC",Object[].class);
        doctorAndTimeQuery.setParameter("doctorid", saveAppointmentsObjectInAvaliableAppointments.selectedAppointment.getDoktorid());
        doctorAndTimeList=new ArrayList<>();
        doctorAndTimeList=doctorAndTimeQuery.getResultList();
        
        TypedQuery<Randevusaatleri> query=em.createQuery("SELECT c FROM Randevusaatleri c WHERE c.doktorid=:doctorid",Randevusaatleri.class);
        System.out.println("Seçilen Randevunun ID'si"+saveAppointmentsObjectInAvaliableAppointments.selectedAppointment.getUygunrandevuid());
        query.setParameter("doctorid", saveAppointmentsObjectInAvaliableAppointments.selectedAppointment.getDoktorid());
        appointmentClockResults=new ArrayList<>();
        appointmentClockResults=query.getResultList();
        
        setRenderingTakingAppointmentInfo(false);
        setRenderingClocks(true);
        
    }

    /*public SaveAppointments getSaveAppointmentsObjectInAvaliableAppointments() {
        return saveAppointmentsObjectInAvaliableAppointments;
    }

    public void setSaveAppointmentsObjectInAvaliableAppointments(SaveAppointments saveAppointmentsObjectInAvaliableAppointments) {
        this.saveAppointmentsObjectInAvaliableAppointments = saveAppointmentsObjectInAvaliableAppointments;
    }*/
}

