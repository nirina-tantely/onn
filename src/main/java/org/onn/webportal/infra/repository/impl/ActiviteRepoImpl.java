package org.onn.webportal.infra.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.IndicateurSMS;
import org.onn.webportal.domain.model.Intervenant;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.onn.webportal.infra.rowmapper.SyntheseRowMapper;
import org.onn.webportal.infra.rowmapper.IntervenantRowMapper;
import org.onn.webportal.infra.rowmapper.ONGBaseSyntheseRowMapper;
import org.onn.webportal.infra.rowmapper.SMSBaseSyntheseRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ActiviteRepoImpl implements ActiviteRepo {

	private final Logger logger = LoggerFactory.getLogger(GeoRepoImpl.class);
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	MetadataRepo metadataRepo;

	@Resource(name = "dataSourceONN")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Activite> listeActivitesParLocalisation(Localisation localisation) {
		Activite act1 = new Activite("activite1");
		Activite act2 = new Activite("activite2");
		List<Activite> liste = new ArrayList<Activite>();
		liste.add(act1);
		liste.add(act2);
		return liste;
	}

	public List<List<Synthese>> getSyntheses(String codeLocalisation, TypeLocalisation typeLocalisation, int annee, String codeIntervenant) {
		StringBuilder requete = new StringBuilder().append("SELECT * FROM activite ");
		List<Object> params = new ArrayList<Object>();
		switch (typeLocalisation) {
		case COMMUNE:	
			requete.append(" WHERE code_commune = ?");	
			params.add(Integer.valueOf(codeLocalisation));
			break;
		case REGION:
			requete.append(" WHERE code_region = ?");
			params.add(Integer.valueOf(codeLocalisation));
			break;
		case FOKONTANY:
			requete.append(" WHERE code_fokontany = ?");
			params.add(Integer.valueOf(codeLocalisation));
			break;
		case NATIONALE:
			requete.append(" WHERE TRUE");
			break;
		default :
			break;
		}
		params.add(annee);
		requete.append(" AND annee = ?");
		if(codeIntervenant.length()>0 && !codeIntervenant.equals("VIDE")){
			requete.append(" AND code_intervenant = ?");
			params.add(codeIntervenant);
		}
		List<List<Synthese>> result = jdbcTemplate.query(requete.toString(), params.toArray(), new SyntheseRowMapper(metadataRepo.getActiviteMetadata()));
		return result;
	}

	public List<List<IndicateurONG>> getOngBase(String codeLocalisation, TypeLocalisation typeLocalisation, int trimestre, int annee) {
		StringBuilder requete = new StringBuilder().append("SELECT * FROM ongbase ");
		Object[] params  = new Object[] {Integer.valueOf(codeLocalisation), trimestre, annee};
		switch (typeLocalisation) {
		case COMMUNE:	
			requete.append(" WHERE code_commune = ?");	
			break;
		case REGION:
			requete.append(" WHERE code_region = ?");
			break;
		case FOKONTANY:
			requete.append(" WHERE code_fokontany = ?");
			break;
		case NATIONALE:
			requete.append(" WHERE TRUE");
			params  = new Object[] {trimestre, annee};
			break;
		default:
			break;
		}
		requete.append(" AND trimestre = ? AND annee = ?");
		List<List<IndicateurONG>> result = jdbcTemplate.query(requete.toString(), params, new ONGBaseSyntheseRowMapper(metadataRepo.getIndicateurONGMetadata()));
		return result;
	}


	public List<List<IndicateurSMS>> getIndicateurSMS(String codeLocalisation, TypeLocalisation typeLocalisation, int annee) {
		StringBuilder requete = new StringBuilder().append("SELECT * FROM donnees_sms ");
		Object[] params  = new Object[] {Integer.valueOf(codeLocalisation), annee};
		switch (typeLocalisation) {
		case COMMUNE:	
			requete.append(" WHERE code_commune = ?");	
			break;
		case REGION:
			requete.append(" WHERE code_region = ?");
			break;
		case FOKONTANY:
			requete.append(" WHERE code_fokontany = ?");
			break;
		case NATIONALE:
			requete.append(" WHERE TRUE");
			params  = new Object[] {annee};
			break;
		default:
			break;
		}
		requete.append(" AND annee = ?");
		List<List<IndicateurSMS>> result = jdbcTemplate.query(requete.toString(), params, new SMSBaseSyntheseRowMapper(metadataRepo.getIndicateurSMSMetadata()));
		return result;
	}


	public List<Integer> getCodesRegionByIntervenant(String codeIntervenant, int annee) {
		StringBuilder requete = new StringBuilder().append("SELECT code_region FROM activite WHERE code_intervenant = ? AND annee = ? GROUP BY code_region");
		List<Integer> result = new ArrayList<Integer>();
		result = jdbcTemplate.queryForList(requete.toString(), new Object[] {codeIntervenant, annee}, Integer.class);
		return result;
	}

	public List<Integer> getCodesCommuneByIntervenant(String codeIntervenant, int annee) {
		StringBuilder requete = new StringBuilder().append("SELECT code_commune FROM activite WHERE code_intervenant = ? AND annee = ? GROUP BY code_commune");
		List<Integer> result = new ArrayList<Integer>();
		result = jdbcTemplate.queryForList(requete.toString(), new Object[] {codeIntervenant, annee}, Integer.class);
		return result;
	}

	public List<Integer> getCodesFokontanyByIntervenant(String codeIntervenant, int annee) {
		StringBuilder requete = new StringBuilder().append("SELECT code_fokontany FROM activite WHERE code_intervenant = ? AND annee = ? GROUP BY code_fokontany");
		List<Integer> result = new ArrayList<Integer>();
		result = jdbcTemplate.queryForList(requete.toString(), new Object[] {codeIntervenant, annee}, Integer.class);
		return result;
	}

}
