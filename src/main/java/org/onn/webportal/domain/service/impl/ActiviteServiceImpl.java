package org.onn.webportal.domain.service.impl;

import java.util.List;

import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiviteServiceImpl implements ActiviteService {

@Autowired
private ActiviteRepo activiteRepo;

	public List<Activite> listeActivitesParLocalisation(Localisation localisation) {
		return activiteRepo.listeActivitesParLocalisation(localisation);
	}

}
