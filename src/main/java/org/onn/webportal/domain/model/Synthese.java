package org.onn.webportal.domain.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Synthese {

	private String idIndicateur;
	private String nom;
	private String description;
	private String idCategorie;

	/**
	 * Valeur pour la résultat d'une requete
	 */
	private int valeur;

	public String getIdIndicateur() {
		return this.idIndicateur;
	}

	public void setIdIndicateur(String idIndicateur) {
		this.idIndicateur = idIndicateur;
	}

	public String getNom() {
		return this.nom;
	}

	@XmlElement
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValeur() {
		return valeur;
	}

	@XmlElement
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public String getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(String idCategorie) {
		this.idCategorie = idCategorie;
	}

	public Synthese copy(){
		Synthese copy = new Synthese();
		copy.setDescription(description);
		copy.setIdIndicateur(idIndicateur);
		copy.setNom(nom);
		copy.setValeur(0);
		return copy;
	}

}