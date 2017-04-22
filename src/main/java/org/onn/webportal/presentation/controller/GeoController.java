package org.onn.webportal.presentation.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.application.utils.CSVUtils;
import org.onn.webportal.domain.model.Etat;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("etat")
public class GeoController {

	private final Logger logger = LoggerFactory.getLogger(GeoController.class);

	@Autowired
	private GeoService geoService;

	@Autowired
	private ActiviteService activiteService;

	@RequestMapping(value = "geojsonRegion.do", method = RequestMethod.GET)
	public ModelAndView selectCommune(@RequestParam("codeRegion") String codeRegion, HttpServletResponse response) {
		if(codeRegion.equals("")) return null;
		String geoJson = geoService.getGeoRegionByCode(codeRegion); 
		//System.out.println("==> "+codeRegion);
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			out.print(geoJson);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "surligner.do", method = RequestMethod.GET)
	public ModelAndView highlightIntervenant(@RequestParam("codeIntervenant") String codeIntervenant, @RequestParam("typeLocalisation") String typeLocalisation,
			@RequestParam("annee") String annee, HttpServletResponse response, HttpSession session) {
		
		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}
		
		JSONArray codes;
		switch (TypeLocalisation.getByValue(typeLocalisation)) {
		case NATIONALE:
			codes = activiteService.getCodesRegionByIntervenant(codeIntervenant, anneeVal);
			break;
		case REGION:
			codes = activiteService.getCodesCommuneByIntervenant(codeIntervenant, anneeVal);
			break;
		case COMMUNE:
			codes = activiteService.getCodesFokontanyByIntervenant(codeIntervenant, anneeVal);
			break;
		default:
			codes = new JSONArray();
			break;
		}
		JSONObject res = new JSONObject();
		res.put("codes", codes);
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			out.print(res.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "downloadGeoJson.do", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ModelAndView downloadGeoJsonIntervenant(@RequestParam("codeIntervenant") String codeIntervenant, @RequestParam("typeLocalisation") String typeLocalisation,
			@RequestParam("annee") String annee, HttpServletResponse response, HttpSession session) {
		
		Integer anneeVal;
		try{
			anneeVal = Integer.valueOf(annee);
		}catch (Exception e) {
			anneeVal = Calendar.getInstance().get(Calendar.YEAR);
		}
		
		String jsonStr = geoService.getGeoJsonByIntervenant(codeIntervenant, anneeVal, typeLocalisation);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			out.write(jsonStr.getBytes(Charset.forName("UTF-8")));
			
			//Prepare response
			response.setContentType("application/json");
			String name = "jsono_"+typeLocalisation;
			response.setHeader("Content-disposition", "attachment;filename=" + name + ".json");
			response.setHeader("Content-Type", "application/json; charset=UTF-8");
			response.setContentLength(out.size());

			//Send content to Browser
			response.getOutputStream().write(out.toByteArray());
			response.getOutputStream().flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
