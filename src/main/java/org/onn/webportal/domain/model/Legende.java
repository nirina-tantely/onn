package org.onn.webportal.domain.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Legende {

	private String text;


	@XmlElement(name = "text")
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

}