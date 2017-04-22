package org.onn.webportal.presentation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.onn.webportal.domain.service.ExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@SessionAttributes("etat")
public class ExportController {

	private final Logger logger = LoggerFactory.getLogger(ExportController.class);

	@Autowired
	private ExportService exportService;

	@RequestMapping(value = "exportSynthese.do", method = RequestMethod.GET, produces={"application/pdf; charset=UTF-8"})
	public ModelAndView exportSynthese(@RequestParam("code") String code, @RequestParam("typeLocalisation") String typeLocalisation, 
			@RequestParam("codeIntervenant") String codeIntervenant, @RequestParam("annee") String annee, @RequestParam("legende") String legende, HttpServletResponse response, HttpSession session, HttpServletRequest request) {

		exportService.exportSynthese(code, typeLocalisation, codeIntervenant, annee, legende, response, request);
		
		return null;
	}
	
	@RequestMapping(value = "exportSyntheseCSV.do", method = RequestMethod.GET, produces={"text/csv; charset=UTF-8"})
	public ModelAndView exportSyntheseCSV(@RequestParam("code") String code, @RequestParam("typeLocalisation") String typeLocalisation, 
			@RequestParam("codeIntervenant") String codeIntervenant, @RequestParam("annee") String annee, HttpServletResponse response, HttpSession session, HttpServletRequest request) {

		exportService.exportSyntheseCSV(code, typeLocalisation, codeIntervenant, annee, response, request);
		
		return null;
	}
	
	
	@RequestMapping(value = "exportONGBase.do", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ModelAndView exportONGBase(@RequestParam("code") String code, @RequestParam("typeLocalisation") String typeLocalisation, 
			@RequestParam("annee") String annee, @RequestParam("legende") String legende, @RequestParam("tousIndicateurs") String tousIndicateurs, HttpServletResponse response, HttpSession session, HttpServletRequest request) {

		exportService.exportONGBase(code, typeLocalisation, annee, legende, response, request, Boolean.valueOf(tousIndicateurs));
		
		return null;
	}

	
	@RequestMapping(value = "exportONGBaseCSV.do", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ModelAndView exportONGBaseCSV(@RequestParam("code") String code, @RequestParam("typeLocalisation") String typeLocalisation, 
			@RequestParam("annee") String annee, @RequestParam("tousIndicateurs") String tousIndicateurs, HttpServletResponse response, HttpSession session, HttpServletRequest request) {

		exportService.exportONGBaseCSV(code, typeLocalisation, annee, response, request, Boolean.valueOf(tousIndicateurs));
		
		return null;
	}
}
