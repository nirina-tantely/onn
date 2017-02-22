package org.onn.webportal.infra.repository;

public interface GeoRepo {

	String getGeoRegionByCode(String codeRegion);

	String getGeoCommuneByCode(String codeCommune);

	String getGeoFktByCommuneCode(String codeCommune);

	String getGeoAllFkt();

}
