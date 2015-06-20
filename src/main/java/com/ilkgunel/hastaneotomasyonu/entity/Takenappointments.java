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
@Table(name = "takenappointments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Takenappointments.findAll", query = "SELECT t FROM Takenappointments t"),
    @NamedQuery(name = "Takenappointments.findByTakedappointmentid", query = "SELECT t FROM Takenappointments t WHERE t.takedappointmentid = :takedappointmentid"),
    @NamedQuery(name = "Takenappointments.findByDoctorid", query = "SELECT t FROM Takenappointments t WHERE t.doctorid = :doctorid"),
    @NamedQuery(name = "Takenappointments.findByPatientid", query = "SELECT t FROM Takenappointments t WHERE t.patientid = :patientid"),
    @NamedQuery(name = "Takenappointments.findByDate", query = "SELECT t FROM Takenappointments t WHERE t.date = :date"),
    @NamedQuery(name = "Takenappointments.findByHospitalname", query = "SELECT t FROM Takenappointments t WHERE t.hospitalname = :hospitalname"),
    @NamedQuery(name = "Takenappointments.findByClinicname", query = "SELECT t FROM Takenappointments t WHERE t.clinicname = :clinicname"),
    @NamedQuery(name = "Takenappointments.findByClinicplace", query = "SELECT t FROM Takenappointments t WHERE t.clinicplace = :clinicplace"),
    @NamedQuery(name = "Takenappointments.findByHour", query = "SELECT t FROM Takenappointments t WHERE t.hour = :hour")})
public class Takenappointments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "takedappointmentid")
    private Integer takedappointmentid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "doctorid")
    private int doctorid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "patientid")
    private String patientid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "hospitalname")
    private String hospitalname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "clinicname")
    private String clinicname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "clinicplace")
    private String clinicplace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "hour")
    private String hour;

    public Takenappointments() {
    }

    public Takenappointments(Integer takedappointmentid) {
        this.takedappointmentid = takedappointmentid;
    }

    public Takenappointments(Integer takedappointmentid, int doctorid, String patientid, Date date, String hospitalname, String clinicname, String clinicplace, String hour) {
        this.takedappointmentid = takedappointmentid;
        this.doctorid = doctorid;
        this.patientid = patientid;
        this.date = date;
        this.hospitalname = hospitalname;
        this.clinicname = clinicname;
        this.clinicplace = clinicplace;
        this.hour = hour;
    }

    public Integer getTakedappointmentid() {
        return takedappointmentid;
    }

    public void setTakedappointmentid(Integer takedappointmentid) {
        this.takedappointmentid = takedappointmentid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getClinicname() {
        return clinicname;
    }

    public void setClinicname(String clinicname) {
        this.clinicname = clinicname;
    }

    public String getClinicplace() {
        return clinicplace;
    }

    public void setClinicplace(String clinicplace) {
        this.clinicplace = clinicplace;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (takedappointmentid != null ? takedappointmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Takenappointments)) {
            return false;
        }
        Takenappointments other = (Takenappointments) object;
        return !((this.takedappointmentid == null && other.takedappointmentid != null) || (this.takedappointmentid != null && !this.takedappointmentid.equals(other.takedappointmentid)));
    }

    @Override
    public String toString() {
        return "com.ilkgunel.hastaneotomasyonu.entity.Takenappointments[ takedappointmentid=" + takedappointmentid + " ]";
    }
    
}
