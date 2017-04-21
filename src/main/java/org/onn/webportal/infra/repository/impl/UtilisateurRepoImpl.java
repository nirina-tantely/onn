package org.onn.webportal.infra.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.onn.webportal.domain.model.User;
import org.onn.webportal.infra.repository.UtilisateurRepo;
import org.onn.webportal.infra.rowmapper.UtilisateurRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurRepoImpl implements UtilisateurRepo {

	private final Logger logger = LoggerFactory.getLogger(GeoRepoImpl.class);
	protected JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSourceONN")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<User> getAllUsers(){
		StringBuilder requete = new StringBuilder().append("SELECT * FROM public.utilisateur;");
		List<User> result = new ArrayList<User>();
		result = jdbcTemplate.query(requete.toString(), new UtilisateurRowMapper());
		return result;
	}

	public int saveOrUpdate(User user) {
		if(user.getIdUtilisateur()>0){
			logger.info("Modification de l'utilisateur "+user.getPseudo());
			StringBuilder requete = new StringBuilder().append("UPDATE utilisateur  SET nom=?, pseudo=?, password=?, idrole=? WHERE id = ?;");
			int res = jdbcTemplate.update(requete.toString(), new Object[] {user.getNom(), user.getPseudo(), user.getPassword(), user.getRole().getId(), user.getIdUtilisateur()});
			return res;
		}else{
			logger.info("Ajout de l'utilisateur "+user.getPseudo());
			StringBuilder requete = new StringBuilder().append("INSERT INTO utilisateur(nom, pseudo, password, idrole) VALUES (?, ?, ?, ?);");
			int res = jdbcTemplate.update(requete.toString(), new Object[] {user.getNom(), user.getPseudo(), user.getPassword(), user.getRole().getId()});
			return res;
		}
	}

	public User getUserByLogin(String pseudo) {
		StringBuilder requete = new StringBuilder().append("SELECT * FROM public.utilisateur WHERE pseudo = ?;");
		List<User> result = new ArrayList<User>();
		result = jdbcTemplate.query(requete.toString(), new Object[] {pseudo}, new UtilisateurRowMapper());
		if(result.size()>0) return result.get(0);
		return null;
	}

	public void deleteUser(int idUser) {
		logger.info("Suppression de l'utilisateur "+idUser);
		StringBuilder requete = new StringBuilder().append("DELETE FROM utilisateur WHERE id = ?;");
		jdbcTemplate.update(requete.toString(), new Object[] {idUser});
	}

}
