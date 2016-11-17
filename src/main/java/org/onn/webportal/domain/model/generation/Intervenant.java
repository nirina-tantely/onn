package org.onn.webportal.domain.model.generation;

public class Intervenant {

	private String idIntervenant;
	private String nom;
	private String description;
	private String site;

	public String getIdIntervenant() {
		return this.idIntervenant;
	}

	public void setIdIntervenant(String idIntervenant) {
		this.idIntervenant = idIntervenant;
	}

	public String getNom() {
		return this.nom;
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

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}