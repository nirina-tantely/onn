package org.onn.webportal.api.enumeration;

public enum ModeCalculEnum {
	SOMME(1),
	MAX_MOYENNE(2);

	private int valeur;
	ModeCalculEnum(int valeur){
		this.valeur = valeur;
	}
	public int getValeur() {
		return valeur;
	}

	public static ModeCalculEnum getByValue(int valeur){
		for(ModeCalculEnum type:values()){
			if(type.getValeur() == valeur) return type;
		}
		return null;
	}
}
