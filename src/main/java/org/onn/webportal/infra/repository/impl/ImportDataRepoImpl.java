package org.onn.webportal.infra.repository.impl;

import java.io.File;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.onn.webportal.infra.repository.ImportDataRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImportDataRepoImpl implements ImportDataRepo {

	private final Logger logger = LoggerFactory.getLogger(GeoRepoImpl.class);
	protected JdbcTemplate jdbcTemplate;

	@Resource(name = "dataSourceONN")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void importInterventionFile(String fileName) throws Exception {
		logger.info("Importation du fichier "+fileName);
		//StringBuilder requete = new StringBuilder().append("COPY activite (annee,code_region,code_commune,code_fokontany,code_intervenant,a1it1a1,a1it2a1,a1it2a2,a1it2a3,a1it3a1,a1it3a2,a1it4a1,a1it5a1,a1it6a1,a1it6a2,a1it6a3,a1it6a4,a1it6a5,a1it7a1,a1it7a2,a1it7a3,a1it7a4,a1it8a1,a1it8a2,a1it9a1,a1it9a2,a2it1a1,a2it1a2,a2it1a3,a2it2a1,a2it3a1,a2it3a2,a2it4a1,a2it5a1,a2it5a2,a2it5a3,a2it5a4,a2it5a5,a3it1a1,a3it1a2,a3it1a3,a3it1a4,a3it1a5,a3it1a6,a3it1a7,a4it1a1,a4it1a2,a4it1a3,a4it2a1,a4it2a2,a4it2a3) FROM '"+fileName+"' WITH DELIMITER ';' CSV HEADER;");
		StringBuilder requete = new StringBuilder().append("COPY activite (annee,nom_region,nom_commune,nom_loca,code_region,code_commune,code_fokontany,code_intervenant,ACT001,ACT002,ACT003,ACT004,ACT005,ACT006,ACT007,ACT008,ACT009,ACT010,ACT011,ACT012,ACT013,ACT014,ACT015,ACT016,ACT017,ACT018,ACT019,ACT020,ACT021,ACT022,ACT023,ACT024,ACT025,ACT026,ACT027,ACT028,ACT029,ACT030,ACT031,ACT032,ACT033,ACT034,ACT035,ACT036,ACT037,ACT038,ACT039,ACT040,ACT041,ACT042,ACT043,ACT044,ACT045,ACT046,ACT047,ACT048,ACT049,ACT050,ACT051,ACT052,ACT053,ACT054,ACT055,ACT056,ACT057,ACT058,ACT059,ACT060,ACT061,ACT062,ACT063,ACT064,ACT065,ACT066,ACT067,ACT068,ACT069,ACT070,ACT071,ACT072,ACT073,ACT074,ACT075,ACT076,ACT077,ACT078,ACT079,ACT080,ACT081,ACT082,ACT083,ACT084,ACT085,ACT086,ACT087,ACT088,ACT089,ACT090,ACT091,ACT092,ACT093,ACT094,ACT095,ACT096,ACT097,ACT098,ACT099,ACT100) FROM '"+fileName+"' WITH DELIMITER ';' CSV HEADER;");
		jdbcTemplate.update(requete.toString());
	}
	
	
	public void importONGBaseFile(String fileName) throws Exception {
		logger.info("Importation du fichier "+fileName);
		StringBuilder requete = new StringBuilder().append("COPY ongbase (annee,mois,trimestre,nom_region,nom_commune,nom_loca,code_region,code_commune,code_fokontany,indco11,indco12,indco13,indco21,indco22,indco23,indco24,indco25,indco31,indco41,indco51,indco52,indco53,indco54,indco61,indco62,indco63,indco64,indco71,indco72,indco73,indco74,indco81,indco82,indco83,indco84,indco91,indco92,indco93,indco94,indc101,indc102,indc103,indc104,indc111,indc112,indc113,indc114,indc121,indc122,indc123,indc124,indc131,indc132,indc133,indc134,indc141,indc142,indc143,indc144,indc151,indc152,indc153,indc154,indc161,indc162,indc163,indc164,indc171,indc172,indc173,indc174,indc181,indc182,indc183,indc184,indc191,indc192,indc193,indc194,indc201,indc202,indc203,indc204,indc211,indc212,indc213,indc214,indc221,indc222,indc223,indc224,indc231,indc232,indc233,indc234,indc241,indc242,indc243,indc244,indc251,indc261,indc271,indc272,indc273,indc274,indc281,indc291,indc292,indc293,indc294,indc301,indc302,indc303,indc304,indc311,indc321,indc331,indc341,indc351,indc361,indc371,indc381,indc391,indc401,indc411,indc421,indc422,indc431,indc432,indc441,indc442,indc451,indc452,indc461,indc462,indc471,indc472,indc481,indc482,indc491,indc492,indc501,indc502,indc511,indc512,indc521,indc522,indc531,indc532,indc541,indc551,indc552,indc553,indc561,indc562,indc571,indc572,indc581,indc582,indc591,indc601,indc611,indc621,indc631,indc641,indc651,indc661,indc671) FROM '"+fileName+"' WITH DELIMITER ';' CSV HEADER;");
		jdbcTemplate.update(requete.toString());
	}
	
	public void importSMSFile(String fileName) throws Exception {
		logger.info("Importation du fichier "+fileName);
		StringBuilder requete = new StringBuilder().append("COPY donnees_sms (annee,mois,nom_region,nom_commune,nom_loca,code_region,code_commune,code_fokontany,code_intervenant,indcsms1,indcsms2,indcsms3,indcsms4,indcsms5,indcsms6,indcsms7,indcsms8,indcsms9,indcsms10,indcsms11,indcsms12,indcsms13,indcsms14,indcsms15,indcsms16,indcsms17,indcsms18,indcsms19,indcsms20) FROM '"+fileName+"' WITH DELIMITER ';' CSV HEADER;");
		jdbcTemplate.update(requete.toString());
	}
	
	public void cleanIntervenantData(int annee, int codeRegion, String codeIntervenant) {
		logger.info("Suppression des donnees "+annee+"  "+codeRegion+"  "+codeIntervenant);
		StringBuilder requete = new StringBuilder().append("DELETE FROM activite WHERE annee = ? AND code_region = ? AND code_intervenant = ?;");
		jdbcTemplate.update(requete.toString(), new Object[] {annee, codeRegion, codeIntervenant});
	}
	
	public void cleanONGBaseData(int annee, int mois, int codeRegion) {
		logger.info("Suppression des donnees "+annee+"  "+codeRegion+"  "+mois);
		StringBuilder requete = new StringBuilder().append("DELETE FROM ongbase WHERE annee = ? AND mois = ? AND code_region = ?;");
		jdbcTemplate.update(requete.toString(), new Object[] {annee, mois, codeRegion});
	}

}
