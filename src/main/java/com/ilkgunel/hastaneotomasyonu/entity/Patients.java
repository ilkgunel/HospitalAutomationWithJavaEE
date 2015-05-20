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
@Table(name = "patients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Patients.findAll", query = "SELECT p FROM Patients p"),
    @NamedQuery(name = "Patients.findByIdentitynumber", query = "SELECT p FROM Patients p WHERE p.identitynumber = :identitynumber"),
    @NamedQuery(name = "Patients.findByName", query = "SELECT p FROM Patients p WHERE p.name = :name"),
    @NamedQuery(name = "Patients.findBySurname", query = "SELECT p FROM Patients p WHERE p.surname = :surname"),
    @NamedQuery(name = "Patients.findByGender", query = "SELECT p FROM Patients p WHERE p.gender = :gender"),
    @NamedQuery(name = "Patients.findByBirthplace", query = "SELECT p FROM Patients p WHERE p.birthplace = :birthplace"),
    @NamedQuery(name = "Patients.findByBirthdate", query = "SELECT p FROM Patients p WHERE p.birthdate = :birthdate"),
    @NamedQuery(name = "Patients.findByFathername", query = "SELECT p FROM Patients p WHERE p.fathername = :fathername"),
    @NamedQuery(name = "Patients.findByMothername", query = "SELECT p FROM Patients p WHERE p.mothername = :mothername"),
    @NamedQuery(name = "Patients.findByPhonenumber", query = "SELECT p FROM Patients p WHERE p.phonenumber = :phonenumber"),
    @NamedQuery(name = "Patients.findByEmailaddress", query = "SELECT p FROM Patients p WHERE p.emailaddress = :emailaddress"),
    @NamedQuery(name = "Patients.findByPassword", query = "SELECT p FROM Patients p WHERE p.password = :password"),
    @NamedQuery(name = "Patients.findByRole", query = "SELECT p FROM Patients p WHERE p.role = :role"),
    @NamedQuery(name = "Patients.findByEnabled", query = "SELECT p FROM Patients p WHERE p.enabled = :enabled")})
public class Patients implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "identitynumber")
    private String identitynumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "birthplace")
    private String birthplace;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "fathername")
    private String fathername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "mothername")
    private String mothername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "phonenumber")
    private String phonenumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "emailaddress")
    private String emailaddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enabled")
    private boolean enabled;

    public Patients() {
    }

    public Patients(String identitynumber) {
        this.identitynumber = identitynumber;
    }

    public Patients(String identitynumber, String name, String surname, String gender, String birthplace, Date birthdate, String fathername, String mothername, String phonenumber, String emailaddress, String password, String role, boolean enabled) {
        this.identitynumber = identitynumber;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthplace = birthplace;
        this.birthdate = birthdate;
        this.fathername = fathername;
        this.mothername = mothername;
        this.phonenumber = phonenumber;
        this.emailaddress = emailaddress;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public String getIdentitynumber() {
        return identitynumber;
    }

    public void setIdentitynumber(String identitynumber) {
        this.identitynumber = identitynumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identitynumber != null ? identitynumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patients)) {
            return false;
        }
        Patients other = (Patients) object;
        if ((this.identitynumber == null && other.identitynumber != null) || (this.identitynumber != null && !this.identitynumber.equals(other.identitynumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ilkgunel.hastaneotomasyonu.entity.Patients[ identitynumber=" + identitynumber + " ]";
    }
    
}
