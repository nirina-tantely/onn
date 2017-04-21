package org.onn.webportal.infra.repository;

import java.util.List;

import org.onn.webportal.domain.model.User;

public interface UtilisateurRepo {

	List<User> getAllUsers();

	int saveOrUpdate(User user);

	User getUserByLogin(String pseudo);

	void deleteUser(int idUser);

}
