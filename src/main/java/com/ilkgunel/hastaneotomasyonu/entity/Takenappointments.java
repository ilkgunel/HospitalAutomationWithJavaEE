package com.ilkgunel.hastaneotomasyonu.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import java.util.Date;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;




/**
 * The persistent class for the takenappointments database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Takenappointments.findAll", query="SELECT t FROM Takenappointments t"),
@NamedQuery(name = "TakenAppointments.findByPatineIdAndIsAppointmentCancelled", query="SELECT t FROM Takenappointments t WHERE t.patientid=:patientid AND t.wasappointmentcancelled=:cancelParameter")
})

public class Takenappointments implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int takedappointmentid;

	private String clinicname;

	private String clinicplace;

	private int clockid;

	@Temporal(TemporalType.DATE)
	private Date date;

	private boolean datepassed;

	private int doctorid;

	private String hospitalname;

	private String hour;

	private String patientid;

	private boolean wasappointmentcancelled;

	public Takenappointments() {
	}

	public int getTakedappointmentid() {
		return this.takedappointmentid;
	}

	public void setTakedappointmentid(int takedappointmentid) {
		this.takedappointmentid = takedappointmentid;
	}

	public String getClinicname() {
		return this.clinicname;
	}

	public void setClinicname(String clinicname) {
		this.clinicname = clinicname;
	}

	public String getClinicplace() {
		return this.clinicplace;
	}

	public void setClinicplace(String clinicplace) {
		this.clinicplace = clinicplace;
	}

	public int getClockid() {
		return this.clockid;
	}

	public void setClockid(int clockid) {
		this.clockid = clockid;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getDatepassed() {
		return this.datepassed;
	}

	public void setDatepassed(boolean datepassed) {
		this.datepassed = datepassed;
	}

	public int getDoctorid() {
		return this.doctorid;
	}

	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}

	public String getHospitalname() {
		return this.hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}

	public String getHour() {
		return this.hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getPatientid() {
		return this.patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public boolean getWasappointmentcancelled() {
		return this.wasappointmentcancelled;
	}

	public void setWasappointmentcancelled(boolean wasappointmentcancelled) {
		this.wasappointmentcancelled = wasappointmentcancelled;
	}

}