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
import com.ilkgunel.hastaneotomasyonu.facade.CitiesFacade;
import com.ilkgunel.hastaneotomasyonu.service.CitiesService;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author ilkaygunel
 */
@ManagedBean(name="getCities")
@ViewScoped
public class GetCities implements Serializable {
    
    public List<String> fillList()
    {
        ApplicationContext context = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        CitiesService citiesService = (CitiesService) context.getBean("citiesService");
        return citiesService.getAllCityNames();
    }   
}
