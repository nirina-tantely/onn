package org.onn.webportal.domain.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.IndicateurSMS;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;

public interface ActiviteService {

	public List<Activite> listeActivitesParLocalisation(Localisation localisation);

	List<Synthese> getActiviteSyntese(String codeLocalisation, TypeLocalisation typeLocalisation);

	List<IndicateurSMS> getSMSBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation);

	JSONArray getONGBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation);
}
