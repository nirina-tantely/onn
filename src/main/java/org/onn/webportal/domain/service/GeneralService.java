package org.onn.webportal.domain.service;

import java.util.List;

import org.onn.webportal.domain.model.Intervenant;

public interface GeneralService {

	List<Intervenant> getAllIntervenants();

	List<Integer> getAllAnnees();

	List<Integer> getAllSMSAnnees();

}
