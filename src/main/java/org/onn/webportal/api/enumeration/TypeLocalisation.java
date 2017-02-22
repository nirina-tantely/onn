package org.onn.webportal.api.enumeration;

public enum TypeLocalisation {
	REGION("region"),
	COMMUNE("commune"),
	FOKONTANY("fokontany"),
	COMPLET("complet");

	private String valeur;
	TypeLocalisation(String valeur){
		this.valeur = valeur;
	}
	public String getValeur() {
		return valeur;
	}
}
