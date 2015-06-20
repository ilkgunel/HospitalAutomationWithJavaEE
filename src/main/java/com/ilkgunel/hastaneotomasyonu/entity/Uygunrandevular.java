/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilkaygunel
 */
@Entity
@Table(name = "uygunrandevular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uygunrandevular.findAll", query = "SELECT u FROM Uygunrandevular u"),
    @NamedQuery(name = "Uygunrandevular.findByUygunrandevuid", query = "SELECT u FROM Uygunrandevular u WHERE u.uygunrandevuid = :uygunrandevuid"),
    @NamedQuery(name = "Uygunrandevular.findByDoktorid", query = "SELECT u FROM Uygunrandevular u WHERE u.doktorid = :doktorid"),
    @NamedQuery(name = "Uygunrandevular.findByTarih", query = "SELECT u FROM Uygunrandevular u WHERE u.tarih = :tarih"),
    @NamedQuery(name = "Uygunrandevular.findByHastaneid", query = "SELECT u FROM Uygunrandevular u WHERE u.hastaneid = :hastaneid"),
    @NamedQuery(name = "Uygunrandevular.findByKlinikid", query = "SELECT u FROM Uygunrandevular u WHERE u.klinikid = :klinikid"),
    @NamedQuery(name = "Uygunrandevular.findByKlinikyeri", query = "SELECT u FROM Uygunrandevular u WHERE u.klinikyeri = :klinikyeri")})
public class Uygunrandevular implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "randevualindimi")
    private boolean randevualindimi;
    @Size(max = 50)
    @Column(name = "doktoradi")
    private String doktoradi;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uygunrandevuid")
    private Integer uygunrandevuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doktorid")
    private int doktorid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarih")
    @Temporal(TemporalType.DATE)
    private Date tarih;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hastaneid")
    private int hastaneid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "klinikid")
    private int klinikid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "klinikyeri")
    private String klinikyeri;

    public Uygunrandevular() {
    }

    public Uygunrandevular(Integer uygunrandevuid) {
        this.uygunrandevuid = uygunrandevuid;
    }

    public Uygunrandevular(Integer uygunrandevuid, int doktorid, Date tarih, int hastaneid, int klinikid, String klinikyeri) {
        this.uygunrandevuid = uygunrandevuid;
        this.doktorid = doktorid;
        this.tarih = tarih;
        this.hastaneid = hastaneid;
        this.klinikid = klinikid;
        this.klinikyeri = klinikyeri;
    }

    public Integer getUygunrandevuid() {
        return uygunrandevuid;
    }

    public void setUygunrandevuid(Integer uygunrandevuid) {
        this.uygunrandevuid = uygunrandevuid;
    }

    public int getDoktorid() {
        return doktorid;
    }

    public void setDoktorid(int doktorid) {
        this.doktorid = doktorid;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public int getHastaneid() {
        return hastaneid;
    }

    public void setHastaneid(int hastaneid) {
        this.hastaneid = hastaneid;
    }

    public int getKlinikid() {
        return klinikid;
    }

    public void setKlinikid(int klinikid) {
        this.klinikid = klinikid;
    }

    public String getKlinikyeri() {
        return klinikyeri;
    }

    public void setKlinikyeri(String klinikyeri) {
        this.klinikyeri = klinikyeri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uygunrandevuid != null ? uygunrandevuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uygunrandevular)) {
            return false;
        }
        Uygunrandevular other = (Uygunrandevular) object;
        return !((this.uygunrandevuid == null && other.uygunrandevuid != null) || (this.uygunrandevuid != null && !this.uygunrandevuid.equals(other.uygunrandevuid)));
    }

    @Override
    public String toString() {
        return "com.ilkgunel.hastaneotomasyonu.entity.Uygunrandevular[ uygunrandevuid=" + uygunrandevuid + " ]";
    }


    public String getDoktoradi() {
        return doktoradi;
    }

    public void setDoktoradi(String doktoradi) {
        this.doktoradi = doktoradi;
    }

    public boolean getRandevualindimi() {
        return randevualindimi;
    }

    public void setRandevualindimi(boolean randevualindimi) {
        this.randevualindimi = randevualindimi;
    }
    
}
