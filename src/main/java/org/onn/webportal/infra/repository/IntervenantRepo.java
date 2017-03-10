package org.onn.webportal.infra.repository;

import java.util.List;

import org.onn.webportal.domain.model.Intervenant;

public interface IntervenantRepo {

	List<Intervenant> getAllIntervenants();

	List<Integer> getAllAnnees();

	List<Integer> getAllSMSAnnees();

	List<Integer> getAllONGAnnees();

}
