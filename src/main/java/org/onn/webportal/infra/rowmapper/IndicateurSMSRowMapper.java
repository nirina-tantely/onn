package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.onn.webportal.domain.model.IndicateurSMS;
import org.springframework.jdbc.core.RowMapper;

public class IndicateurSMSRowMapper implements RowMapper<IndicateurSMS> {

	public IndicateurSMSRowMapper() {
		//super();
	}

	public IndicateurSMS mapRow(ResultSet rs, int rowNum) throws SQLException {
		IndicateurSMS result = new IndicateurSMS();
		result.setIdIndicateur(rs.getString("id_indicateur"));
		result.setNom(rs.getString("nom_court"));
		result.setDefinition(rs.getString("definition"));
		result.setNom(rs.getString("nom_court"));
		result.setRang(rs.getInt("rang"));
		return result;
	}

}
