package org.onn.webportal.domain.model;

public class Intervenant {

	private String idIntervenant;
	private String nom;
	private String description;

	public String getIdIntervenant() {
		return this.idIntervenant;
	}

	public void setIdIntervenant(String idIntervenant) {
		this.idIntervenant = idIntervenant;
	}

	public String getNom() {
		return this.nom;
	}

	public Intervenant() {
	}
	public Intervenant(String idIntervenant, String nom, String description) {
		super();
		this.idIntervenant = idIntervenant;
		this.nom = nom;
		this.description = description;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}