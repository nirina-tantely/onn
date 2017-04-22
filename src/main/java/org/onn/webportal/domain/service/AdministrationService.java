package org.onn.webportal.domain.service;

import java.util.List;

import org.onn.webportal.domain.model.User;

public interface AdministrationService {

	List<User> getAllUsers();

	int saveOrUpdate(User user);

	User getUserByLogin(String pseudo);

	void deleteUser(int idUser);

	User checkAuthenfication(String pseudo, String password);

}
