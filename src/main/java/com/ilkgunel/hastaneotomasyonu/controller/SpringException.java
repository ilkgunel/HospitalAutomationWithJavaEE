/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilkgunel.hastaneotomasyonu.controller;

/**
 *
 * @author 010533
 */
public class SpringException extends Exception{
   private String exceptionMsg;
   
   public SpringException(String exceptionMsg) {
      this.exceptionMsg = exceptionMsg;
   }
   
   public String getExceptionMsg(){
      return this.exceptionMsg;
   }
   
   public void setExceptionMsg(String exceptionMsg) {
      this.exceptionMsg = exceptionMsg;
   }
}
