package org.onn.webportal.infra.repository;

public interface ImportDataRepo {

	void importInterventionFile(String fileName) throws Exception;

	void importONGBaseFile(String fileName) throws Exception;

	void importSMSFile(String fileName) throws Exception;

	void cleanIntervenantData(int annee, int codeRegion, String codeIntervenant);

	void cleanONGBaseData(int annee, int mois, int codeRegion);

}
