package org.onn.webportal.domain.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExportSMS {


	private String indicateur;
	private String m1;
	private String m2;
	private String m3;
	private String m4;
	private String m5;
	private String m6;
	private String m7;
	private String m8;
	private String m9;
	private String m10;
	private String m11;
	private String m12;


	public String getIndicateur() {
		return indicateur;
	}

	@XmlElement
	public void setIndicateur(String indicateur) {
		this.indicateur = indicateur;
	}

	public String getM1() {
		return m1;
	}

	@XmlElement
	public void setM1(String m1) {
		if(m1 == null || m1.equals("")){
			this.m1 = " ";
			return;
		}
		this.m1 = m1;
	}

	public String getM2() {
		return m2;
	}

	@XmlElement
	public void setM2(String m2) {
		if(m2 == null || m2.equals("")){
			this.m2 = " ";
			return;
		}
		this.m2 = m2;
	}

	public String getM3() {
		return m3;
	}

	@XmlElement
	public void setM3(String m3) {
		if(m3 == null || m3.equals("")){
			this.m3 = " ";
			return;
		}
		this.m3 = m3;
	}

	public String getM4() {
		return m4;
	}

	@XmlElement
	public void setM4(String m4) {
		if(m4 == null || m4.equals("")){
			this.m4 = " ";
			return;
		}
		this.m4 = m4;
	}

	public String getM5() {
		return m5;
	}

	@XmlElement
	public void setM5(String m5) {
		if(m5 == null || m5.equals("")){
			this.m5 = " ";
			return;
		}
		this.m5 = m5;
	}

	public String getM6() {
		return m6;
	}

	@XmlElement
	public void setM6(String m6) {
		if(m6 == null || m6.equals("")){
			this.m6 = " ";
			return;
		}
		this.m6 = m6;
	}

	public String getM7() {
		return m7;
	}

	@XmlElement
	public void setM7(String m7) {
		if(m7 == null || m7.equals("")){
			this.m7 = " ";
			return;
		}
		this.m7 = m7;
	}

	public String getM8() {
		return m8;
	}

	@XmlElement
	public void setM8(String m8) {
		if(m8 == null || m8.equals("")){
			this.m8 = " ";
			return;
		}
		this.m8 = m8;
	}

	public String getM9() {
		return m9;
	}

	@XmlElement
	public void setM9(String m9) {
		if(m9 == null || m9.equals("")){
			this.m9 = " ";
			return;
		}
		this.m9 = m9;
	}

	public String getM10() {
		return m10;
	}

	@XmlElement
	public void setM10(String m10) {
		if(m10 == null || m10.equals("")){
			this.m10 = " ";
			return;
		}
		this.m10 = m10;
	}

	public String getM11() {
		return m11;
	}

	@XmlElement
	public void setM11(String m11) {
		if(m11 == null || m11.equals("")){
			this.m11 = " ";
			return;
		}
		this.m11 = m11;
	}

	public String getM12() {
		return m12;
	}

	@XmlElement
	public void setM12(String m12) {
		if(m12 == null || m12.equals("")){
			this.m12 = " ";
			return;
		}
		this.m12 = m12;
	}
}