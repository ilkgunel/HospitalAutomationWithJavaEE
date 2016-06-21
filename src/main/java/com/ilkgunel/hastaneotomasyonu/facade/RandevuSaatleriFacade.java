/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.facade;

import com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 010533
 */
public class RandevuSaatleriFacade extends AbstractFacade<Randevusaatleri>{
    @PersistenceContext
    EntityManager em;
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public RandevuSaatleriFacade(){
        super(Randevusaatleri.class);
    }
}
