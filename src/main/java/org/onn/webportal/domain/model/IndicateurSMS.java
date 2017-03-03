package org.onn.webportal.domain.model;

public class IndicateurSMS {
	private String idIndicateur;
	private String nom;
	private String definition;
	private int rang;
	
	private int valeur;
	private int mois;
	
	public IndicateurSMS(String idIndicateur, String nom, String definition, int rang) {
		super();
		this.idIndicateur = idIndicateur;
		this.nom = nom;
		this.definition = definition;
		this.rang = rang;
	}
	
	public IndicateurSMS(){}
	
	public String getIdIndicateur() {
		return idIndicateur;
	}
	public String getNom() {
		return nom;
	}
	public String getDefinition() {
		return definition;
	}
	public int getRang() {
		return rang;
	}
	public void setIdIndicateur(String idIndicateur) {
		this.idIndicateur = idIndicateur;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	
	public IndicateurSMS copy(){
		return new IndicateurSMS(idIndicateur, nom, definition, rang);
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

}