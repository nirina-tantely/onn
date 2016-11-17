package org.onn.webportal.domain.model;

public class ActiviteMetadata {

	private String idActivite;
	private String nom;
	private String Description;
	private int rangColonne;
	private Axe axe;
	private Synthese sythese;

	public String getIdActivite() {
		return this.idActivite;
	}

	public void setIdActivite(String idActivite) {
		this.idActivite = idActivite;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public int getRangColonne() {
		return this.rangColonne;
	}

	public void setRangColonne(int rangColonne) {
		this.rangColonne = rangColonne;
	}

	public Axe getAxe() {
		return this.axe;
	}

	public void setAxe(Axe axe) {
		this.axe = axe;
	}

	public Synthese getSythese() {
		return this.sythese;
	}

	public void setSythese(Synthese sythese) {
		this.sythese = sythese;
	}

}