package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
public class GeoController {

	private final Logger logger = LoggerFactory.getLogger(GeoController.class);

	@Autowired
	private GeoService geoService;
	
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
}
