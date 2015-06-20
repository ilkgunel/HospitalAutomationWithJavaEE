/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.ilkgunel.hastaneotomasyonu.entity.Takenappointments;
/**
 *
 * @author ilkaygunel
 */
public class Test {
   /* String isim;
    private Md5PasswordEncoder passwordEncoder=new Md5PasswordEncoder();
    public static void main(String[] args) {
        
        
        Test t=new Test();
        //t.isim="ilknur";
        System.out.println("Şifrelendi"+t.passwordEncoder.encodePassword(t.isim, ""));
    }*/
    
   /* public static void main(String[] args)
{
String parola = "malmesap93";
    
    try{
        MessageDigest messageDigestNesnesi = MessageDigest.getInstance("MD5");
        messageDigestNesnesi.update(parola.getBytes());
        byte messageDigestDizisi[] = messageDigestNesnesi.digest();
        StringBuffer sb16 = new StringBuffer();
        StringBuffer sb32 = new StringBuffer();
        for (int i = 0; i < messageDigestDizisi.length; i++) {
        sb16.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 16).substring(1));
        sb32.append(Integer.toString((messageDigestDizisi[i] & 0xff) + 0x100, 32));
 }
 System.out.println("Parolanın Şifrelenmiş Hali:(16) " + sb16.toString());
 System.out.println("Parolanın Şifrelenmiş Hali:(32) " + sb32.toString());
    }
    catch(NoSuchAlgorithmException ex){
        System.err.println(ex);
    }
}*/
	
	public static void main(String[] args)
	{
		System.out.println("Metot Çalıştı");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("HospitalAutomation");
	    EntityManager em=emf.createEntityManager();
		List<Takenappointments> patientsAppointments=new ArrayList<Takenappointments>();
		TypedQuery<Takenappointments> query=em.createQuery("SELECT t FROM Takenappointments t",Takenappointments.class);
		//query.setParameter("patientid", "11111111111");
		patientsAppointments=query.getResultList();
		
		for (Takenappointments t:patientsAppointments) {
			System.out.println("Döngü içindeyiz:");
			System.out.println(t.getDoctorid());
			System.out.println(t.getClinicplace());
		}
	}
}
