/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Ilceler;
import com.ilkgunel.hastaneotomasyonu.entity.Iller;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ValueChangeEvent;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import javax.annotation.PostConstruct;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean(name="getDistricts")
@ViewScoped
public class GetDistricts implements Serializable {

    
    List<String> districts;
    int id=0;
    String currentCity;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    @ManagedProperty(value="#{saveAppointments}")
    private SaveAppointments saveAppointments;
    
    @ManagedProperty(value="#{getCities}")
    private GetCities getCities;

    public GetCities getGetCities() {
        return getCities;
    }

    public void setGetCities(GetCities getCities) {
        this.getCities = getCities;
    }

    public SaveAppointments getSaveAppointments() {
        return saveAppointments;
    }

    public void setSaveAppointments(SaveAppointments saveAppointments) {
        this.saveAppointments = saveAppointments;
    }
    
    

    public List<String> getDistricts() {
        return districts;
    }

    public void setDistricts(List<String> districts) {
        this.districts = districts;
    }
    
    public void fillList()
    {
        System.out.println("\nGüncel Şehir:"+getCurrentCity());
        for(Iller i:getCities.allResults)
        {
            System.out.println("\n"+i.getSehir());
            if(i.getSehir().equals(currentCity))
            {
                id=i.getId();
                break;
            }
        }
        System.out.println("\n");
        System.out.println("Seçilen İlin ID'si"+id);
        
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
        EntityManager em=emf.createEntityManager();
        TypedQuery<Ilceler> query=em.createQuery("SELECT i FROM Ilceler i WHERE i.sehir=:value",Ilceler.class);
        query.setParameter("value", id);
        districts=new ArrayList<>();
        
        
        for(Ilceler i:query.getResultList())
        {
            districts.add(i.getIlce());
        }
        for(String s:districts)
        {
            System.out.println(s);
        }
    }
}
