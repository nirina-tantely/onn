package org.onn.webportal.presentation.controller;

import java.util.List;
import java.util.Map;

import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.service.ActiviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainMapController {
	
	private final Logger logger = LoggerFactory.getLogger(MainMapController.class);
	
	@Autowired
	private ActiviteService activiteService;


	@RequestMapping(value = "map.do", method = RequestMethod.GET)
	public String home(Map<String, Object> model) {
		logger.debug("home() is executed!");
		Localisation localisation = new Localisation("F001","C009","R066");
		List<Activite> activites = activiteService.listeActivitesParLocalisation(localisation);
		for(Activite act:activites){
			System.out.println("==> "+act.getId());
		}
		return "mapview";
	}

}
