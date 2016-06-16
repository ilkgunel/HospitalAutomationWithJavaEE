/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Patients;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 010533
 */
public class SavePatientsWithSpring {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public String returnOperationResult(Patients patientsObject){
        
        em.persist(patientsObject);
        
        return "Kayıt Başarı İle Eklendi!";
    }
}
