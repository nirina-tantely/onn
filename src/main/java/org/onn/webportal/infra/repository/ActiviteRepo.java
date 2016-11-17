package org.onn.webportal.infra.repository;

import java.util.List;

import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.Localisation;

public interface ActiviteRepo {

	public List<Activite> listeActivitesParLocalisation(Localisation localisation);
}
