package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.onn.webportal.domain.model.ActiviteMetadata;
import org.onn.webportal.domain.model.Axe;
import org.onn.webportal.domain.model.Synthese;
import org.springframework.jdbc.core.RowMapper;

public class ActiviteRowMapper implements RowMapper<ActiviteMetadata> {

	public ActiviteRowMapper() {
		//super();
	}

	public ActiviteMetadata mapRow(ResultSet rs, int rowNum) throws SQLException {
		ActiviteMetadata result = new ActiviteMetadata();
		result.setIdActivite(rs.getString("idactivite"));
		result.setDescription(rs.getString("amdesc"));
		result.setNom(rs.getString("nomactivite"));
		result.setRangColonne(rs.getInt("rangcolonne"));
		Axe axe = new Axe();
		axe.setIdAxe(rs.getString("idaxe"));
		axe.setNom(rs.getString("nomaxe"));
		axe.setDescription(rs.getString("axedesc"));
		axe.setIntervention(rs.getString("intervention"));
		result.setAxe(axe);
		Synthese syn = new Synthese();
		syn.setIdIndicateur(rs.getString("indicateur"));
		syn.setDescription(rs.getString("syndesc"));
		syn.setNom(rs.getString("nom"));
		result.setSythese(syn);
		return result;
	}

}
