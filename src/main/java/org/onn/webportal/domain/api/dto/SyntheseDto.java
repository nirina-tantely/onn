package org.onn.webportal.domain.api.dto;


public class SyntheseDto {

	private String indicateur;
	private int valeur;
	private double taux;



	public String getIndicateur() {
		return indicateur;
	}
	public int getValeur() {
		return valeur;
	}
	public double getTaux() {
		return taux;
	}
	public void setIndicateur(String indicateur) {
		this.indicateur = indicateur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}

	public String toJson(){
		try {
			return OnnUtils.getJsonMapper().writeValueAsString(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}
}
