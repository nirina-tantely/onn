package org.onn.webportal.domain.service;

import java.util.List;

import org.onn.webportal.domain.model.Utilisateur;

public interface AdministrationService {

	List<Utilisateur> getAllUsers();

	int saveOrUpdate(Utilisateur user);

	Utilisateur getUserByLogin(String pseudo);

	void deleteUser(int idUser);

}
