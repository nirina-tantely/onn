package org.onn.webportal.domain.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.IndicateurSMS;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiviteServiceImpl implements ActiviteService {

	@Autowired
	private ActiviteRepo activiteRepo;

	@Autowired
	private MetadataRepo metadataRepo;

	public List<Activite> listeActivitesParLocalisation(Localisation localisation) {
		return activiteRepo.listeActivitesParLocalisation(localisation);
	}

	public List<Synthese> getActiviteSyntese(String codeLocalisation, TypeLocalisation typeLocalisation){
		int annee = Calendar.getInstance().get(Calendar.YEAR);
		List<List<Synthese>> res = activiteRepo.getSyntheses(codeLocalisation, typeLocalisation, annee);
		Map<String, Synthese> map = new HashMap<String, Synthese>();
		Synthese synthese;
		for(List<Synthese> liste: res){
			for(Synthese syn :  liste){
				synthese = map.get(syn.getIdIndicateur());
				if(synthese!=null){
					synthese.setValeur(synthese.getValeur()+syn.getValeur()); //mode de calcule
				}else{
					map.put(syn.getIdIndicateur(), syn);
				}
			}
		}
		return new ArrayList<Synthese>(map.values());
	}

	@SuppressWarnings("unchecked")
	public JSONArray getONGBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation){
		int annee = Calendar.getInstance().get(Calendar.YEAR);
		List<List<IndicateurONG>> resT1 = activiteRepo.getOngBase(codeLocalisation, typeLocalisation, 1, annee);
		Map<String, IndicateurONG> mapT1 = new HashMap<String, IndicateurONG>();
		IndicateurONG indicateur;
		for(List<IndicateurONG> liste: resT1){
			for(IndicateurONG indc :  liste){
				indicateur = mapT1.get(indc.getIdIndicateur());
				if(indicateur!=null){
					indicateur.setValeur(modeCalcule(indicateur.getValeur(),indc.getValeur()));//mode de calcul
				}else{
					mapT1.put(indc.getIdIndicateur(), indc);
				}
			}
		}

		List<List<IndicateurONG>> resT2 = activiteRepo.getOngBase(codeLocalisation, typeLocalisation, 2, annee);
		Map<String, IndicateurONG> mapT2 = new HashMap<String, IndicateurONG>();
		for(List<IndicateurONG> liste: resT2){
			for(IndicateurONG indc :  liste){
				indicateur = mapT2.get(indc.getIdIndicateur());
				if(indicateur!=null){
					indicateur.setValeur(modeCalcule(indicateur.getValeur(),indc.getValeur()));//mode de calcul
				}else{
					mapT2.put(indc.getIdIndicateur(), indc);
				}
			}
		}

		List<List<IndicateurONG>> resT3 = activiteRepo.getOngBase(codeLocalisation, typeLocalisation, 3, annee);
		Map<String, IndicateurONG> mapT3 = new HashMap<String, IndicateurONG>();
		for(List<IndicateurONG> liste: resT3){
			for(IndicateurONG indc :  liste){
				indicateur = mapT3.get(indc.getIdIndicateur());
				if(indicateur!=null){
					indicateur.setValeur(modeCalcule(indicateur.getValeur(),indc.getValeur()));//mode de calcul
				}else{
					mapT3.put(indc.getIdIndicateur(), indc);
				}
			}
		}

		List<List<IndicateurONG>> resT4 = activiteRepo.getOngBase(codeLocalisation, typeLocalisation, 4, annee);
		Map<String, IndicateurONG> mapT4 = new HashMap<String, IndicateurONG>();
		for(List<IndicateurONG> liste: resT4){
			for(IndicateurONG indc :  liste){
				indicateur = mapT4.get(indc.getIdIndicateur());
				if(indicateur!=null){
					indicateur.setValeur(modeCalcule(indicateur.getValeur(),indc.getValeur()));//mode de calcul
				}else{
					mapT4.put(indc.getIdIndicateur(), indc);
				}
			}
		}

		JSONArray liste = new JSONArray();
		for(IndicateurONG indc: metadataRepo.getIndicateurONGMetadata()){
			JSONObject obj = new JSONObject();
			obj.put("indicateur", indc.getNom());
			if(mapT1.size()>0)	obj.put("T1", mapT1.get(indc.getIdIndicateur()).getValeur());else obj.put("T1", "");
			if(mapT2.size()>0)	obj.put("T2", mapT2.get(indc.getIdIndicateur()).getValeur());else obj.put("T2", "");
			if(mapT3.size()>0)	obj.put("T3", mapT3.get(indc.getIdIndicateur()).getValeur());else obj.put("T3", "");
			if(mapT4.size()>0)	obj.put("T4", mapT4.get(indc.getIdIndicateur()).getValeur());else obj.put("T4", "");
			liste.add(obj);
		}

		return liste;
	}

	private int modeCalcule(int val1, int val2){
		return val1 + val2;
	}

	public List<IndicateurSMS> getSMSBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation){
		List<List<IndicateurSMS>> res = activiteRepo.getIndicateurSMS(codeLocalisation, typeLocalisation);
		Map<String, IndicateurSMS> map = new HashMap<String, IndicateurSMS>();
		IndicateurSMS indicateur;
		for(List<IndicateurSMS> liste: res){
			for(IndicateurSMS indc :  liste){
				indicateur = map.get(indc.getIdIndicateur());
				if(indicateur!=null){
					indicateur.setValeur(indicateur.getValeur()+indc.getValeur());
				}else{
					map.put(indc.getIdIndicateur(), indc);
				}
			}
		}
		return new ArrayList<IndicateurSMS>(map.values());
	}

}
