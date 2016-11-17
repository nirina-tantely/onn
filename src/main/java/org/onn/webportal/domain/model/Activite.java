package org.onn.webportal.domain.model;

import java.util.Date;

public class Activite {

	private String a1it1a1;
	private String a1it2a1;
	private String a1it2a2;
	private Intervenant intervenant;
	private Localisation localisation;
	private Date date;
	private Date timeStamp;
	private String id;
	private String a1it2a3;
	
	public Activite(String id) {
		super();
		this.id = id;
	}

	public String getA1it1a1() {
		return this.a1it1a1;
	}

	public void setA1it1a1(String a1it1a1) {
		this.a1it1a1 = a1it1a1;
	}

	public String getA1it2a1() {
		return this.a1it2a1;
	}

	public void setA1it2a1(String a1it2a1) {
		this.a1it2a1 = a1it2a1;
	}

	public String getA1it2a2() {
		return this.a1it2a2;
	}

	public void setA1it2a2(String a1it2a2) {
		this.a1it2a2 = a1it2a2;
	}

	public Intervenant getIntervenant() {
		return this.intervenant;
	}

	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}

	public Localisation getLocalisation() {
		return this.localisation;
	}

	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getA1it2a3() {
		return this.a1it2a3;
	}

	public void setA1it2a3(String a1it2a3) {
		this.a1it2a3 = a1it2a3;
	}

}