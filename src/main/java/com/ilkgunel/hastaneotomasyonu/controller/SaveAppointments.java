/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
/**
 *
 * @author ilkaygunel
 */
@ManagedBean
@ViewScoped
public class SaveAppointments implements Serializable{

    private String city;

    public String getCity() {
        System.out.println("GetCity Metodunun İçindenDöndürülen Değer:"+city);
        return city;
    }

    public void setCity(String city) {
        System.out.println("Set metoduna gelen değer:"+city);
        this.city = city;
        System.out.println("Set edildikden sonraki değer:"+this.city);
    }
    
}
