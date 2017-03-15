
package org.onn.webportal.application.boot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.Intervenant;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.GeneralService;
import org.onn.webportal.domain.service.GeoService;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.onn.webportal.infra.repository.LocalisationRepo;
import org.onn.webportal.infra.repository.MetadataRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class App 
{

	@Autowired
	private LocalisationRepo localisationRepo;

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private GeoService geoService;
	
	@Autowired
	private ActiviteRepo activiteRepo;

	@Autowired
	private MetadataRepo metadataRepo;
	
	@Autowired
	private GeneralService generalService;

	private void run(){
		//testTaloha();

		//testActivieSertice();
		
		testONGbase();
		//testGeneral();
		
		//testIntervenantCode();
	}

	private void testTaloha(){
		System.out.println("==> "+geoService.getGeoRegionByCode("23"));
		List<Localisation> regions = localisationRepo.getAllRegions();
		for(Localisation region:regions){
			System.out.println("==>"+region.getNomRegion());
		}
	}

	private void testIntervenantCode(){
		String codeIntervenant = "intv1";
			activiteService.getCodesRegionByIntervenant(codeIntervenant, 2017);
			activiteService.getCodesCommuneByIntervenant(codeIntervenant, 2017);
			activiteService.getCodesFokontanyByIntervenant(codeIntervenant, 2017);
	}

	private void testActivieSertice(){
			List<Synthese> liste = activiteService.getActiviteSyntese("0", TypeLocalisation.NATIONALE, "intv1", 2017);
			for(Synthese syn: liste){
				System.out.println("==>"+syn.getIdIndicateur()+"  "+syn.getValeur());
			}
	}
	
	private void testGeneral(){
		List<Intervenant> ints = generalService.getAllIntervenants();
		for(Intervenant itv:ints){
			System.out.println("==>"+itv.getNom());
		}
	}
	private void testONGbase(){
		JSONArray s = activiteService.getONGBaseSyntese("0", TypeLocalisation.NATIONALE, 2017);
		System.out.println("==> "+s);

	}

	public static void main( String[] args )
	{
		ApplicationEntry entry = ApplicationEntry.getInstance();
		entry.getApp().run();
	}
}
