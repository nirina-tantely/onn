package org.onn.webportal.domain.service.impl;

import java.util.List;
import org.onn.webportal.domain.model.Intervenant;
import org.onn.webportal.domain.service.GeneralService;
import org.onn.webportal.infra.repository.IntervenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Autowired
	private IntervenantRepo intervenantRepo;

	public List<Intervenant> getAllIntervenants() {
		return intervenantRepo.getAllIntervenants();
	}

}
