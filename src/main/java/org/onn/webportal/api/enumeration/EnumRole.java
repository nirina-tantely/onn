package org.onn.webportal.api.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum EnumRole {
	ADMIN("Administrateur", 1),
	PTF_USER("Utilisateur TPF", 2),
	ONN_USER("Utilisateur ONN", 3);

	private String nom;
	private int id;


	private EnumRole(String nom, int id) {
		this.nom = nom;
		this.id = id;
	}

	public static EnumRole getById(int id){
		for(EnumRole type:values()){
			if(type.getId()==id) return type;
		}
		return null;
	}

	public String getNom() {
		return nom;
	}


	public int getId() {
		return id;
	}

	public static List<EnumRole> getAllRoles(){
		ArrayList<EnumRole> roles = new ArrayList<EnumRole>();
		for(EnumRole role: values()){
			roles.add(role);
		}
		return roles;
	}

}
