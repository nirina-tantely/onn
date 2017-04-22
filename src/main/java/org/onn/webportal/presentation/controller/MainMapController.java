
package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.Calendar;
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
public class MainMapController {

	private final Logger logger = LoggerFactory.getLogger(MainMapController.class);

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private LocalisationService localisationService;
	
	@Autowired
	private GeoService geoService;
	
	@Autowired
	private GeneralService generalService;
	
	@RequestMapping(value = "map.do", method = RequestMethod.GET)
	public String home(Map<String, Object> model) {
		logger.debug("home() is executed!");
		List<Localisation> allRegions = localisationService.getAllRegions();
		model.put("regions", allRegions);		
		List<Intervenant> intervenants = generalService.getAllIntervenants();
		List<Integer> annees = generalService.getAllAnnees();
		model.put("intervenants", intervenants);
		model.put("annees", annees);
		model.put("anneeCourante", Calendar.getInstance().get(Calendar.YEAR));
		model.put("currentView", "HOME");//si la page courante est sms et MAP si la page courante est l'acceuil

		return "mapview";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "selectRegion.do", method = RequestMethod.GET)
	public ModelAndView selectRegion(@RequestParam("codeRegion") String codeRegion, @RequestParam("nomRegion") String nomRegion, HttpServletResponse response, HttpSession session) {
		
		if(codeRegion.equals("")) return null;
		JSONObject obj = new JSONObject();
		String listeCommuneJson = localisationService.getCommuneListJson(codeRegion); 
		String geoJson = geoService.getGeoRegionByCode(codeRegion);
		obj.put("liste", listeCommuneJson);
		obj.put("goeJson", geoJson);
		obj.put("chemin", "");
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
	public ModelAndView selectCommune(@RequestParam("codeCommune") String codeCommune, @RequestParam("nomCommune") String nomCommune, HttpServletResponse response, HttpSession session) {
		
		if(codeCommune.equals("")) return null;
		JSONObject obj = new JSONObject();
		String listeFktJson = localisationService.getFokontanyListJson(codeCommune); 
		String geoJson = geoService.getGeoCommuneAndFktByCode(codeCommune);
		//System.out.println("==> "+codeCommune);
		obj.put("liste", listeFktJson);
		obj.put("goeJson", geoJson);
		obj.put("chemin", "");
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
	@RequestMapping(value = "selectAllFktn.do", method = RequestMethod.GET)
	public ModelAndView selectAllFktn(HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		String geoJson = geoService.getGeoAllFkt();
		//System.out.println("==> "+codeCommune);
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
	public ModelAndView selectFkt(@RequestParam("codeFkt") String codeFkt, HttpServletResponse response, HttpSession session) {
		if(codeFkt.equals("")) return null;
		
		return null;
	}
	
	@SuppressWarnings("unused")
	private String genererChemin(Etat etat){
		String chemin = "";
		
		switch (etat.getNiveauLocalisation()) {
		case NATIONALE:
			break;
		case REGION:
			if(etat.getLocalisation().getNomRegion()!=null) chemin += etat.getLocalisation().getNomRegion();
			break;
		case COMMUNE:
			if(etat.getLocalisation().getNomRegion()!=null) chemin += etat.getLocalisation().getNomRegion();
			chemin+=">";
			if(etat.getLocalisation().getNomcommune()!=null) chemin += etat.getLocalisation().getNomcommune();
			break;
		case FOKONTANY:
			if(etat.getLocalisation().getNomRegion()!=null) chemin += etat.getLocalisation().getNomRegion();
			chemin+=">";
			if(etat.getLocalisation().getNomcommune()!=null) chemin += etat.getLocalisation().getNomcommune();
			chemin+=">";
			if(etat.getLocalisation().getNomFokontany()!=null) chemin += etat.getLocalisation().getNomFokontany();
			break;
		}
		chemin+=">";
		return chemin;
	}

}
