package org.onn.webportal.application.boot;

import java.util.List;

import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.GeoService;
import org.onn.webportal.infra.repository.LocalisationRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class App 
{

	@Autowired
	private LocalisationRepo localisationRepo;

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private GeoService geoService;

	private void run(){
		//testTaloha();

		testActivieSertice();
	}

	private void testTaloha(){
		System.out.println("==> "+geoService.getGeoRegionByCode("23"));
		List<Localisation> regions = localisationRepo.getAllRegions();
		for(Localisation region:regions){
			System.out.println("==>"+region.getNomRegion());
		}
	}


	private void testActivieSertice(){
			List<Synthese> liste = activiteService.getActiviteSyntese("42", TypeLocalisation.REGION);
			for(Synthese syn: liste){
				System.out.println("==>"+syn.getIdIndicateur()+"  "+syn.getValeur());
			}
	}

	public static void main( String[] args )
	{
		ApplicationEntry entry = ApplicationEntry.getInstance();
		entry.getApp().run();
	}
}
