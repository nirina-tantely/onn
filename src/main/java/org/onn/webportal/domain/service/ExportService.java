package org.onn.webportal.domain.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ExportService {

	ModelAndView exportSynthese(String code, String typeLocalisation, String codeIntervenant, String annee, String legende, HttpServletResponse response, HttpServletRequest request);

	void exportONGBase(String code, String typeLocalisation, String annee, String legende, HttpServletResponse response,
			HttpServletRequest request, boolean tousIndicateurs);

	ModelAndView exportSyntheseCSV(String code, String typeLocalisation, String codeIntervenant, String annee, HttpServletResponse response, HttpServletRequest request);

	void exportONGBaseCSV(String code, String typeLocalisation, String annee, HttpServletResponse response,
			HttpServletRequest request, boolean tousIndicateurs);

}
