package org.onn.webportal.domain.service.impl;

import org.onn.webportal.domain.service.ImportService;
import org.onn.webportal.infra.repository.ImportDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportServiceImpl implements ImportService {

	@Autowired
	private ImportDataRepo importDataRepo;
	
	public void importInterventionData(String fileName) throws Exception{
		importDataRepo.importInterventionFile(fileName);
	}
	
	public void importONGBaseData(String fileName) throws Exception{
		importDataRepo.importONGBaseFile(fileName);
	}

	public void importSMSData(String fileName) throws Exception{
		importDataRepo.importSMSFile(fileName);
	}

}
