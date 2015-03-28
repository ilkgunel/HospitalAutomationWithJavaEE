/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 *
 * @author ilkaygunel
 */
@Named(value = "geciciTarihSinifi")
@Dependent
public class GeciciTarihSinifi {
Date tarih;

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public Date getTarih() {
        tarih=new Date();
        DateFormat df=new SimpleDateFormat("yyyy/MM/dd");
        try{
        tarih=df.parse("1980/01/01");
        }
        catch(Exception ex)
        {
            System.err.println(ex);
        }
        System.out.println(tarih);
        return tarih;
    }

}
