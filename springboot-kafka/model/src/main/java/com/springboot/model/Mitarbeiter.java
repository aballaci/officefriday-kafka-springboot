package com.springboot.model;

import java.io.Serializable;

public class Mitarbeiter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String name;
	private String nachname;
	private String alter;
	private Anscrift anscrift;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getAlter() {
		return alter;
	}

	public void setAlter(String alter) {
		this.alter = alter;
	}

	public Anscrift getAnscrift() {
		return anscrift;
	}

	public void setAnscrift(Anscrift anscrift) {
		this.anscrift = anscrift;
	}


	@Override
	public String toString() {
		return "Mitarbeiter {" +
				"userId='" + userId + '\'' +
				", name='" + name + '\'' +
				", nachname='" + nachname + '\'' +
				", alter='" + alter + '\'' +
				", anscrift=" + anscrift +
				'}';
	}
}
