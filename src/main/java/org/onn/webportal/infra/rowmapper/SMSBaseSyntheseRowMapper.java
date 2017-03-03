package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.onn.webportal.domain.model.IndicateurSMS;
import org.springframework.jdbc.core.RowMapper;

public class SMSBaseSyntheseRowMapper implements RowMapper<List<IndicateurSMS>> {

	List<IndicateurSMS> indicateurSMS;
	public SMSBaseSyntheseRowMapper(List<IndicateurSMS> metadata) {
		//super();
		this.indicateurSMS = metadata;
	}

	public List<IndicateurSMS> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<IndicateurSMS> result = new ArrayList<IndicateurSMS>();
		int val;
		IndicateurSMS indc;
		int mois = rs.getInt("mois");
		for(IndicateurSMS metadata: indicateurSMS){
			val  = rs.getInt(metadata.getIdIndicateur());
			indc = metadata.copy();
			indc.setValeur(val);
			result.add(indc);
			indc.setMois(mois);
		}
		return result;
	}

}
