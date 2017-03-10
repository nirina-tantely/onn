package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Etat;
import org.onn.webportal.domain.model.Intervenant;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.GeneralService;
import org.onn.webportal.domain.service.GeoService;
import org.onn.webportal.domain.service.LocalisationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("etat")
public class SMSMapController {

	private final Logger logger = LoggerFactory.getLogger(SMSMapController.class);

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private LocalisationService localisationService;
	
	@Autowired
	private GeoService geoService;
	
	@Autowired
	private GeneralService generalService;


	@RequestMapping(value = "smsmap.do", method = RequestMethod.GET)
	public String smsHome(Map<String, Object> model) {
		logger.debug("sms home is executed!");
		List<Localisation> allRegions = localisationService.getAllRegions();
		model.put("regions", allRegions);		
		List<Intervenant> intervenants = generalService.getAllIntervenants();
		List<Integer> annees = generalService.getAllSMSAnnees();
		model.put("intervenants", intervenants);
		model.put("annees", annees);
		model.put("currentView", "SMS");//si la page courante est sms et MAP si la page courante est l'acceuil
		
		Etat etat = new Etat();
		etat.setNiveauLocalisation(TypeLocalisation.NATIONALE);
		etat.setIntervenant(new Intervenant("0","Tout",""));
		model.put("etat", etat);
		
		return "smsview";
	}
}
