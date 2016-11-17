package org.onn.webportal.domain.model.generation;

public class Indicateur {

	private String idIndicateur;
	private String designation;
	private String description;
	private int rangSMS;
	private int rangONGBase;

	public String getIdIndicateur() {
		return this.idIndicateur;
	}

	public void setIdIndicateur(String idIndicateur) {
		this.idIndicateur = idIndicateur;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRangSMS() {
		return this.rangSMS;
	}

	public void setRangSMS(int rangSMS) {
		this.rangSMS = rangSMS;
	}

	public int getRangONGBase() {
		return this.rangONGBase;
	}

	public void setRangONGBase(int rangONGBase) {
		this.rangONGBase = rangONGBase;
	}

}