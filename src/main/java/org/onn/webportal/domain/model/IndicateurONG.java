package org.onn.webportal.domain.model;

public class IndicateurONG {

	private String idIndicateur;
	private String nom;
	private String definition;
	private String codeCategorie;
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
	
	private int valeur;
	
	public IndicateurONG(){
		
	}
	
	public IndicateurONG(String idIndicateur, String nom, String definition, String codeCategorie, String desagregation,
			String indcPerfomance, String infoComp, String typeIndc, boolean codeComplet, int rang) {
		super();
		this.idIndicateur = idIndicateur;
		this.nom = nom;
		this.definition = definition;
		this.codeCategorie = codeCategorie;
		this.desagregation = desagregation;
		this.indcPerfomance = indcPerfomance;
		this.infoComp = infoComp;
		this.typeIndc = typeIndc;
		this.codeComplet = codeComplet;
		this.rang = rang;
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
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public IndicateurONG copy(){
		return new IndicateurONG(idIndicateur, nom, definition, codeCategorie, desagregation, indcPerfomance, infoComp, typeIndc, codeComplet, rang);
	}

}