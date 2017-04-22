
package org.onn.webportal.application.boot;

import java.util.List;
import org.json.simple.JSONArray;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.application.utils.Config;
import org.onn.webportal.domain.model.Intervenant;
import org.onn.webportal.domain.model.Localisation;
import org.onn.webportal.domain.model.Synthese;
import org.onn.webportal.domain.service.ActiviteService;
import org.onn.webportal.domain.service.ExportService;
import org.onn.webportal.domain.service.GeneralService;
import org.onn.webportal.domain.service.GeoService;
import org.onn.webportal.domain.service.ImportService;
import org.onn.webportal.infra.repository.ActiviteRepo;
import org.onn.webportal.infra.repository.GeoRepo;
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
	private GeoRepo geoRepo;

	@Autowired
	private MetadataRepo metadataRepo;
	
	@Autowired
	private GeneralService generalService;
	
	@Autowired
	private ExportService exportService;
	
	@Autowired
	private ImportService importService;

	private void run(){
		//testTaloha();

		//testActivieSertice();
		
		//testONGbase();
		//testGeneral();
		
		//testIntervenantCode();
		
		//System.out.println(Config.getInstance().getProperty("import.directory"));
		
		//testImport();
		
		testGeoInterv();
	}
	
	private void testGeoInterv(){
		String res = geoRepo.getGeoRegionByIntervenant("unicef", 2017);
		System.out.println(res);
		String res2 = geoRepo.getGeoCommuneByIntervenant("unicef", 2017);
		System.out.println(res2);
		String res3 = geoRepo.getGeoFktByIntervenant("unicef", 2017);
		System.out.println(res3);
	}
	
	private void testImport(){
		try {
			importService.importInterventionData("/Users/tantely/Documents/ETUDES/SOA/workspace/onn/src/main/webapp/import/import_activite_test.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void testSynthese(){
		//exportService.updateSynthese("0", "nationale", "unicef", "2017");
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
		JSONArray s = activiteService.getONGBaseSyntese("0", TypeLocalisation.NATIONALE, 2017, true);
		System.out.println("==> "+s);

	}

	public static void main( String[] args )
	{
		ApplicationEntry entry = ApplicationEntry.getInstance();
		entry.getApp().run();
	}
}
