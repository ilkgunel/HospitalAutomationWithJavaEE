/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.facade;

import com.ilkgunel.hastaneotomasyonu.entity.Hastaneler;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author 010533
 */
public class HospitalFacade extends AbstractFacade<Hastaneler>{
    @PersistenceContext
    private EntityManager em;
    
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public HospitalFacade(){
        super(Hastaneler.class);
    }
}
