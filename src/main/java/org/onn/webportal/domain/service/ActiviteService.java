package org.onn.webportal.domain.service;

import java.util.List;

import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.Localisation;

public interface ActiviteService {

	public List<Activite> listeActivitesParLocalisation(Localisation localisation);
}
