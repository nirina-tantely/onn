package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.onn.webportal.api.enumeration.TypeLocalisation;
import org.onn.webportal.domain.model.Localisation;
import org.springframework.jdbc.core.RowMapper;

public class LocalisationRowMapper implements RowMapper<Localisation> {
	
	private TypeLocalisation typeLoc = null;
	
	
	public LocalisationRowMapper(TypeLocalisation typeLoc) {
		//super();
		this.typeLoc = typeLoc;
	}
	
	public LocalisationRowMapper() {
		
	}

	public Localisation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Localisation result = new Localisation();
		result.setType(typeLoc);
		if(typeLoc==null || typeLoc.equals(TypeLocalisation.NATIONALE)){
			result.setIdRegion(rs.getString("c_reg"));
			result.setNomRegion(rs.getString("nom_region"));
			result.setIdRegion(rs.getString("c_com"));
			result.setNomRegion(rs.getString("nom_commun"));
			result.setIdRegion(rs.getString("c_lc"));
			result.setNomRegion(rs.getString("nom_loca"));
		}else{
			switch(typeLoc){
				case REGION:
					result.setIdRegion(rs.getString("c_reg"));
					result.setNomRegion(rs.getString("nom_region"));
					break;
				case COMMUNE:
					result.setIdCommune(rs.getString("c_com"));
					result.setNomcommune(rs.getString("nom_commun"));
					break;
				case FOKONTANY:
					result.setIdFokontany(rs.getString("c_lc"));
					result.setNomFokontany(rs.getString("nom_loca"));
					break;
				}
		}
		return result;
	}

}
