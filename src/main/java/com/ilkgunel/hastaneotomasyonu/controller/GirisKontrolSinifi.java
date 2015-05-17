package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.logging.Logger;
import java.util.logging.Level;
@ManagedBean(name="girisKontrolSnifi")
@ViewScoped
public class GirisKontrolSinifi implements Serializable{

    String identityNumber="guest";
    String password="12345";

    public String getIdentityNumber() {
        System.out.println("Döndürülen İsim:"+identityNumber);
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPassword() {
        System.out.println("Döndürülen Şifre:"+password);
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login()  {

        try {

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            RequestDispatcher dispatcher = ((ServletRequest)context.getRequest()).getRequestDispatcher("/j_spring_security_check");

            dispatcher.forward((ServletRequest)context.getRequest(), (ServletResponse)context.getResponse());
            FacesContext.getCurrentInstance().responseComplete();

        } catch (ServletException | IOException ex) {
            Logger.getLogger(GirisKontrolSinifi.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            return null;
        }
    }

    public void authorizedUserControl(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.getPrincipal().toString().equals("anonymousUser")){

            NavigationHandler nh =  FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
            nh.handleNavigation(FacesContext.getCurrentInstance(), null, "/takingAppointment.xhtml?faces-redirect=true");

        }
    }
    
}
