package org.onn.webportal.infra.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.onn.webportal.domain.model.ActiviteMetadata;
import org.onn.webportal.domain.model.Synthese;
import org.springframework.jdbc.core.RowMapper;

public class SyntheseRowMapper implements RowMapper<List<Synthese>> {

	List<ActiviteMetadata> activiteMedata;
	public SyntheseRowMapper(List<ActiviteMetadata> metadata) {
		//super();
		this.activiteMedata = metadata;
	}

	public List<Synthese> mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<Synthese> result = new ArrayList<Synthese>();
		int val;
		Synthese syn;
		for(ActiviteMetadata metadata: activiteMedata){
			//val O ou 1
			val  = rs.getInt(metadata.getIdActivite());
			syn = metadata.getSythese().copy();
			syn.setValeur(val);
			result.add(syn);
		}
		return result;
	}

}
