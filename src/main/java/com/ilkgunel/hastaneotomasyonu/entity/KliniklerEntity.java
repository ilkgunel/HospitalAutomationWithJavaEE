package com.ilkgunel.hastaneotomasyonu.entity;

import javax.persistence.*;

/**
 * Created by root on 4/24/15.
 */
@Entity
@Table(name = "klinikler", schema = "", catalog = "HospitalAutomation")
public class KliniklerEntity {
    private int id;
    private String klinikadi;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "klinikadi", nullable = false, insertable = true, updatable = true, length = 50)
    public String getKlinikadi() {
        return klinikadi;
    }

    public void setKlinikadi(String klinikadi) {
        this.klinikadi = klinikadi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KliniklerEntity that = (KliniklerEntity) o;

        if (id != that.id) return false;
        if (klinikadi != null ? !klinikadi.equals(that.klinikadi) : that.klinikadi != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (klinikadi != null ? klinikadi.hashCode() : 0);
        return result;
    }
}
