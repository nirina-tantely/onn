package org.onn.webportal.infra.repository;

public interface GeoRepo {

	String getGeoRegionByCode(String codeRegion);

	String getGeoCommuneByCode(String codeCommune);

	String getGeoFktByCommuneCode(String codeCommune);

	String getGeoAllFkt();

	String getGeoRegionByIntervenant(String codeIntervenant, int annee);

	String getGeoCommuneByIntervenant(String codeIntervenant, int annee);

	String getGeoFktByIntervenant(String codeIntervenant, int annee);

}
