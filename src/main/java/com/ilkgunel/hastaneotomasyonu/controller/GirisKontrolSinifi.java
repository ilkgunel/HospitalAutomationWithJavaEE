package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.User;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="girisKontrolSnifi")
@ViewScoped
public class GirisKontrolSinifi implements Serializable{

    User user = new User();
        
    public String login()
    {
        if(user.getIdentityNumber().equals("1111")&&user.getPassword().equals("12345"))
        {
           return "takingAppointment.xhtml?faces-redirect=true";
        }
        else
        {
            System.out.println("Sistem yönlenemedi.");
            return "index.xhtml";
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
