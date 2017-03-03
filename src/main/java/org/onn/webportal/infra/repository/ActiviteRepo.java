package org.onn.webportal.infra.repository;

import java.util.List;
import java.util.Map;

import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.IndicateurSMS;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;

public interface ActiviteRepo {

	public List<Activite> listeActivitesParLocalisation(Localisation localisation);

	List<List<Synthese>> getSyntheses(String codeLocalisation, TypeLocalisation typeLocalisation,  int annee, String codeIntervenant);

	List<List<IndicateurONG>> getOngBase(String codeLocalisation, TypeLocalisation typeLocalisation,  int trimestre, int annee);

	List<List<IndicateurSMS>> getIndicateurSMS(String codeLocalisation, TypeLocalisation typeLocalisation, int annee);

	List<Integer> getCodesRegionByIntervenant(String codeIntervenant, int annee);

	List<Integer> getCodesCommuneByIntervenant(String codeIntervenant, int annee);

	List<Integer> getCodesFokontanyByIntervenant(String codeIntervenant, int annee);
}
