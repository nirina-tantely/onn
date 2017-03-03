package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Etat;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.GeoService;
import org.onn.webportal.domain.service.LocalisationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("etat")
public class MAJSynthesesController {

	private final Logger logger = LoggerFactory.getLogger(MAJSynthesesController.class);

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private LocalisationService localisationService;

	@Autowired
	private GeoService geoService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateSynthese.do", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ModelAndView updateSynthese(@RequestParam("code") String code, @RequestParam("typeLocalisation") String typeLocalisation, 
			@RequestParam("codeIntervenant") String codeIntervenant, HttpServletResponse response, HttpSession session) {
		if(code.equals("")) return null;
		Etat etat = (Etat)session.getAttribute("etat");
		if(code.equals("VIDE") && typeLocalisation.equals("VIDE")){//Changement d'intervenant
			System.out.println("Etat ===> "+etat.getNiveauLocalisation().getValeur());
			switch (etat.getNiveauLocalisation()) {
			case COMMUNE:
				code = etat.getLocalisation().getIdCommune();
				break;
			case REGION:
				code = etat.getLocalisation().getIdRegion();
				break;
			case FOKONTANY:
				code = etat.getLocalisation().getIdFokontany();
				break;
			case NATIONALE:
				code = "0";
				break;
			}
			typeLocalisation = etat.getNiveauLocalisation().getValeur();
		}else{
			switch (TypeLocalisation.getByValue(typeLocalisation)) {
			case COMMUNE:
				etat.getLocalisation().setIdCommune(code);
				break;
			case REGION:
				etat.getLocalisation().setIdRegion(code);
				break;
			case FOKONTANY:
				etat.getLocalisation().setIdFokontany(code);
				break;
			case NATIONALE:
				break;
			}
			etat.setNiveauLocalisation(TypeLocalisation.getByValue(typeLocalisation));
		}
		List<Synthese> syntheses = activiteService.getActiviteSyntese(code, TypeLocalisation.getByValue(typeLocalisation), codeIntervenant);
		JSONArray liste = new JSONArray();
		for(Synthese syn:syntheses){
			JSONObject obj = new JSONObject();
			obj.put("indicateur", syn.getDescription());
			obj.put("valeur", syn.getValeur());
			liste.add(obj);
		}
		JSONObject res = new JSONObject();
		res.put("activites", liste);


		JSONArray indicateurs = activiteService.getONGBaseSyntese(code, TypeLocalisation.getByValue(typeLocalisation));
		System.out.println("=> "+indicateurs);
		res.put("ongbase", indicateurs);

		ServletOutputStream out;
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out = response.getOutputStream();
			out.print(res.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateSMSSynthese.do", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ModelAndView updateSMSSynthese(@RequestParam("code") String code, @RequestParam("typeLocalisation") String typeLocalisation, 
			HttpServletResponse response, HttpSession session) {
		if(code.equals("")) return null;
		Etat etat = (Etat)session.getAttribute("etat");
		switch (TypeLocalisation.getByValue(typeLocalisation)) {
		case COMMUNE:
			etat.getLocalisation().setIdCommune(code);
			break;
		case REGION:
			etat.getLocalisation().setIdRegion(code);
			break;
		case FOKONTANY:
			etat.getLocalisation().setIdFokontany(code);
			break;
		case NATIONALE:
			break;
		}
		etat.setNiveauLocalisation(TypeLocalisation.getByValue(typeLocalisation));
		JSONArray indicateurs = activiteService.getSMSBaseSyntese(code, TypeLocalisation.getByValue(typeLocalisation));
		JSONObject res = new JSONObject();
		res.put("indicateurs", indicateurs);

		ServletOutputStream out;
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out = response.getOutputStream();
			out.print(res.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
