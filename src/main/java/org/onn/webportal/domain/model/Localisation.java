package org.onn.webportal.domain.model;

import org.onn.webportal.api.enumeration.TypeLocalisation;

public class Localisation {

	

	private String idFokontany;
	private String idCommune;
	private String idRegion;
	private String nomFokontany;
	private String nomcommune;
	private String nomRegion;
	
	private TypeLocalisation type;

	public Localisation(){
		super();
	}
	public Localisation(String idFokontany, String idCommune, String idRegion) {
		super();
		this.idFokontany = idFokontany;
		this.idCommune = idCommune;
		this.idRegion = idRegion;
	}
	
	public String getIdFokontany() {
		return this.idFokontany;
	}

	public void setIdFokontany(String idFokontany) {
		this.idFokontany = idFokontany;
	}

	public String getIdCommune() {
		return this.idCommune;
	}

	public void setIdCommune(String idCommune) {
		this.idCommune = idCommune;
	}

	public String getIdRegion() {
		return this.idRegion;
	}

	public void setIdRegion(String idRegion) {
		this.idRegion = idRegion;
	}

	public String getNomFokontany() {
		return this.nomFokontany;
	}

	public void setNomFokontany(String nomFokontany) {
		this.nomFokontany = nomFokontany;
	}

	public String getNomcommune() {
		return this.nomcommune;
	}

	public void setNomcommune(String nomcommune) {
		this.nomcommune = nomcommune;
	}

	public String getNomRegion() {
		return this.nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}
	public TypeLocalisation getType() {
		return type;
	}
	public void setType(TypeLocalisation type) {
		this.type = type;
	}

}