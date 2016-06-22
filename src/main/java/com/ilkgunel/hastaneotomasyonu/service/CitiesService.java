/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.service;

import com.ilkgunel.hastaneotomasyonu.entity.Iller;
import com.ilkgunel.hastaneotomasyonu.facade.CitiesFacade;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 010533
 */
public class CitiesService {
    @Autowired
    private CitiesFacade citiesFacade;
    
    List<Iller> cityResults;
    List<String> cities;
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
    
    public List<String> getAllCityNames(){
        cities=new ArrayList<>();
        try {
            cityResults = citiesFacade.findListByNamedQuery("Iller.findAll");
        } catch (Exception e) {
            System.err.println("An Error Occured! Error is:"+e);
        }
        
        for (Iller i:cityResults)
        {
            cities.add(i.getSehir());
        }        
        return cities;
    }
}
