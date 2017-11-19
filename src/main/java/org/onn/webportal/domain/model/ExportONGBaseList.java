package org.onn.webportal.domain.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExportONGBaseList {

	private List<ExportONGBase> exportONGBase = null;
	
	private String legende;
	
	private String rootPath;


	public List<ExportONGBase> getExportONGBase() {
		return exportONGBase;
	}

	@XmlElement
	public void setExportONGBase(List<ExportONGBase> exportONGBase) {
		this.exportONGBase = exportONGBase;
	}

	public String getLegende() {
		return legende;
	}

	@XmlElement
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