package org.onn.webportal.infra.repository;

public interface ImportDataRepo {

	void importInterventionFile(String fileName) throws Exception;

	void importONGBaseFile(String fileName) throws Exception;

	void importSMSFile(String fileName) throws Exception;

}
