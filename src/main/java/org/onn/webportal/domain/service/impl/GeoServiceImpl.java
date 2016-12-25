package org.onn.webportal.domain.service.impl;

import org.json.simple.JSONObject;
import org.onn.webportal.domain.service.GeoService;
import org.onn.webportal.infra.repository.GeoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoServiceImpl implements GeoService {

	@Autowired
	private GeoRepo geoRepo;

	public String getGeoRegionByCode(String codeRegion) {
		return geoRepo.getGeoRegionByCode(codeRegion);
	}
	
	@SuppressWarnings("unchecked")
	public String getGeoCommuneAndFktByCode(String codeCommune) {
		JSONObject obj = new JSONObject();
		String limCommune = geoRepo.getGeoCommuneByCode(codeCommune);
		String listeFkt = geoRepo.getGeoFktByCommuneCode(codeCommune);
		obj.put("limCommune", limCommune);
		obj.put("listeFkt", listeFkt);
		return obj.toJSONString();
	}

}
