package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;
import com.ilkgunel.hastaneotomasyonu.service.ClinicService;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.faces.context.FacesContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@SessionScoped
public class GetClinics implements Serializable{

    public List<String> fillList() throws Exception
    {
        ApplicationContext context= FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        ClinicService clinicService = (ClinicService) context.getBean("clinicService");
        return clinicService.getAllClinicNames();
    }
}
