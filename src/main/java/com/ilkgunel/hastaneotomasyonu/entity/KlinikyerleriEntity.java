package com.ilkgunel.hastaneotomasyonu.entity;

import javax.persistence.*;

/**
 * Created by root on 4/24/15.
 */
@Entity
@Table(name = "klinikyerleri", schema = "", catalog = "HospitalAutomation")
public class KlinikyerleriEntity {
    private int id;
    private String klinikyeri;
    private int hastaneid;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "klinikyeri", nullable = false, insertable = true, updatable = true, length = 50)
    public String getKlinikyeri() {
        return klinikyeri;
    }

    public void setKlinikyeri(String klinikyeri) {
        this.klinikyeri = klinikyeri;
    }

    @Basic
    @Column(name = "hastaneid", nullable = false, insertable = true, updatable = true)
    public int getHastaneid() {
        return hastaneid;
    }

    public void setHastaneid(int hastaneid) {
        this.hastaneid = hastaneid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KlinikyerleriEntity that = (KlinikyerleriEntity) o;

        if (hastaneid != that.hastaneid) return false;
        if (id != that.id) return false;
        if (klinikyeri != null ? !klinikyeri.equals(that.klinikyeri) : that.klinikyeri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (klinikyeri != null ? klinikyeri.hashCode() : 0);
        result = 31 * result + hastaneid;
        return result;
    }
}
