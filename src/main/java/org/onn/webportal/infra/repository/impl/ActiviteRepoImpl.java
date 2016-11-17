package org.onn.webportal.infra.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.springframework.stereotype.Repository;

@Repository
public class ActiviteRepoImpl implements ActiviteRepo {

	public List<Activite> listeActivitesParLocalisation(Localisation localisation) {
		Activite act1 = new Activite("activite1");
		Activite act2 = new Activite("activite2");
		List<Activite> liste = new ArrayList<Activite>();
		liste.add(act1);
		liste.add(act2);
		return liste;
	}

}
