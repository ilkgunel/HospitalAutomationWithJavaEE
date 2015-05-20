/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilkaygunel
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Randevusaatleri.findAll", query = "SELECT r FROM Randevusaatleri r"),
    @NamedQuery(name = "Randevusaatleri.findBySaatid", query = "SELECT r FROM Randevusaatleri r WHERE r.saatid = :saatid"),
    @NamedQuery(name = "Randevusaatleri.findBySaat", query = "SELECT r FROM Randevusaatleri r WHERE r.saat = :saat"),
    @NamedQuery(name = "Randevusaatleri.findBySaatalindimi", query = "SELECT r FROM Randevusaatleri r WHERE r.saatalindimi = :saatalindimi"),
    @NamedQuery(name = "Randevusaatleri.findByRandevuid", query = "SELECT r FROM Randevusaatleri r WHERE r.randevuid = :randevuid"),
    @NamedQuery(name = "Randevusaatleri.findByDoktorid", query = "SELECT r FROM Randevusaatleri r WHERE r.doktorid = :doktorid"),
    @NamedQuery(name = "Randevusaatleri.findByTitle", query = "SELECT r FROM Randevusaatleri r WHERE r.title = :title")})
public class Randevusaatleri implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer saatid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    private String saat;
    @Basic(optional = false)
    @NotNull
    private boolean saatalindimi;
    @Basic(optional = false)
    @NotNull
    private int randevuid;
    @Basic(optional = false)
    @NotNull
    private int doktorid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    private String title;

    public Randevusaatleri() {
    }

    public Randevusaatleri(Integer saatid) {
        this.saatid = saatid;
    }

    public Randevusaatleri(Integer saatid, String saat, boolean saatalindimi, int randevuid, int doktorid, String title) {
        this.saatid = saatid;
        this.saat = saat;
        this.saatalindimi = saatalindimi;
        this.randevuid = randevuid;
        this.doktorid = doktorid;
        this.title = title;
    }

    public Integer getSaatid() {
        return saatid;
    }

    public void setSaatid(Integer saatid) {
        this.saatid = saatid;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public boolean getSaatalindimi() {
        return saatalindimi;
    }

    public void setSaatalindimi(boolean saatalindimi) {
        this.saatalindimi = saatalindimi;
    }

    public int getRandevuid() {
        return randevuid;
    }

    public void setRandevuid(int randevuid) {
        this.randevuid = randevuid;
    }

    public int getDoktorid() {
        return doktorid;
    }

    public void setDoktorid(int doktorid) {
        this.doktorid = doktorid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saatid != null ? saatid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Randevusaatleri)) {
            return false;
        }
        Randevusaatleri other = (Randevusaatleri) object;
        if ((this.saatid == null && other.saatid != null) || (this.saatid != null && !this.saatid.equals(other.saatid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ilkgunel.hastaneotomasyonu.entity.Randevusaatleri[ saatid=" + saatid + " ]";
    }
    
}
