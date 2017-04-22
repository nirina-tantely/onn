package org.onn.webportal.infra.repository.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.onn.webportal.domain.model.ActiviteMetadata;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.IndicateurSMS;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.onn.webportal.infra.rowmapper.ActiviteRowMapper;
import org.onn.webportal.infra.rowmapper.IndicateurONGBaseRowMapper;
import org.onn.webportal.infra.rowmapper.IndicateurSMSRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MetadataRepoImpl implements MetadataRepo {

	private final Logger logger = LoggerFactory.getLogger(GeoRepoImpl.class);
	
	protected JdbcTemplate jdbcTemplate;

	private List<ActiviteMetadata> activiteMetadata;

	private List<IndicateurONG> indicateurONGMetadata;

	private List<IndicateurSMS> indicateurSMSMetadata;

	@Resource(name = "dataSourceONN")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ActiviteMetadata> loadActivityMetadata() {
		StringBuilder requete = new StringBuilder().append(" SELECT am.idactivite, am.nomactivite,  am.description as amdesc,  am.axeid,  am.rangcolonne,  am.syntheseid,  axe.idaxe, axe.nomaxe, axe.description as axedesc");
		requete.append(", axe.intervention,  synthese.indicateur, synthese.nom, synthese.description as syndesc");
		requete.append(" FROM activite_metadata am , axe, synthese WHERE  am.axeid = axe.idaxe AND  am.syntheseid = synthese.indicateur;");
		List<ActiviteMetadata> result = jdbcTemplate.query(requete.toString(), new ActiviteRowMapper());
		return result;
	}

	public List<IndicateurONG> loadIndicateurONG() {
		StringBuilder requete = new StringBuilder().append("SELECT  id,  id_indicateur,  nom_court, definition, code_indicateur_cat, desagregation, modalite_calcul, categorie, indc_perfomance, info_comp, type_indc, code_complet, rang, mode_calcule, principale FROM  indicateur_ongbase ORDER BY id_indicateur ASC;");
		List<IndicateurONG> result = jdbcTemplate.query(requete.toString(), new IndicateurONGBaseRowMapper());
		return result;
	}

	public List<IndicateurSMS> loadIndicateurSMS() {
		StringBuilder requete = new StringBuilder().append("SELECT  id, id_indicateur, nom_court, definition, rang FROM indicateur_sms ORDER BY id_indicateur ASC;");
		List<IndicateurSMS> result = jdbcTemplate.query(requete.toString(), new IndicateurSMSRowMapper());
		return result;
	}

	public List<ActiviteMetadata> getActiviteMetadata() {
		if(activiteMetadata==null) activiteMetadata = loadActivityMetadata();
		return activiteMetadata;
	}

	public List<IndicateurONG> getIndicateurONGMetadata() {
		if(indicateurONGMetadata==null) indicateurONGMetadata = loadIndicateurONG();
		return indicateurONGMetadata;
	}

	public List<IndicateurSMS> getIndicateurSMSMetadata() {
		if(indicateurSMSMetadata==null) indicateurSMSMetadata = loadIndicateurSMS();
		return indicateurSMSMetadata;
	}
}
