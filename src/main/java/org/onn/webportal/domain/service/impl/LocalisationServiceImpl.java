package org.onn.webportal.domain.service.impl;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.service.LocalisationService;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.onn.webportal.infra.repository.LocalisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalisationServiceImpl implements LocalisationService {

@Autowired
private ActiviteRepo activiteRepo;

@Autowired
private LocalisationRepo localisationRepo;

	public List<Activite> listeActivitesParLocalisation(Localisation localisation) {
		return activiteRepo.listeActivitesParLocalisation(localisation);
	}

	public List<Localisation> getAllRegions() {
		return localisationRepo.getAllRegions();
	}
	
	@SuppressWarnings("unchecked")
	public String getCommuneListJson(String codeRegion){
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		List<Localisation> communes = localisationRepo.getCommunesByregion(codeRegion);
		JSONObject ojbComm;
		for(Localisation commune:communes){
			ojbComm = new JSONObject();
			ojbComm.put("codeCommune", commune.getIdCommune());
			ojbComm.put("nomCommune", commune.getNomcommune());
			list.add(ojbComm);
		}
		obj.put("communes", list);
		return obj.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	public String getFokontanyListJson(String codeCommune){
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();
		List<Localisation> fokontany = localisationRepo.getFokontanyByCommune(codeCommune);
		JSONObject ojbComm;
		for(Localisation fkt:fokontany){
			ojbComm = new JSONObject();
			ojbComm.put("codeFkt", fkt.getIdFokontany());
			ojbComm.put("nomFkt", fkt.getNomFokontany());
			list.add(ojbComm);
		}
		obj.put("fokontany", list);
		return obj.toJSONString();
	}

}
