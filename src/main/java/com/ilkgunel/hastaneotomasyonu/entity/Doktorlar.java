/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilkaygunel
 */
@Entity
@Table(name = "doktorlar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doktorlar.findAll", query = "SELECT d FROM Doktorlar d"),
    @NamedQuery(name = "Doktorlar.findByDoktorid", query = "SELECT d FROM Doktorlar d WHERE d.doktorid = :doktorid"),
    @NamedQuery(name = "Doktorlar.findByBransid", query = "SELECT d FROM Doktorlar d WHERE d.bransid = :bransid"),
    @NamedQuery(name = "Doktorlar.findByHastaneid", query = "SELECT d FROM Doktorlar d WHERE d.hastaneid = :hastaneid"),
    @NamedQuery(name = "Doktorlar.findByDoktoradi", query = "SELECT d FROM Doktorlar d WHERE d.doktoradi = :doktoradi"),
    @NamedQuery(name = "Doktorlar.findByBransIdAndHastaneID",query="SELECT d FROM Doktorlar d WHERE d.bransid=:clinicid AND d.hastaneid=:hospitalid")})
public class Doktorlar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "doktorid")
    private Integer doktorid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bransid")
    private int bransid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hastaneid")
    private int hastaneid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "doktoradi")
    private String doktoradi;

    public Doktorlar() {
    }

    public Doktorlar(Integer doktorid) {
        this.doktorid = doktorid;
    }

    public Doktorlar(Integer doktorid, int bransid, int hastaneid, String doktoradi) {
        this.doktorid = doktorid;
        this.bransid = bransid;
        this.hastaneid = hastaneid;
        this.doktoradi = doktoradi;
    }

    public Integer getDoktorid() {
        return doktorid;
    }

    public void setDoktorid(Integer doktorid) {
        this.doktorid = doktorid;
    }

    public int getBransid() {
        return bransid;
    }

    public void setBransid(int bransid) {
        this.bransid = bransid;
    }

    public int getHastaneid() {
        return hastaneid;
    }

    public void setHastaneid(int hastaneid) {
        this.hastaneid = hastaneid;
    }

    public String getDoktoradi() {
        return doktoradi;
    }

    public void setDoktoradi(String doktoradi) {
        this.doktoradi = doktoradi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doktorid != null ? doktorid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doktorlar)) {
            return false;
        }
        Doktorlar other = (Doktorlar) object;
        return !((this.doktorid == null && other.doktorid != null) || (this.doktorid != null && !this.doktorid.equals(other.doktorid)));
    }

    @Override
    public String toString() {
        return "com.ilkgunel.hastaneotomasyonu.entity.Doktorlar[ doktorid=" + doktorid + " ]";
    }
    
}
