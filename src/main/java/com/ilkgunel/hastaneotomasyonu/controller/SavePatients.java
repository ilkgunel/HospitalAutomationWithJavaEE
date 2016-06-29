/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.service.SavePatientsService;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.bean.ManagedBean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import com.ilkgunel.hastaneotomasyonu.entity.Patients;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean(name = "savePatients")
@ViewScoped
public class SavePatients implements Serializable{
    private Patients patientsObject;
    
    
    public SavePatients(){
        patientsObject=new Patients();
    }        

    public void setPatientsObject(Patients patientsObject) {
        this.patientsObject = patientsObject;
    }
    
    private String mobilePhoneNumber;
    private String homePhoneNumber;
    

    private String operationResult;
    private Md5PasswordEncoder passwordEncoder;

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

    public Patients getPatientsObject() {
        return patientsObject;
    }
    
    public void saveToDb(ActionEvent event) throws Exception
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
        
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        SavePatientsService savePatientsService = (SavePatientsService) context.getBean("savePatientsService");
        
        
        try {
            operationResult = savePatientsService.returnOperationResult(patientsObject);
        } 
        catch (Exception e) {
            operationResult = "Kayıt Sırasında Bir Hata Meydana Geldi\nHata:"+e;
        }
    }
}
