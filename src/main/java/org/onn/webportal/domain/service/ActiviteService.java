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

	List<Synthese> getActiviteSyntese(String codeLocalisation, TypeLocalisation typeLocalisation, String codeIntervenant, int annee);

	JSONArray getONGBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation, int annee, boolean tousIndicarteurs);

	JSONArray getCodesRegionByIntervenant(String codeIntervenant, int annee);

	JSONArray getCodesCommuneByIntervenant(String codeIntervenant, int annee);

	JSONArray getCodesFokontanyByIntervenant(String codeIntervenant, int annee);
	
	JSONArray getSMSBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation, int annee);
}
