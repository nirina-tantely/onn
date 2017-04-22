package org.onn.webportal.domain.service;

public interface GeoService {

	public String getGeoRegionByCode(String codeRegion);

	String getGeoCommuneAndFktByCode(String codeCommune);

	String getGeoAllFkt();

	String getGeoJsonByIntervenant(String codeIntervenant, int annee, String typeLocalisation);
}
