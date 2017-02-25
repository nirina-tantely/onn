package org.onn.webportal.infra.repository;

import java.util.List;
import org.onn.webportal.domain.model.Localisation;

public interface LocalisationRepo {

	public List<Localisation> getAllRegions();

	List<Localisation> getCommunesByregion(String codeRegion);

	List<Localisation> getFokontanyByCommune(String codeCommune);

	List<Localisation> getAllFokontany();
}
