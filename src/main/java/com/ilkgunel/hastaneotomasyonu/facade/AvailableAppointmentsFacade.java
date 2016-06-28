/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.facade;

import com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 010533
 */
public class AvailableAppointmentsFacade extends AbstractFacade<Uygunrandevular>{
    @PersistenceContext
    private EntityManager em;
    
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public AvailableAppointmentsFacade(){
        super(Uygunrandevular.class);
    }
}
