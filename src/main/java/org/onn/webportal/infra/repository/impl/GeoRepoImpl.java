package org.onn.webportal.infra.repository.impl;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.onn.webportal.infra.repository.GeoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GeoRepoImpl implements GeoRepo {

	private final Logger logger = LoggerFactory.getLogger(GeoRepoImpl.class);
	protected JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSourceONN")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String getGeoRegionByCode(String codeRegion) {
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.0015, false))::json As geometry, row_to_json((nom_commun, c_com, c_reg, 'commune')) As properties");
		requete.append(" FROM communes As lg where c_reg = ?   ) As f )  As fc;");
		String result = jdbcTemplate.queryForObject(requete.toString(), new Object[] {Integer.valueOf(codeRegion)}, String.class);
		return result;
	}
	
	public String getGeoCommuneByCode(String codeCommune) {
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.0006, false))::json As geometry, row_to_json((nom_commun, c_com, c_reg, 'commune')) As properties");
		requete.append(" FROM communes As lg where c_com = ?   ) As f )  As fc;");
		String result = jdbcTemplate.queryForObject(requete.toString(), new Object[] {Integer.valueOf(codeCommune)}, String.class);
		return result;
	}
	
	public String getGeoFktByCommuneCode(String codeCommune) {
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.0009, false))::json As geometry, row_to_json((nom_loca, c_lc, c_com, 'fokontany')) As properties");
		requete.append(" FROM fokontany As lg where c_com = ?   ) As f )  As fc;");
		String result = jdbcTemplate.queryForObject(requete.toString(), new Object[] {Integer.valueOf(codeCommune)}, String.class);
		return result;
	}

	public String getGeoAllFkt() {
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.0006, false))::json As geometry, row_to_json((nom_loca, c_lc, c_com, 'fokontany')) As properties");
		requete.append(" FROM fokontany As lg ) As f )  As fc;");
		String result = jdbcTemplate.queryForObject(requete.toString(), new Object[] {}, String.class);
		return result;
	}
}
