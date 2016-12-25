package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.onn.webportal.domain.model.Localisation;
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
public class MainMapController {

	private final Logger logger = LoggerFactory.getLogger(MainMapController.class);

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private LocalisationService localisationService;
	
	@Autowired
	private GeoService geoService;


	@RequestMapping(value = "map.do", method = RequestMethod.GET)
	public String home(Map<String, Object> model) {
		logger.debug("home() is executed!");
		List<Localisation> allRegions = localisationService.getAllRegions();
		model.put("regions", allRegions);		
		return "mapview";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "selectRegion.do", method = RequestMethod.GET)
	public ModelAndView selectRegion(@RequestParam("codeRegion") String codeRegion, HttpServletResponse response) {
		if(codeRegion.equals("")) return null;
		JSONObject obj = new JSONObject();
		String listeCommuneJson = localisationService.getCommuneListJson(codeRegion); 
		String geoJson = geoService.getGeoRegionByCode(codeRegion);
		obj.put("liste", listeCommuneJson);
		obj.put("goeJson", geoJson);
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			out.print(obj.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "selectCommune.do", method = RequestMethod.GET)
	public ModelAndView selectCommune(@RequestParam("codeCommune") String codeCommune, HttpServletResponse response) {
		if(codeCommune.equals("")) return null;
		JSONObject obj = new JSONObject();
		String listeFktJson = localisationService.getFokontanyListJson(codeCommune); 
		String geoJson = geoService.getGeoCommuneAndFktByCode(codeCommune);
		//System.out.println("==> "+codeCommune);
		obj.put("liste", listeFktJson);
		obj.put("goeJson", geoJson);
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			out.print(obj.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value = "selectFkt.do", method = RequestMethod.GET)
	public ModelAndView selectFkt(@RequestParam("codeFkt") String codeFkt, HttpServletResponse response) {
		if(codeFkt.equals("")) return null;
		
		return null;
	}

}
