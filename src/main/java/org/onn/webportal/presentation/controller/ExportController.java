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

	@RequestMapping(value = "exportSynthese.do", method = RequestMethod.GET, produces={"application/json; charset=UTF-8"})
	public ModelAndView updateSynthese(@RequestParam("code") String code, @RequestParam("typeLocalisation") String typeLocalisation, 
			@RequestParam("codeIntervenant") String codeIntervenant, @RequestParam("annee") String annee, @RequestParam("legende") String legende, HttpServletResponse response, HttpSession session, HttpServletRequest request) {

		System.out.println(request.getRealPath("export/synthese_export.xsl"));
		exportService.exportSynthese(code, typeLocalisation, codeIntervenant, annee, legende, response, request);
		
		return null;
	}
}
