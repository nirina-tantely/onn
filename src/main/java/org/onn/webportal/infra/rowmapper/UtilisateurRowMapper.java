package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.onn.webportal.api.enumeration.EnumRole;
import org.onn.webportal.domain.model.User;
import org.springframework.jdbc.core.RowMapper;

public class UtilisateurRowMapper implements RowMapper<User> {


	public UtilisateurRowMapper() {
	}

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User result = new User();
		result.setIdUtilisateur(rs.getInt("id"));
		result.setNom(rs.getString("nom"));
		result.setPassword(rs.getString("password"));
		result.setPseudo(rs.getString("pseudo"));
		result.setRole(EnumRole.getById(rs.getInt("idrole")));
		return result;
	}
}
