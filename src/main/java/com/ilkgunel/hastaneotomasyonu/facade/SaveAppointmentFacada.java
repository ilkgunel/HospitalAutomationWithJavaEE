/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.facade;

import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 010533
 */
public class SaveAppointmentFacada extends AbstractFacade<Takenappointments>{
    public SaveAppointmentFacada(){
        super(Takenappointments.class);
    }
    
    @PersistenceContext
    private EntityManager em;
    
    protected EntityManager getEntityManager(){
        return em;
    }
}
