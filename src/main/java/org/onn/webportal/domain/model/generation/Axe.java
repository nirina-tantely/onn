package org.onn.webportal.domain.model.generation;

public class Axe {

	public Axe(String idAxe, String nom, String description) {
		super();
		this.idAxe = idAxe;
		this.nom = nom;
		this.description = description;
	}

	private String idAxe;
	private String nom;
	private String description;
	private String intervention;

	public String getIdAxe() {
		return this.idAxe;
	}

	public void setIdAxe(String idAxe) {
		this.idAxe = idAxe;
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

	public String getIntervention() {
		return this.intervention;
	}

	public void setIntervention(String intervention) {
		this.intervention = intervention;
	}

}