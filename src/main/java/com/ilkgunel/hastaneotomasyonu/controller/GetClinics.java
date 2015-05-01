package com.ilkgunel.hastaneotomasyonu.controller;

import com.ilkgunel.hastaneotomasyonu.entity.Klinikler;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.bean.ManagedProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@ManagedBean
@ViewScoped
public class GetClinics implements Serializable{

     List<Klinikler> clinicResults;
     List<String> clinics;

    @ManagedProperty(value = "#{saveAppointments}")
    private SaveAppointments sao;

    public SaveAppointments getSao() {
        return sao;
    }

    public void setSao(SaveAppointments sao) {
        this.sao = sao;
    }

    public List<Klinikler> getClinicResults() {
        return clinicResults;
    }

    public void setClinicResults(List<Klinikler> clinicResults) {
        this.clinicResults = clinicResults;
    }

    public List<String> getClinics() {
        return clinics;
    }

    public void setClinics(List<String> clinics) {
        this.clinics = clinics;
    }

    public void fillList() throws Exception
    {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("HospitalAutomation");
        EntityManager em=emf.createEntityManager();
        TypedQuery<Klinikler> query=em.createQuery("select k from Klinikler k",Klinikler.class);
        clinicResults=new ArrayList<>();
        clinics=new ArrayList<>();
        clinicResults=query.getResultList();
        for (Klinikler k:clinicResults)
        {
            clinics.add(k.getKlinikadi());
        }
    }
}
