package org.onn.webportal.domain.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExportONGBase {


	private String indicateur;
	private String t1;
	private String t2;
	private String t3;
	private String t4;


	public String getIndicateur() {
		return indicateur;
	}
	public String getT1() {
		return t1;
	}
	public String getT2() {
		return t2;
	}
	public String getT3() {
		return t3;
	}
	public String getT4() {
		return t4;
	}

	@XmlElement
	public void setIndicateur(String indicateur) {
		this.indicateur = indicateur;
	}

	@XmlElement
	public void setT1(String t1) {
		if(t1 == null || t1.equals("")){
			this.t1 = " ";
			return;
		}
		this.t1 = t1;
	}

	@XmlElement
	public void setT2(String t2) {
		if(t2 == null || t2.equals("")){
			this.t2 = " ";
			return;
		}
		this.t2 = t2;
	}

	@XmlElement
	public void setT3(String t3) {
		if(t3 == null || t3.equals("")){
			this.t3 = " ";
			return;
		}
		this.t3 = t3;
	}

	@XmlElement
	public void setT4(String t4) {
		if(t4 == null || t4.equals("")){
			this.t4 = " ";
			return;
		}
		this.t4 = t4;
	}

}