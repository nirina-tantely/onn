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
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.onn.webportal.infra.rowmapper.SyntheseRowMapper;
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

	public List<List<Synthese>> getSyntheses(String codeLocalisation, TypeLocalisation typeLocalisation) {
		StringBuilder requete = new StringBuilder().append("SELECT * FROM activite ");
		switch (typeLocalisation) {
		case COMMUNE:	
			requete.append(" WHERE code_commune = ?;");	
			break;
		case REGION:
			requete.append(" WHERE code_region = ?;");
			break;
		case FOKONTANY:
			requete.append(" WHERE code_fokontany = ?;");
			break;
		default:
			break;
		}
		List<List<Synthese>> result = jdbcTemplate.query(requete.toString(), new Object[] {Integer.valueOf(codeLocalisation)}, new SyntheseRowMapper(metadataRepo.getActiviteMetadata()));
		return result;
	}

	public List<List<IndicateurONG>> getOngBase(String codeLocalisation, TypeLocalisation typeLocalisation) {
		StringBuilder requete = new StringBuilder().append("SELECT * FROM ongbase ");
		switch (typeLocalisation) {
		case COMMUNE:	
			requete.append(" WHERE code_commune = ?;");	
			break;
		case REGION:
			requete.append(" WHERE code_region = ?;");
			break;
		case FOKONTANY:
			requete.append(" WHERE code_fokontany = ?;");
			break;
		default:
			break;
		}
		List<List<IndicateurONG>> result = jdbcTemplate.query(requete.toString(), new Object[] {Integer.valueOf(codeLocalisation)}, new ONGBaseSyntheseRowMapper(metadataRepo.getIndicateurONGMetadata()));
		return result;
	}


	public List<List<IndicateurSMS>> getIndicateurSMS(String codeLocalisation, TypeLocalisation typeLocalisation) {
		StringBuilder requete = new StringBuilder().append("SELECT * FROM donnees_sms ");
		switch (typeLocalisation) {
		case COMMUNE:	
			requete.append(" WHERE code_commune = ?;");	
			break;
		case REGION:
			requete.append(" WHERE code_region = ?;");
			break;
		case FOKONTANY:
			requete.append(" WHERE code_fokontany = ?;");
			break;
		default:
			break;
		}
		List<List<IndicateurSMS>> result = jdbcTemplate.query(requete.toString(), new Object[] {Integer.valueOf(codeLocalisation)}, new SMSBaseSyntheseRowMapper(metadataRepo.getIndicateurSMSMetadata()));
		return result;
	}

}
