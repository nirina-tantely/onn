package org.onn.webportal.domain.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.onn.webportal.api.enumeration.ModeCalculEnum;

public class IndicateurONG {

	private String idIndicateur;

	private String nom;
	private String definition;
	private String codeCategorie;
	private int modeCalcule;
	private boolean principale;
	/**
	 * Désagrégation par tranche d'âge
	 */
	private String desagregation;
	private String indcPerfomance;
	/**
	 * ODP/Indicateurs intermédiaires
	 */
	private String infoComp;
	/**
	 * Types d'indicateur (outcome/output/processus)
	 */
	private String typeIndc;
	private boolean codeComplet;
	private int rang;

	private float valeur;	
	private int mois;


	private Map<Integer, List<Float>> vals;

	public IndicateurONG(){

	}

	public IndicateurONG(String idIndicateur, String nom, String definition, String codeCategorie, int modeCalcule,
			String desagregation, String indcPerfomance, String infoComp, String typeIndc, boolean codeComplet,
			int rang, float valeur, boolean principale) {
		super();
		this.idIndicateur = idIndicateur;
		this.nom = nom;
		this.definition = definition;
		this.codeCategorie = codeCategorie;
		this.modeCalcule = modeCalcule;
		this.desagregation = desagregation;
		this.indcPerfomance = indcPerfomance;
		this.infoComp = infoComp;
		this.typeIndc = typeIndc;
		this.codeComplet = codeComplet;
		this.rang = rang;
		this.valeur = valeur;
		this.principale = principale;
	}

	public String getIdIndicateur() {
		return idIndicateur;
	}
	public String getNom() {
		return nom;
	}
	public String getDefinition() {
		return definition;
	}
	public String getCodeCategorie() {
		return codeCategorie;
	}
	public String getDesagregation() {
		return desagregation;
	}
	public String getIndcPerfomance() {
		return indcPerfomance;
	}
	public String getInfoComp() {
		return infoComp;
	}
	public String getTypeIndc() {
		return typeIndc;
	}
	public boolean isCodeComplet() {
		return codeComplet;
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
	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}
	public void setDesagregation(String desagregation) {
		this.desagregation = desagregation;
	}
	public void setIndcPerfomance(String indcPerfomance) {
		this.indcPerfomance = indcPerfomance;
	}
	public void setInfoComp(String infoComp) {
		this.infoComp = infoComp;
	}
	public void setTypeIndc(String typeIndc) {
		this.typeIndc = typeIndc;
	}
	public void setCodeComplet(boolean codeComplet) {
		this.codeComplet = codeComplet;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	public float getValeur() {
		return valeur;
	}
	public void setValeur(float valeur) {
		this.valeur = valeur;
	}

	public IndicateurONG copy(){
		return new IndicateurONG(idIndicateur, nom, definition, codeCategorie, modeCalcule, desagregation, indcPerfomance, infoComp, typeIndc, codeComplet, rang, valeur, principale);
	}

	public int getModeCalcule() {	
		return modeCalcule;
	}

	public void setModeCalcule(int modeCalcule) {
		this.modeCalcule = modeCalcule;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

	public Map<Integer, List<Float>> getVals() {
		if(vals==null) {
			vals = new HashMap<Integer, List<Float>>();
		}
		return vals;
	}

	public String valeurAfficher(){
		String res = "";
		DecimalFormat df = new DecimalFormat("#.##");
		switch (ModeCalculEnum.getByValue(modeCalcule)) {
		case MAX_MOYENNE:
			float val = calculerMaxMoyen();
			if(val>-1) res = df.format(val);
			break;
		case SOMME:
			float somme = calculerSomme();
			if(somme>-1) res = df.format(somme);
			break;
		default:
			break;
		}
		return res;
	}

	/**
	 * Calculer le maximal des mayennes pour chaque mois
	 * @return
	 */
	private float calculerMaxMoyen(){
		float maxMoyen = -1;
		if(vals!=null)
		for(Entry<Integer, List<Float>> val: vals.entrySet()){
			int count = 0;
			int total = 0;
			float moyen = -1;
			for(float v : val.getValue()){
				if(v>=0){
					count++;
					total+=v;
				}
			}
			if(count>0) moyen = (float) total/(float)count;
			if(moyen>maxMoyen) maxMoyen = moyen;
		}
		return maxMoyen;
	}

	private float calculerSomme(){
		float somme = 0;
		if(vals!=null)
		for(Entry<Integer, List<Float>> val: vals.entrySet()){
			for(float v : val.getValue()){
				if(v>=0){//valeur non nulle
					somme+=v;
				}
			}
		}
		return somme;
	}

	public boolean isPrincipale() {
		return principale;
	}

	public void setPrincipale(boolean principale) {
		this.principale = principale;
	}
}