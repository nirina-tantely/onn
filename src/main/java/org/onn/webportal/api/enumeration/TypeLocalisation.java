package org.onn.webportal.api.enumeration;

public enum TypeLocalisation {
	REGION("region"),
	COMMUNE("commune"),
	FOKONTANY("fokontany"),
	NATIONALE("nationale");

	private String valeur;
	TypeLocalisation(String valeur){
		this.valeur = valeur;
	}
	public String getValeur() {
		return valeur;
	}
	
	public static TypeLocalisation getByValue(String valeur){
		for(TypeLocalisation type:values()){
			if(type.getValeur().equals(valeur)) return type;
		}
		return null;
	}
}
