/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.facade;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 010533
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected abstract EntityManager getEntityManager();
    
    @Transactional
    public String create(T entity) {
        String message = "";
        try {
            getEntityManager().persist(entity);
            message = "Record Inserted Successfully";
        } 
        catch (Exception e) {
            message = "An error occured! Error is:"+e;
        }
        return message;
    }
    
    @Transactional
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    @Transactional
    public List<T> findListByNamedQuery(String namedQuery) throws Exception {
        Query q = getEntityManager().createNamedQuery(namedQuery);
        return q.getResultList();
    }
    
    @Transactional
    public List<T> findListByNamedQuery(String namedQuery, Map parameters) throws Exception {
        Query q = getEntityManager().createNamedQuery(namedQuery);
        Iterator iterator = parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            q.setParameter(entry.getKey().toString(), entry.getValue());
        }
        return q.getResultList();
    }
    
    @Transactional
    public void updateMemberInfo(T entity){
        getEntityManager().merge(entity);
    }
}
