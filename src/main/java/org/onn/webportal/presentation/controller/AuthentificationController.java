package org.onn.webportal.presentation.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.onn.webportal.domain.model.User;
import org.onn.webportal.domain.service.AdministrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("currentuser")
public class AuthentificationController {

	private final Logger logger = LoggerFactory.getLogger(AuthentificationController.class);


	@Autowired
	private AdministrationService administrationService;


	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {

		return "/login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "authentifier.do", method = RequestMethod.POST)
	public void authentifier(@RequestParam("pseudo") String pseudo, @RequestParam("password") String password, Model model, HttpServletResponse response) {

		User user = administrationService.checkAuthenfication(pseudo, password);

		JSONObject obj = new JSONObject();
		if(user!=null){//authentification ok
			obj.put("Result", "SUCCES");
			obj.put("Message", "Connexion reussie!");

			model.addAttribute("currentuser", user);

		}else{
			obj.put("Result", "FAILURE");
			obj.put("Message", "Echec! Compte inconnu!");
		}
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			out.print(obj.toJSONString());
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@RequestMapping(value = "deconnecter.do", method = RequestMethod.GET)
	public String deconnexion(HttpSession session, SessionStatus status) {
		status.setComplete();
		session.removeAttribute("currentuser");
		return "/login";
	}

}