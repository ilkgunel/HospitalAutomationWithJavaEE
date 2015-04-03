package com.ilkgunel.hastaneotomasyonu;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="girisKontrolSnifi")
@SessionScoped
public class GirisKontrolSinifi {

     String kimlikNo;
     String parola;

    public String getKimlikNo() {
        return kimlikNo;
    }

    public void setKimlikNo(String kimlikNo) {
        this.kimlikNo = kimlikNo;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }
     
    public String login()
    {
        if((kimlikNo.equals("11111111111"))&&(parola.equals("12345")))
        {
           return "takingAppointment.xhtml";      
        }
        else
        {
        return "index.xhtml";
        }
    }
}
