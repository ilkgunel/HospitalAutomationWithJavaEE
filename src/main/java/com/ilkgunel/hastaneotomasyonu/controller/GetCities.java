/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import com.ilkgunel.hastaneotomasyonu.entity.Iller;

/**
 *
 * @author ilkaygunel
 */
@ManagedBean(name="getCities")
@SessionScoped
public class GetCities implements Serializable {
    
    @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;
    
    List<String> cities;
    List<Iller> cityResults;
    int cityId;
    
    public List<Iller> getAllResults()
    {
        return cityResults;
    }

    public void setAllResults(List<Iller> cityResults)
    {
        this.cityResults = cityResults;
    }

    public int getCityId()
    {
        return cityId;
    }

    public void setCityId(int cityId)
    {
        this.cityId = cityId;
    }
    
    public List<String> getCities()
    {
        
        return cities;
    }

    public void setCities(List<String> cities)
    {
        this.cities = cities;
    }
    
    @PostConstruct
    public void fillList()
    {
        cities=new ArrayList<>();
        cityResults=new ArrayList<>();
        /*EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
        EntityManager em=emf.createEntityManager();*/
        TypedQuery<Iller> query=em.createQuery("SELECT i FROM Iller i",Iller.class);
        cityResults=query.getResultList();
        
        for (Iller i:cityResults)
        {
            cities.add(i.getSehir());
        }
         
    }
    
}
