package com.ilkgunel.hastaneotomasyonu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.lang.Object;
import com.ilkgunel.hastaneotomasyonu.entity.Patients;
import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
@ManagedBean
@SessionScoped
public class GetPatientInformation implements Serializable{
    
   /* @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;*/
    
    private String identityNumber;
    private String name;
    private String surname;
    private String gender;
    private String birthplace;
    private Date birthDate;
    private String fatherName;
    private String motherName;
    private String mobilePhoneNumber;
    private String homePhoneNumber;
    private String emailAddress;
    private String password;
    
    private String messageForUpdate;
    
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointments;
    
    @ManagedProperty(value="#{getAppointmentsOfPatient}")
    private GetAppointmentsOfPatient g;
    
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
    EntityManager em=emf.createEntityManager();
    
    //List<Patient> patientInfo;
    List<Takenappointments> takenAppointmentsOfPatient;

    public List<Takenappointments> getTakenAppointmentsOfPatient() {
        return takenAppointmentsOfPatient;
    }

    public void setTakenAppointmentsOfPatient(List<Takenappointments> takenAppointmentsOfPatient) {
        this.takenAppointmentsOfPatient = takenAppointmentsOfPatient;
    }
    

    public void fillList(ActionEvent event)
    {
        TypedQuery<Patients> query=em.createQuery("SELECT p FROM Patients p WHERE p.identitynumber=:patientid",Patients.class);
        query.setParameter("patientid", saveAppointments.comingIdentityNumber);
        //patientInfo=new ArrayList<Patient>();
        //patientInfo=query.getResultList();

        for (Patients p : query.getResultList()) 
        {
            setIdentityNumber(p.getIdentitynumber());
            setName(p.getName());
            setSurname(p.getSurname());
            setGender(p.getGender());
            setBirthplace(p.getBirthplace());
            setBirthDate(p.getBirthdate());
            setFatherName(p.getFathername());
            setMotherName(p.getMothername());
            setMobilePhoneNumber(p.getPhonenumber());
            setHomePhoneNumber(p.getPhonenumber());
            setEmailAddress(p.getEmailaddress());
            setPassword(p.getPassword());
        }
        g.fillList();
    }

    /*public List<Patient> getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(List<Patient> patientInfo)
    {
        this.patientInfo = patientInfo;
    }*/

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SaveAppointments getSaveAppointments() {
        return saveAppointments;
    }

    public void setSaveAppointments(SaveAppointments saveAppointments) {
        this.saveAppointments = saveAppointments;
    }



    public void updatePatientInfo()
    {
        try {
            em.getTransaction().begin();
            Patients p = em.find(Patients.class, identityNumber);

            p.setName(name);
            System.out.println(p.getName());
            p.setSurname(surname);
            System.out.println(p.getSurname());
            p.setGender(gender);
            p.setBirthplace(birthplace);

            java.sql.Date sqlBirDate =new java.sql.Date(birthDate.getTime());
            p.setBirthdate(sqlBirDate);

            p.setFathername(fatherName);
            p.setMothername(motherName);
            p.setPhonenumber(mobilePhoneNumber);
            p.setEmailaddress(emailAddress);
            em.getTransaction().commit();

            messageForUpdate="Bilgileriniz Başarı İle Güncellendi!";

        }
        catch (Exception ex)
        {
            System.out.println("\nBir hata meydana geldi:\n"+ex);
            messageForUpdate="Güncelleme Sırasında Bir Hata Meydana Geldi";
        }
    }

    public String getMessageForUpdate() {
        return messageForUpdate;
    }

    public void setMessageForUpdate(String messageForUpdate) {
        this.messageForUpdate = messageForUpdate;
    }

    public GetAppointmentsOfPatient getG() {
        return g;
    }

    public void setG(GetAppointmentsOfPatient g) {
        this.g = g;
    }
    
    
}
