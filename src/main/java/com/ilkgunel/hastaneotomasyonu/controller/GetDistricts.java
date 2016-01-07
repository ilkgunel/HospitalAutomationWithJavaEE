    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Ilceler;
import com.ilkgunel.hastaneotomasyonu.entity.Iller;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;
/**
 *
 * @author ilkaygunel
 */
@Component
@ManagedBean(name="getDistricts")
@SessionScoped
public class GetDistricts implements Serializable {
    
    @PersistenceContext(unitName = "HospitalAutomation")
    private EntityManager em;
    
    List<String> districts;
    List<Ilceler> districtResults;
    int cityId=0;
    String currentCity;
    
    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }
    
    public int getId() {
        return cityId;
    }

    public void setId(int cityId) {
        this.cityId = cityId;
    }
    

    /*public GetCities getGetCities() {
        return getCities;
    }

    public void setGetCities(GetCities getCities) {
        this.getCities = getCities;
    }*/

    public List<String> getDistricts() {
        return districts;
    }

    public void setDistricts(List<String> districts) {
        this.districts = districts;
    }

    public List<Ilceler> getDistrictResults() {
        return districtResults;
    }

    public void setDistrictResults(List<Ilceler> districtResults) {
        this.districtResults = districtResults;
    }
    
    public void fillList()
    {
        ApplicationContext context=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
                                                                        
        GetCities getCities=(GetCities) context.getBean("getCities");
        for(Iller i:getCities.cityResults)
        {
            if(i.getSehir().equals(currentCity))
            {
                cityId=i.getId();
                break;
            }
        }
        
        //EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
        //EntityManager em=emf.createEntityManager();
        TypedQuery<Ilceler> query=em.createQuery("SELECT i FROM Ilceler i WHERE i.sehir=:value",Ilceler.class);
        query.setParameter("value", cityId);
        districts=new ArrayList<>();
        districtResults=new ArrayList<>();
        districtResults=query.getResultList();
        
        for(Ilceler i:districtResults)
        {
            districts.add(i.getIlce());
        }
    }
}
