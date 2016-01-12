/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.ejb;

import com.ilkgunel.hastaneotomasyonu.entity.Patients;
import javax.ejb.Local;

/**
 *
 * @author ilkaygunel
 */
@Local
public interface SavePatientSessionBeanLocal {
    public String savePatient(Patients patients);
}
