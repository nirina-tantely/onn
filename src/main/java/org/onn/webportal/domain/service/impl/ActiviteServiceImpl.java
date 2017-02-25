package org.onn.webportal.domain.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Activite;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.IndicateurSMS;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiviteServiceImpl implements ActiviteService {

	@Autowired
	private ActiviteRepo activiteRepo;

	public List<Activite> listeActivitesParLocalisation(Localisation localisation) {
		return activiteRepo.listeActivitesParLocalisation(localisation);
	}

	public List<Synthese> getActiviteSyntese(String codeLocalisation, TypeLocalisation typeLocalisation){
		List<List<Synthese>> res = activiteRepo.getSyntheses(codeLocalisation, typeLocalisation);
		Map<String, Synthese> map = new HashMap<String, Synthese>();
		Synthese synthese;
		for(List<Synthese> liste: res){
			for(Synthese syn :  liste){
				synthese = map.get(syn.getIdIndicateur());
				if(synthese!=null){
					synthese.setValeur(synthese.getValeur()+syn.getValeur());
				}else{
					map.put(syn.getIdIndicateur(), syn);
				}
			}
		}
		return new ArrayList<Synthese>(map.values());
	}

	public List<IndicateurONG> getONGBaseSyntese(String codeLocalisation, TypeLocalisation typeLocalisation){
		List<List<IndicateurONG>> res = activiteRepo.getOngBase(codeLocalisation, typeLocalisation);
		Map<String, IndicateurONG> map = new HashMap<String, IndicateurONG>();
		IndicateurONG indicateur;
		for(List<IndicateurONG> liste: res){
			for(IndicateurONG indc :  liste){
				indicateur = map.get(indc.getIdIndicateur());
				if(indicateur!=null){
					indicateur.setValeur(indicateur.getValeur()+indc.getValeur());
				}else{
					map.put(indc.getIdIndicateur(), indc);
				}
			}
		}
		return new ArrayList<IndicateurONG>(map.values());
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
