package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.service.ActiviteService;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
//@SessionAttributes("etat")
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
			@RequestParam("codeIntervenant") String codeIntervenant, @RequestParam("annee") String annee, @RequestParam("afficherTousIndicateurs") String afficherTousIndicateurs,
			Model model, HttpServletResponse response, HttpSession session) {
		if(code.equals("")) return null;
		
		boolean tousIndicarteurs = Boolean.valueOf(afficherTousIndicateurs);
		
		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}
		
		List<Synthese> syntheses = activiteService.getActiviteSyntese(code, TypeLocalisation.getByValue(typeLocalisation), codeIntervenant, anneeVal);
		JSONArray liste = new JSONArray();
		for(Synthese syn:syntheses){
			JSONObject obj = new JSONObject();
			obj.put("indicateur", syn.getDescription());
			obj.put("valeur", syn.getValeur());
			liste.add(obj);
		}
		JSONObject res = new JSONObject();
		res.put("activites", liste);


		JSONArray indicateurs = activiteService.getONGBaseSyntese(code, TypeLocalisation.getByValue(typeLocalisation), anneeVal, tousIndicarteurs);
		res.put("ongbase", indicateurs);
		
		res.put("afficherTousIndicateurs", tousIndicarteurs);

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
			@RequestParam("annee") String annee, HttpServletResponse response, HttpSession session) {
		if(code.equals("")) return null;
		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}

		JSONArray indicateurs = activiteService.getSMSBaseSyntese(code, TypeLocalisation.getByValue(typeLocalisation), anneeVal);
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
