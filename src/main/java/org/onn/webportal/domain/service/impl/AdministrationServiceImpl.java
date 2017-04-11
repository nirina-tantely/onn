package org.onn.webportal.domain.service.impl;

import java.util.List;
import org.onn.webportal.domain.model.Utilisateur;
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

	public List<Utilisateur> getAllUsers(){
		return utilisateurRepo.getAllUsers();
	}

	public int saveOrUpdate(Utilisateur user){
		return utilisateurRepo.saveOrUpdate(user);
	}
	
	public Utilisateur getUserByLogin(String pseudo){
		return utilisateurRepo.getUserByLogin(pseudo);
	}

	public void deleteUser(int idUser) {
		utilisateurRepo.deleteUser(idUser);
	}

}
