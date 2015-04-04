/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilkgunel.hastaneotomasyonu.entity;

import java.io.Serializable;

/**
 *
 * @author Batuhan
 */
public class User implements Serializable{
    
    private String firstName;
    private String lastName;
    private String identityNumber;
    private String maleFemale;
    private String birthPlace;
    private String birthDate;
    private String fatherName;
    private String motherName;
    private String phoneNumber;
    private String homeNumber;
    private String emailAdreess;
    private String emailAdreessRepeat;
    private String password;
    private String passwordRepeat;

    public User(){
        
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getMaleFemale() {
        return maleFemale;
    }

    public void setMaleFemale(String maleFemale) {
        this.maleFemale = maleFemale;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getEmailAdreess() {
        return emailAdreess;
    }

    public void setEmailAdreess(String emailAdreess) {
        this.emailAdreess = emailAdreess;
    }

    public String getEmailAdreessRepeat() {
        return emailAdreessRepeat;
    }

    public void setEmailAdreessRepeat(String emailAdreessRepeat) {
        this.emailAdreessRepeat = emailAdreessRepeat;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
    
    
    
}
