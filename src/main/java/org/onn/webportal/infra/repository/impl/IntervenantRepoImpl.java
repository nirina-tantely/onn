package org.onn.webportal.infra.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.onn.webportal.domain.model.Intervenant;
import org.onn.webportal.infra.repository.IntervenantRepo;
import org.onn.webportal.infra.rowmapper.IntervenantRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IntervenantRepoImpl implements IntervenantRepo {

	private final Logger logger = LoggerFactory.getLogger(IntervenantRepoImpl.class);
	protected JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSourceONN")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Intervenant> getAllIntervenants() {
		StringBuilder requete = new StringBuilder().append("SELECT code_intervenant, nom_intervenant, desc_intervenant FROM public.intervenant;");
		List<Intervenant> result = new ArrayList<Intervenant>();
		result = jdbcTemplate.query(requete.toString(), new IntervenantRowMapper());
		return result;
	}
	
	
	public List<Integer> getAllAnnees() {
		StringBuilder requete = new StringBuilder().append("SELECT  annee FROM activite GROUP BY annee ORDER BY annee asc;");
		List<Integer> result = new ArrayList<Integer>();
		result = jdbcTemplate.queryForList(requete.toString(), Integer.class);
		return result;
	}
	
	public List<Integer> getAllONGAnnees() {
		StringBuilder requete = new StringBuilder().append("SELECT  annee FROM ongbase GROUP BY annee ORDER BY annee asc;");
		List<Integer> result = new ArrayList<Integer>();
		result = jdbcTemplate.queryForList(requete.toString(), Integer.class);
		return result;
	}
	
	public List<Integer> getAllSMSAnnees() {
		StringBuilder requete = new StringBuilder().append("SELECT  annee FROM donnees_sms GROUP BY annee ORDER BY annee asc;");
		List<Integer> result = new ArrayList<Integer>();
		result = jdbcTemplate.queryForList(requete.toString(), Integer.class);
		return result;
	}
}
