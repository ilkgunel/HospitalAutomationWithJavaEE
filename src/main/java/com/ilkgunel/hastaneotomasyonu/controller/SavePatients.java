/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.ilkgunel.hastaneotomasyonu.entity.Patients;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ilkaygunel
 */
@ManagedBean
@ViewScoped
public class SavePatients implements Serializable{
    
    /*@PersistenceContext(unitName = "HospitalAutomation")
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
    
    private String operationResult;
    private Md5PasswordEncoder passwordEncoder;
    
    private EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
    private EntityManager em=emf.createEntityManager();
        
    
    Matcher matcher;

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

    public Date getBirthDate() throws ParseException{
        String date="01/01/1980";
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        birthDate=sdf.parse(date);
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

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }
    
    public void saveToDb()
    {
        Patients patientsObject=new Patients();
        patientsObject.setIdentitynumber(identityNumber);
        System.out.println("İsim:"+name);
        patientsObject.setName(name);
        System.out.println("Soyİsim:"+surname);
        patientsObject.setSurname(surname);
        patientsObject.setGender(gender);
         System.out.println("Doğum Yeri:"+birthplace);
        patientsObject.setBirthplace(birthplace);
        
        java.sql.Date sqlBirDate =new java.sql.Date(birthDate.getTime());
        patientsObject.setBirthdate(sqlBirDate);
        
        System.out.println("Baba Adı:"+fatherName);
        patientsObject.setFathername(fatherName);
        patientsObject.setMothername(motherName);
        
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
        
        patientsObject.setEmailaddress(emailAddress);   
        try
        {
            MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
            messageDigestNesnesi.update(password.getBytes());
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
