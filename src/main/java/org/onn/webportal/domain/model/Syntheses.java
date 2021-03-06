package org.onn.webportal.domain.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Syntheses {

	private List<Synthese> syntheses = null;
	private String legende;
	private String rootPath;
	public List<Synthese> getSyntheses() {
		return syntheses;
	}

	@XmlElement(name = "synthese")
	public void setSyntheses(List<Synthese> syntheses) {
		this.syntheses = syntheses;
	}

	public String getLegende() {
		return legende;
	}

	@XmlElement(name = "legende")
	public void setLegende(String legende) {
		this.legende = legende;
	}

	public String getRootPath() {
		return rootPath;
	}

	@XmlElement(name = "rootpath")
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

}