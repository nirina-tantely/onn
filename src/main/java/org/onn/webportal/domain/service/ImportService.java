package org.onn.webportal.domain.service;

public interface ImportService {

	void importInterventionData(String fileName) throws Exception;

	void importONGBaseData(String fileName) throws Exception;

	void importSMSData(String fileName) throws Exception;
}
