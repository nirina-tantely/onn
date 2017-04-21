package org.onn.webportal.domain.model;

import org.onn.webportal.api.enumeration.EnumRole;

public class User {

	private int idUtilisateur;
	private String pseudo;
	private String nom;
	private EnumRole role;
	private String password;


	public User(){
	}
	
	public User(int idUtilisateur, String pseudo, String nom, EnumRole role, String password) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.role = role;
		this.password = password;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public String getNom() {
		return nom;
	}
	public EnumRole getRole() {
		return role;
	}
	public String getPassword() {
		return password;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setRole(EnumRole role) {
		this.role = role;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}