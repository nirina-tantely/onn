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
		float val;
		int mois;
		IndicateurONG indc;
		for(IndicateurONG metadata: indicateurONG){
			val = rs.getObject(metadata.getIdIndicateur())!=null?rs.getFloat(metadata.getIdIndicateur()):-1; //-1 si la valeur dans la base est nulle
			mois = rs.getInt("mois");
			indc = metadata.copy();
			indc.setValeur(val);
			indc.setMois(mois);
			result.add(indc);
		}
		return result;
	}

}
