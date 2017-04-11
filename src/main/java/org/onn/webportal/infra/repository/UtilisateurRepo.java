package org.onn.webportal.infra.repository;

import java.util.List;

import org.onn.webportal.domain.model.Utilisateur;

public interface UtilisateurRepo {

	List<Utilisateur> getAllUsers();

	int saveOrUpdate(Utilisateur user);

	Utilisateur getUserByLogin(String pseudo);

	void deleteUser(int idUser);

}
