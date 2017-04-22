package org.onn.webportal.presentation.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.onn.webportal.api.enumeration.EnumRole;
import org.onn.webportal.application.utils.Config;
import org.onn.webportal.domain.model.User;
import org.onn.webportal.domain.service.AdministrationService;
import org.onn.webportal.domain.service.GeneralService;
import org.onn.webportal.domain.service.GeoService;
import org.onn.webportal.domain.service.ImportService;
import org.onn.webportal.domain.service.LocalisationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("etat")
public class AdministrationController {

	private final Logger logger = LoggerFactory.getLogger(AdministrationController.class);

	@Autowired
	private AdministrationService administrationService;

	@Autowired
	private ImportService importService;
	
	@Autowired
	private MainMapController mainMapController;

	@Autowired
	private GeoService geoService;

	@Autowired
	private GeneralService generalService;


	@RequestMapping(value = "gestion_donnes.do", method = RequestMethod.GET)
	public String gestionAcces(Map<String, Object> model, HttpSession session) {
		logger.debug("Gestion des donnees is executed!");
		User currentuser = (User) session.getAttribute("currentuser");
		if(currentuser!=null && currentuser.getIdUtilisateur()>0){
			if(!(currentuser.getRole().equals(EnumRole.ADMIN) || currentuser.getRole().equals(EnumRole.PTF_USER))){
				return mainMapController.home(model);
			}
		}else{
			return "/login";
		}
		return "gestiondonnees";
	}

	@RequestMapping(value = "importIntervention.do", headers=("content-type=multipart/*"), method = RequestMethod.POST)
	public String importerIntervention(Map<String, Object> model, @RequestParam("interventionFile") MultipartFile fichier, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {

		if(fichier==null) return gestionAcces(model, session);
		
		String result = "Import terminé avec succés";
		
		String destination = Config.getInstance().getProperty("import.directory")+fichier.getOriginalFilename();
	    File file = new File(destination);
	    
	    try {
			fichier.transferTo(file);
			file.setReadable(true, false);
		    file.setExecutable(true, false);
		    file.setWritable(true, false);
			importService.importInterventionData(destination);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		}
	    
	    model.put("importResult", result);
	    
		return gestionAcces(model, session);
	}
	
	
	@RequestMapping(value = "importONGBase.do", headers=("content-type=multipart/*"), method = RequestMethod.POST)
	public String importerONGBase(Map<String, Object> model, @RequestParam("ongbaseFile") MultipartFile fichier, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {

		if(fichier==null) return gestionAcces(model, session);
		
		String result = "Import terminé avec succés";
		
		String destination = Config.getInstance().getProperty("import.directory")+fichier.getOriginalFilename();
	    File file = new File(destination);
	    
	    try {
			fichier.transferTo(file);
			file.setReadable(true, false);
		    file.setExecutable(true, false);
		    file.setWritable(true, false);
			importService.importONGBaseData(destination);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		}
	    
	    model.put("importResult", result);
	    
		return gestionAcces(model, session);
	}
	
	@RequestMapping(value = "importSMS.do", headers=("content-type=multipart/*"), method = RequestMethod.POST)
	public String importerSMS(Map<String, Object> model, @RequestParam("smsFile") MultipartFile fichier, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpSession session) {

		if(fichier==null) return gestionAcces(model, session);
		
		String result = "Import terminé avec succés";
		
		String destination = Config.getInstance().getProperty("import.directory")+fichier.getOriginalFilename();
	    File file = new File(destination);
	    
	    try {
			fichier.transferTo(file);
			file.setReadable(true, false);
		    file.setExecutable(true, false);
		    file.setWritable(true, false);
			importService.importSMSData(destination);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		}
	    
	    model.put("importResult", result);
	    
		return gestionAcces(model, session);
	}
	
}
