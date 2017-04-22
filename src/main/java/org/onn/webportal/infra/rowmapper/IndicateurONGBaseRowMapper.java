package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.onn.webportal.domain.model.IndicateurONG;
import org.springframework.jdbc.core.RowMapper;

public class IndicateurONGBaseRowMapper implements RowMapper<IndicateurONG> {

	public IndicateurONGBaseRowMapper() {
		//super();
	}

	public IndicateurONG mapRow(ResultSet rs, int rowNum) throws SQLException {
		IndicateurONG result = new IndicateurONG();
		result.setCodeCategorie(rs.getString("code_indicateur_cat"));
		result.setCodeComplet(rs.getBoolean("code_complet"));
		result.setDefinition(rs.getString("definition"));
		result.setDesagregation(rs.getString("desagregation"));
		result.setIdIndicateur(rs.getString("id_indicateur"));
		result.setIndcPerfomance(rs.getString("indc_perfomance"));
		result.setInfoComp(rs.getString("info_comp"));
		result.setNom(rs.getString("nom_court"));
		result.setRang(rs.getInt("rang"));
		result.setTypeIndc(rs.getString("type_indc"));
		result.setModeCalcule(rs.getInt("mode_calcule"));
		result.setPrincipale(rs.getBoolean("principale"));
		return result;
	}

}
