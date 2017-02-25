package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.onn.webportal.domain.model.IndicateurONG;
import org.springframework.jdbc.core.RowMapper;

public class ONGBaseSyntheseRowMapper implements RowMapper<List<IndicateurONG>> {

	List<IndicateurONG> indicateurONG;
	public ONGBaseSyntheseRowMapper(List<IndicateurONG> metadata) {
		//super();
		this.indicateurONG = metadata;
	}

	public List<IndicateurONG> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<IndicateurONG> result = new ArrayList<IndicateurONG>();
		int val;
		IndicateurONG indc;
		for(IndicateurONG metadata: indicateurONG){
			val  = rs.getInt(metadata.getIdIndicateur());
			indc = metadata.copy();
			indc.setValeur(val);
			result.add(indc);
		}
		return result;
	}

}
