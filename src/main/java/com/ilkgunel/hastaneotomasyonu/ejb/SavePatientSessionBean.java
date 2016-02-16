/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.ejb;

import com.ilkgunel.hastaneotomasyonu.entity.Patients;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ilkaygunel
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SavePatientSessionBean implements SavePatientSessionBeanLocal {

    @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String savePatient(Patients patients) {
        try 
        {
            em.persist(patients);
            //return "Bilgileriniz Kaydedildi. Sisteme Giriş Yapıp Randevu Alabilirsiniz";
            return "Everything is fine. Registering is succesful";
        } catch (Exception e) {
            System.err.println(e);
            //return "Bilgilerin Kaydı Sırasında Bir Hata Meydana Geldi!";
            return  "An Error Occured! Please Look Log!";
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void WriteToConsole()
    {
        System.err.println("SavePatientSessionBean içindeki WriteToConsole() metodu çalıştı.");
    }
}
