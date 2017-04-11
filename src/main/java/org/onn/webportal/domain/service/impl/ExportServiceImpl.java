package org.onn.webportal.domain.service.impl;

import java.util.Calendar;
import java.util.List;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.ExportService;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ExportServiceImpl implements ExportService {

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private MetadataRepo metadataRepo;

	public ModelAndView updateSynthese(String code, String typeLocalisation, String codeIntervenant, String annee) {
		if(code.equals("")) return null;
		
		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}
		
		List<Synthese> synthReses = activiteService.getActiviteSyntese(code, TypeLocalisation.getByValue(typeLocalisation), codeIntervenant, anneeVal);
		
		return null;
	}

	
}
