package org.onn.webportal.infra.repository.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.onn.webportal.infra.repository.GeoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.onn.webportal.infra.repository.ActiviteRepo;

@Repository
public class GeoRepoImpl implements GeoRepo {

	private final Logger logger = LoggerFactory.getLogger(GeoRepoImpl.class);
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	ActiviteRepo activiteRepo;

	@Resource(name = "dataSourceONN")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String getGeoRegionByCode(String codeRegion) {
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.004, false))::json As geometry, row_to_json((nom_commun, c_com, c_reg, 'commune')) As properties");
		requete.append(" FROM communes As lg where c_reg = ?   ) As f )  As fc;");
		String result = jdbcTemplate.queryForObject(requete.toString(), new Object[] {Integer.valueOf(codeRegion)}, String.class);
		return result;
	}

	public String getGeoCommuneByCode(String codeCommune) {
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.0015, false))::json As geometry, row_to_json((nom_commun, c_com, c_reg, 'commune')) As properties");
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



	public String getGeoRegionByIntervenant(String codeIntervenant, int annee) {
		List<Integer> codes = activiteRepo.getCodesRegionByIntervenant(codeIntervenant, annee);
		if(codes.size()<=0) return "";
		String listeCode = "(";
		boolean first = true;
		for (Integer integer : codes) {
			if(first) {
				listeCode += integer; 
			}else{
				listeCode += ", "+integer;
			}
			first = false;
		}
		listeCode += ")";
		
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.0006, false))::json As geometry, row_to_json((nom_region, c_reg, gid)) As properties");
		requete.append(" FROM regions As lg where c_reg IN "+listeCode+"   ) As f )  As fc;");
		String result = jdbcTemplate.queryForObject(requete.toString(), String.class);
		return result;
	}
	

	public String getGeoCommuneByIntervenant(String codeIntervenant, int annee) {
		List<Integer> codes = activiteRepo.getCodesCommuneByIntervenant(codeIntervenant, annee);
		if(codes.size()<=0) return "";
		String listeCode = "(";
		boolean first = true;
		for (Integer integer : codes) {
			if(first) {
				listeCode += integer; 
			}else{
				listeCode += ", "+integer;
			}
			first = false;
		}
		listeCode += ")";
		
		
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.004, false))::json As geometry, row_to_json((nom_commun, c_com, c_reg)) As properties");
		requete.append(" FROM communes As lg where c_com IN "+listeCode+"   ) As f )  As fc;");
		String result = jdbcTemplate.queryForObject(requete.toString(), String.class);
		return result;
	}
	
	
	public String getGeoFktByIntervenant(String codeIntervenant, int annee) {
		List<Integer> codes = activiteRepo.getCodesFokontanyByIntervenant(codeIntervenant, annee);
		if(codes.size()<=0) return "";
		String listeCode = "(";
		boolean first = true;
		for (Integer integer : codes) {
			if(first) {
				listeCode += integer; 
			}else{
				listeCode += ", "+integer;
			}
			first = false;
		}
		listeCode += ")";
		
		
		StringBuilder requete = new StringBuilder().append(" SELECT row_to_json(fc)  FROM ( SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features");
		requete.append(" FROM (SELECT 'Feature' As type , ST_AsGeoJSON(ST_Simplify(lg.geom, 0.0006, false))::json As geometry, row_to_json((nom_loca, c_lc, c_com, 'fokontany')) As properties");
		requete.append(" FROM fokontany As lg WHERE c_lc IN "+listeCode+" ) As f )  As fc;");
		String result = jdbcTemplate.queryForObject(requete.toString(), String.class);
		return result;
	}
}
