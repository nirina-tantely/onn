package org.onn.webportal.domain.service;

import java.util.List;

import org.onn.webportal.domain.model.Localisation;

public interface LocalisationService {

	public List<Localisation> getAllRegions();

	String getCommuneListJson(String codeRegion);

	String getFokontanyListJson(String codeCommune);
}
