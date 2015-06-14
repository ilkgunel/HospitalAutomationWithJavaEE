/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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
    
    public static void main(String[] args)
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
}
}
