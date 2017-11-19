package org.onn.webportal.domain.service.impl;

import org.onn.webportal.application.utils.CSVUtils;
import org.onn.webportal.domain.service.ImportService;
import org.onn.webportal.infra.repository.ImportDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportServiceImpl implements ImportService {

	@Autowired
	private ImportDataRepo importDataRepo;
	
	public void importInterventionData(String fileName) throws Exception{
		String[] data = CSVUtils.readLineCSV(fileName);
		int annee;
		int codeRegion;
		String codeIntervenant;
		if(data != null){
			annee = Integer.valueOf(data[0]);
			codeRegion = Integer.valueOf(data[1]);
			codeIntervenant = data[4];
		}else{
			throw new Exception("N'a pas pu lire le fichier CSV");
		}
		importDataRepo.cleanIntervenantData(annee, codeRegion, codeIntervenant);
		importDataRepo.importInterventionFile(fileName);
	}
	
	public void importONGBaseData(String fileName) throws Exception{
		String[] data = CSVUtils.readLineCSV(fileName);
		int annee;
		int codeRegion;
		int mois;
		if(data != null){
			annee = Integer.valueOf(data[0]);
			mois = Integer.valueOf(data[1]);
			codeRegion = Integer.valueOf(data[3]);
		}else{
			throw new Exception("N'a pas pu lire le fichier CSV");
		}
		importDataRepo.cleanONGBaseData(annee, mois, codeRegion);
		importDataRepo.importONGBaseFile(fileName);
	}

	public void importSMSData(String fileName) throws Exception{
		importDataRepo.importSMSFile(fileName);
	}

}
