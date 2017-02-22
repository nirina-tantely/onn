package org.onn.webportal.infra.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.infra.repository.LocalisationRepo;
import org.onn.webportal.infra.rowmapper.LocalisationRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LocalisationRepoImpl implements LocalisationRepo {

private final Logger logger = LoggerFactory.getLogger(LocalisationRepoImpl.class);
protected JdbcTemplate jdbcTemplate;

@Resource(name = "dataSourceONN")
public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
}

	public List<Localisation> getAllRegions() {
		StringBuilder requete = new StringBuilder().append("SELECT DISTINCT c_reg, nom_region FROM fokontany WHERE nom_region IS NOT NULL;");
		List<Localisation> result = new ArrayList<Localisation>();
		result = jdbcTemplate.query(requete.toString(), new LocalisationRowMapper(TypeLocalisation.REGION));
		return result;
	}
	
	public List<Localisation> getCommunesByregion(String codeRegion) {
		StringBuilder requete = new StringBuilder().append("SELECT DISTINCT c_com, nom_commun FROM fokontany WHERE c_reg = '"+codeRegion+"' ORDER BY nom_commun ASC");
		List<Localisation> result = new ArrayList<Localisation>();
		result = jdbcTemplate.query(requete.toString(), new LocalisationRowMapper(TypeLocalisation.COMMUNE));
		return result;
	}
	
	public List<Localisation> getFokontanyByCommune(String codeCommune) {
		StringBuilder requete = new StringBuilder().append("SELECT DISTINCT c_lc, nom_loca FROM fokontany WHERE c_com = '"+codeCommune+"' ORDER BY nom_loca ASC");
		logger.debug(requete.toString());
		List<Localisation> result = new ArrayList<Localisation>();
		result = jdbcTemplate.query(requete.toString(), new LocalisationRowMapper(TypeLocalisation.FOKONTANY));
		return result;
	}
	
	public List<Localisation> getAllFokontany() {
		StringBuilder requete = new StringBuilder().append("SELECT DISTINCT c_lc, nom_loca FROM fokontany ORDER BY nom_loca ASC");
		logger.debug(requete.toString());
		List<Localisation> result = new ArrayList<Localisation>();
		result = jdbcTemplate.query(requete.toString(), new LocalisationRowMapper(TypeLocalisation.FOKONTANY));
		return result;
	}


}
