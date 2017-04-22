package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.EnumRole;
import org.onn.webportal.domain.model.User;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.AdministrationService;
import org.onn.webportal.domain.service.GeneralService;
import org.onn.webportal.domain.service.GeoService;
import org.onn.webportal.domain.service.LocalisationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("etat")
public class GestionAccesController {

	private final Logger logger = LoggerFactory.getLogger(GestionAccesController.class);

	@Autowired
	private AdministrationService administrationService;

	@Autowired
	private LocalisationService localisationService;

	@Autowired
	private GeoService geoService;

	@Autowired
	private GeneralService generalService;
	
	@Autowired
	private MainMapController mainMapController;


	@RequestMapping(value = "gestion_acces.do", method = RequestMethod.GET)
	public String gestionAcces(Map<String, Object> model, HttpSession session) {
		logger.debug("Gestion d'acces is executed!");
		
		User currentuser = (User) session.getAttribute("currentuser");
		if(currentuser!=null && currentuser.getIdUtilisateur()>0){
			if(!(currentuser.getRole().equals(EnumRole.ADMIN) || currentuser.getRole().equals(EnumRole.PTF_USER))){
				return mainMapController.home(model);
			}
		}else{
			return "/login";
		}
		
		List<User> allUsers = administrationService.getAllUsers();
		model.put("users", allUsers);
		model.put("roles", EnumRole.values());
		return "gestionutilisateur";
	}


	@RequestMapping(value = "addUser.do", method = RequestMethod.POST)
	public String addUser(@RequestBody MultiValueMap<String,String> formData,Map<String, Object> model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.debug("Ajout d'un utilisateur");
		User utilisateur = new User();
		if(!formData.get("id").get(0).isEmpty()) utilisateur.setIdUtilisateur(Integer.valueOf(formData.get("id").get(0)));
		if(!formData.get("nom").get(0).isEmpty()) utilisateur.setNom(formData.get("nom").get(0));
		if(!formData.get("pseudo").get(0).isEmpty()) utilisateur.setPseudo(formData.get("pseudo").get(0));
		if(!formData.get("password").get(0).isEmpty()) utilisateur.setPassword(formData.get("password").get(0));
		if(!formData.get("role").get(0).isEmpty() && !formData.get("role").get(0).equals("VIDE")) utilisateur.setRole(EnumRole.getById(Integer.valueOf(formData.get("role").get(0))));
		administrationService.saveOrUpdate(utilisateur);
		return gestionAcces(model, session);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "checkUser.do", method = RequestMethod.GET)
	public void checkUser(@RequestParam("pseudo") String pseudo, Map<String, Object> model, HttpServletResponse response, HttpSession session) {
		User user = administrationService.getUserByLogin(pseudo);
		JSONObject obj = new JSONObject();
		if(user!=null){//l'utilisateur existe déjà
			obj.put("Error", "OUI");
			obj.put("Message", "L'utilisateur Existe déjà!");
		}else{
			obj.put("Error", "NON");
		}
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			out.print(obj.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getUserDetail.do", method = RequestMethod.GET)
	public void getUserDetail(@RequestParam("pseudo") String pseudo, Map<String, Object> model, HttpServletResponse response, HttpSession session) {
		User user = administrationService.getUserByLogin(pseudo);
		JSONObject obj = new JSONObject();
		if(user!=null){//l'utilisateur existe déjà
			JSONObject userJson = new JSONObject();
			userJson.put("nom", user.getNom());
			userJson.put("pseudo", user.getPseudo());
			userJson.put("id", user.getIdUtilisateur());
			userJson.put("password", user.getPassword());
			userJson.put("role", user.getRole().getId());
			obj.put("utilisateur", userJson);
		}
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			out.print(obj.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "deleteUser.do", method = RequestMethod.GET)
	public void deleteUser(@RequestParam("id") String id, Map<String, Object> model, HttpServletResponse response, HttpSession session) {
		int idUser = Integer.valueOf(id);
		if(idUser>0){
			administrationService.deleteUser(idUser);
		}
	}
}
