package org.onn.webportal.domain.service.impl;

import java.util.List;
import org.onn.webportal.domain.model.User;
import org.onn.webportal.domain.service.AdministrationService;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.onn.webportal.infra.repository.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrationServiceImpl implements AdministrationService  {

	@Autowired
	private UtilisateurRepo utilisateurRepo;

	@Autowired
	private MetadataRepo metadataRepo;

	public List<User> getAllUsers(){
		return utilisateurRepo.getAllUsers();
	}

	public int saveOrUpdate(User user){
		return utilisateurRepo.saveOrUpdate(user);
	}

	public User getUserByLogin(String pseudo){
		return utilisateurRepo.getUserByLogin(pseudo);
	}

	public User checkAuthenfication(String pseudo, String password){
		User user = utilisateurRepo.getUserByLogin(pseudo);
		if(user!=null){
			if(!user.getPassword().equals(password)) user = null;
		}
		return user;
	}

	public void deleteUser(int idUser) {
		utilisateurRepo.deleteUser(idUser);
	}

}
