package org.onn.webportal.domain.model;

import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.TypeLocalisation;

public class Etat {
	private Localisation localisation = new Localisation();
	private Intervenant intervenant = new Intervenant();
	private TypeLocalisation niveauLocalisation;
	
	public Localisation getLocalisation() {
		return localisation;
	}
	public Intervenant getIntervenant() {
		return intervenant;
	}
	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}
	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}
	public TypeLocalisation getNiveauLocalisation() {
		return niveauLocalisation;
	}
	public void setNiveauLocalisation(TypeLocalisation niveauLocalisation) {
		this.niveauLocalisation = niveauLocalisation;
	}
	
	public JSONObject getJson(){
		JSONObject obj = new JSONObject();
		return obj;		
	}
}