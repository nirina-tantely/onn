package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.TypeLocalisation;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
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
	public ModelAndView selectRegion(@RequestParam("code") String code, @RequestParam("typeLocalisation") String typeLocalisation, HttpServletResponse response) {
		if(code.equals("")) return null;
		List<Synthese> syntheses = activiteService.getActiviteSyntese(code, TypeLocalisation.getByValue(typeLocalisation));
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
}
