package org.onn.webportal.domain.api.dto;

import org.codehaus.jackson.map.ObjectMapper;

public class OnnUtils {

	private static ObjectMapper mapper;

	public static ObjectMapper getJsonMapper(){
		if(mapper == null) mapper = new ObjectMapper();
		return mapper;
	}

}
