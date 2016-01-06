/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import com.ilkgunel.hastaneotomasyonu.entity.Patients;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.PersistenceContext;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean
@RequestScoped
public class SavePatients implements Serializable{
    
    @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;
    
    private Patients patientsObject=new Patients();

    public Patients getPatientsObject() throws ParseException{
        /*String date="01/01/1980";
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        patientsObject.setBirthdate(sdf.parse(date));*/
        return patientsObject;
    }

    public void setPatientsObject(Patients patientsObject) {
        this.patientsObject = patientsObject;
    }
    
    private String mobilePhoneNumber;
    private String homePhoneNumber;
    

    private String operationResult;
    private Md5PasswordEncoder passwordEncoder;

    /*private EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
    private EntityManager em=emf.createEntityManager();*/

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

    

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public void saveToDb(ActionEvent event)
    {
        System.err.println("Save To DB Metoduna Girildi!!!!");
        if (mobilePhoneNumber!=null&&homePhoneNumber==null)
        {
            patientsObject.setPhonenumber(mobilePhoneNumber);
        }
        else if(mobilePhoneNumber==null&&homePhoneNumber!=null)
        {
            patientsObject.setPhonenumber(mobilePhoneNumber);
        }
        else if (mobilePhoneNumber!=null&&homePhoneNumber!=null)
        {
            patientsObject.setPhonenumber(mobilePhoneNumber);
        }
        
        System.err.println("Doğum Tarihi"+patientsObject.getBirthdate());
        java.sql.Date sqlBirthDate =new java.sql.Date(patientsObject.getBirthdate().getTime());
        patientsObject.setBirthdate(sqlBirthDate);
        
        try
        {
            MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
            messageDigestNesnesi.update(patientsObject.getPassword().getBytes());
            byte messageDigestDizisi[] = messageDigestNesnesi.digest();
            StringBuffer sb16 = new StringBuffer();
            StringBuffer sb32 = new StringBuffer();
            for (int i = 0; i < messageDigestDizisi.length; i++)
            {
                sb16.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 16).substring(1));
                patientsObject.setPassword(sb16.toString());
            }
        }
        catch(NoSuchAlgorithmException ex){
            System.err.println(ex);
        }

        patientsObject.setRole("ROLE_GUEST");
        patientsObject.setEnabled(true);

        try {
            em.getTransaction().begin();
            em.persist(patientsObject);
            em.getTransaction().commit();
            operationResult="Bilgileriniz Kaydedildi. Sisteme Giriş Yapıp Randevu Alabilirsiniz";
        } catch (Exception e) {
            System.err.println(e);
            operationResult="Bilgilerin Kaydı Sırasında Bir Hata Meydana Geldi!";
        }

    }
}
