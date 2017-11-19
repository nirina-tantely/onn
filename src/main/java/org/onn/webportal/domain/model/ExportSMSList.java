package org.onn.webportal.domain.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExportSMSList {

	private List<ExportSMS> exportSMS = null;
	
	private String legende;
	
	private String rootPath;


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

	public List<ExportSMS> getExportSMS() {
		return exportSMS;
	}

	@XmlElement
	public void setExportSMS(List<ExportSMS> exportSMS) {
		this.exportSMS = exportSMS;
	}

}