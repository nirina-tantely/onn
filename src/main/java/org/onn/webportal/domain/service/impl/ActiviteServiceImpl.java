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

	public List<Synthese> getActiviteSyntese(String codeLocalisation, TypeLocalisation typeLocalisation, String codeIntervenant, int annee){
		List<List<Synthese>> res = activiteRepo.getSyntheses(codeLocalisation, typeLocalisation, annee, codeIntervenant);
		Map<String, Synthese> map = new HashMap<String, Synthese>();
		Synthese synthese;
		for(List<Synthese> liste: res){
			for(Synthese syn :  liste){
				synthese = map.get(syn.getIdIndicateur());
				if(synthese!=null){
					synthese.setValeur((synthese.getValeur()+syn.getValeur())>0?1:0); //mode de calcule présence/abscence
				}else{
					map.put(syn.getIdIndicateur(), syn);
				}
			}
		}
		return new ArrayList<Synthese>(map.values());
	}

	@SuppressWarnings("unchecked")
	public JSONArray getONGBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation, int annee, boolean tousIndicarteurs){
		List<List<IndicateurONG>> resT1 = activiteRepo.getOngBase(codeLocalisation, typeLocalisation, 1, annee);
		Map<String, IndicateurONG> mapT1 = new HashMap<String, IndicateurONG>();
		IndicateurONG indicateur;
		for(List<IndicateurONG> liste: resT1){
			for(IndicateurONG indc :  liste){
				indicateur = mapT1.get(indc.getIdIndicateur());
				if(indicateur!=null){
					if(indicateur.getVals().containsKey(indc.getMois())){
						indicateur.getVals().get(indc.getMois()).add(indc.getValeur());
					}else{
						List<Float> valeurs = new ArrayList<Float>();
						valeurs.add(indc.getValeur());
						indicateur.getVals().put(indc.getMois(), valeurs);
					}
				}else{
					List<Float> valeurs = new ArrayList<Float>();
					valeurs.add(indc.getValeur());
					indc.getVals().put(indc.getMois(), valeurs);

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
					if(indicateur.getVals().containsKey(indc.getMois())){
						indicateur.getVals().get(indc.getMois()).add(indc.getValeur());
					}else{
						List<Float> valeurs = new ArrayList<Float>();
						valeurs.add(indc.getValeur());
						indicateur.getVals().put(indc.getMois(), valeurs);
					}
				}else{
					List<Float> valeurs = new ArrayList<Float>();
					valeurs.add(indc.getValeur());
					indc.getVals().put(indc.getMois(), valeurs);

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
					if(indicateur.getVals().containsKey(indc.getMois())){
						indicateur.getVals().get(indc.getMois()).add(indc.getValeur());
					}else{
						List<Float> valeurs = new ArrayList<Float>();
						valeurs.add(indc.getValeur());
						indicateur.getVals().put(indc.getMois(), valeurs);
					}
				}else{
					List<Float> valeurs = new ArrayList<Float>();
					valeurs.add(indc.getValeur());
					indc.getVals().put(indc.getMois(), valeurs);

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
					if(indicateur.getVals().containsKey(indc.getMois())){
						indicateur.getVals().get(indc.getMois()).add(indc.getValeur());
					}else{
						List<Float> valeurs = new ArrayList<Float>();
						valeurs.add(indc.getValeur());
						indicateur.getVals().put(indc.getMois(), valeurs);
					}
				}else{
					List<Float> valeurs = new ArrayList<Float>();
					valeurs.add(indc.getValeur());
					indc.getVals().put(indc.getMois(), valeurs);

					mapT4.put(indc.getIdIndicateur(), indc);
				}
			}
		}

		JSONArray liste = new JSONArray();
		JSONArray listeIncPrincipale = new JSONArray();
		if(mapT1.size()>0 || mapT2.size()>0 || mapT3.size()>0 || mapT4.size()>0){
			List<IndicateurONG> listIndc = metadataRepo.getIndicateurONGMetadata();
			for(IndicateurONG indc: listIndc){
				JSONObject obj = new JSONObject();
				obj.put("indicateur", indc.getNom());
				if(mapT1.size()>0)	obj.put("T1", mapT1.get(indc.getIdIndicateur()).valeurAfficher());else obj.put("T1", "");
				if(mapT2.size()>0)	obj.put("T2", mapT2.get(indc.getIdIndicateur()).valeurAfficher());else obj.put("T2", "");
				if(mapT3.size()>0)	obj.put("T3", mapT3.get(indc.getIdIndicateur()).valeurAfficher());else obj.put("T3", "");
				if(mapT4.size()>0)	obj.put("T4", mapT4.get(indc.getIdIndicateur()).valeurAfficher());else obj.put("T4", "");

				if(indc.isPrincipale()){
					listeIncPrincipale.add(obj);
				}else{
					liste.add(obj);
				}
			}
		}
		
		if(tousIndicarteurs){
			for (Object object : liste) {
				listeIncPrincipale.add(object);
			}
		}
		
		
		return listeIncPrincipale;
	}


	@SuppressWarnings("unchecked")
	public JSONArray getSMSBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation, int annee){
		List<List<IndicateurSMS>> res = activiteRepo.getIndicateurSMS(codeLocalisation, typeLocalisation, annee);
		Map<String, Map<Integer, IndicateurSMS>> mapmap = new HashMap<String, Map<Integer, IndicateurSMS>>();
		IndicateurSMS indicateur;
		Map<Integer, IndicateurSMS> map;
		for(List<IndicateurSMS> liste: res){
			for(IndicateurSMS indc :  liste){
				if(mapmap.containsKey(indc.getIdIndicateur())){
					map = mapmap.get(indc.getIdIndicateur());
					if(map.containsKey(indc.getMois())){
						indicateur = map.get(indc.getMois());
						indicateur.setValeur(indicateur.getValeur()+indc.getValeur());//mode de calcul
					}else{
						map.put(indc.getMois(), indc);
					}
				}else{
					map  = new HashMap<Integer, IndicateurSMS>();
					map.put(indc.getMois(), indc);
					mapmap.put(indc.getIdIndicateur(), map);
				}
			}
		}
		JSONArray liste = new JSONArray();
		List<IndicateurSMS> listIndc = metadataRepo.getIndicateurSMSMetadata();
		boolean canAdd = true;
		for(IndicateurSMS indc: listIndc){
			canAdd = true;
			JSONObject obj = new JSONObject();
			obj.put("indicateur", indc.getNom());
			if(mapmap.containsKey(indc.getIdIndicateur())){
				map = mapmap.get(indc.getIdIndicateur());
				for(int mois = 1; mois<=12; mois++){
					if(map.containsKey(mois)){
						indicateur = map.get(mois);
						obj.put("m"+mois, indicateur.getValeur());
					}else{
						obj.put("m"+mois, "");
					}
				}
			}else{
				canAdd = false;
				/*
				for(int mois = 1; mois<=12; mois++){
					obj.put("m"+mois, "");
				}
				 */
			}
			if(canAdd) liste.add(obj);
		}
		return liste;
	}

	@SuppressWarnings("unchecked")
	public JSONArray getCodesRegionByIntervenant(String codeIntervenant, int annee){
		JSONArray liste = new JSONArray();
		List<Integer> codes = activiteRepo.getCodesRegionByIntervenant(codeIntervenant, annee);
		for(int code : codes){
			liste.add(code);
		}
		return liste;
	}

	@SuppressWarnings("unchecked")
	public JSONArray getCodesCommuneByIntervenant(String codeIntervenant, int annee){
		JSONArray liste = new JSONArray();
		List<Integer> codes = activiteRepo.getCodesCommuneByIntervenant(codeIntervenant, annee);
		for(int code : codes){
			liste.add(code);
		}
		return liste;
	}

	@SuppressWarnings("unchecked")
	public JSONArray getCodesFokontanyByIntervenant(String codeIntervenant, int annee){
		JSONArray liste = new JSONArray();
		List<Integer> codes = activiteRepo.getCodesFokontanyByIntervenant(codeIntervenant, annee);
		for(int code : codes){
			liste.add(code);
		}
		return liste;
	}
}
