package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.onn.webportal.domain.model.Intervenant;
import org.springframework.jdbc.core.RowMapper;

public class IntervenantRowMapper implements RowMapper<Intervenant> {


	public IntervenantRowMapper() {
	}

	public Intervenant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Intervenant result = new Intervenant();
		result.setIdIntervenant(rs.getString("code_intervenant"));
		result.setDescription(rs.getString("desc_intervenant"));
		result.setNom(rs.getString("nom_intervenant"));
		return result;
	}

}
