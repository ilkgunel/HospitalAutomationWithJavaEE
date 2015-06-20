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
@Table(name = "hastaneler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hastaneler.findAll", query = "SELECT h FROM Hastaneler h"),
    @NamedQuery(name = "Hastaneler.findById", query = "SELECT h FROM Hastaneler h WHERE h.id = :id"),
    @NamedQuery(name = "Hastaneler.findByHastaneadi", query = "SELECT h FROM Hastaneler h WHERE h.hastaneadi = :hastaneadi"),
    @NamedQuery(name = "Hastaneler.findByIlceid", query = "SELECT h FROM Hastaneler h WHERE h.ilceid = :ilceid")})
public class Hastaneler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "hastaneadi")
    private String hastaneadi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ilceid")
    private int ilceid;

    public Hastaneler() {
    }

    public Hastaneler(Integer id) {
        this.id = id;
    }

    public Hastaneler(Integer id, String hastaneadi, int ilceid) {
        this.id = id;
        this.hastaneadi = hastaneadi;
        this.ilceid = ilceid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHastaneadi() {
        return hastaneadi;
    }

    public void setHastaneadi(String hastaneadi) {
        this.hastaneadi = hastaneadi;
    }

    public int getIlceid() {
        return ilceid;
    }

    public void setIlceid(int ilceid) {
        this.ilceid = ilceid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hastaneler)) {
            return false;
        }
        Hastaneler other = (Hastaneler) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.ilkgunel.hastaneotomasyonu.entity.Hastaneler[ id=" + id + " ]";
    }
    
}
