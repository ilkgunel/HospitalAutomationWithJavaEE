    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.service.DistrictService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean(name="getDistricts")
@ViewScoped
public class GetDistricts implements Serializable {
 
    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments saveAppointments;
            
    String currentCity;
    
    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public SaveAppointments getSaveAppointments() {
        return saveAppointments;
    }

    public void setSaveAppointments(SaveAppointments saveAppointments) {
        this.saveAppointments = saveAppointments;
    }
    
    public List<String> fillList() throws Exception
    {
        System.out.println("GetDistricts FillList metodu çalıştı!");
        ApplicationContext context=FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        DistrictService districtService = (DistrictService) context.getBean("districtService");
        return districtService.getAllDistrictNames(saveAppointments.getCurrentCity());       
    }
}
