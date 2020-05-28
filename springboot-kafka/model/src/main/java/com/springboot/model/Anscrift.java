package com.springboot.model;

import java.io.Serializable;

public class Anscrift implements Serializable {

	private static final long serialVersionUID = 1L;


	private String email;
	private String strasse;
	private String housenummer;
	private String stadt;
	private String plz;


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHousenummer() {
		return housenummer;
	}

	public void setHousenummer(String housenummer) {
		this.housenummer = housenummer;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	@Override
	public String toString() {
		return "Anscrift {" +
				"email='" + email + '\'' +
				", strasse='" + strasse + '\'' +
				", housenummer='" + housenummer + '\'' +
				", stadt='" + stadt + '\'' +
				", plz='" + plz + '\'' +
				'}';
	}
}
