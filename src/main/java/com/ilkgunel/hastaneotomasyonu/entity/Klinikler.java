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
@Table(name = "klinikler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klinikler.findAll", query = "SELECT k FROM Klinikler k"),
    @NamedQuery(name = "Klinikler.findById", query = "SELECT k FROM Klinikler k WHERE k.id = :id"),
    @NamedQuery(name = "Klinikler.findByKlinikadi", query = "SELECT k FROM Klinikler k WHERE k.klinikadi = :klinikadi")})
public class Klinikler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "klinikadi")
    private String klinikadi;

    public Klinikler() {
    }

    public Klinikler(Integer id) {
        this.id = id;
    }

    public Klinikler(Integer id, String klinikadi) {
        this.id = id;
        this.klinikadi = klinikadi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKlinikadi() {
        return klinikadi;
    }

    public void setKlinikadi(String klinikadi) {
        this.klinikadi = klinikadi;
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
        if (!(object instanceof Klinikler)) {
            return false;
        }
        Klinikler other = (Klinikler) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.ilkgunel.hastaneotomasyonu.entity.Klinikler[ id=" + id + " ]";
    }
    
}
