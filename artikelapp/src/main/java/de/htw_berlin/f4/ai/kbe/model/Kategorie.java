package de.htw_berlin.f4.ai.kbe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Kategorie {

	
	@Id 
	@GeneratedValue 
	@Column(updatable=false)
	Long id;
	
	@Column(unique=true)
	private String name;
	
	
	private String beschreibung;


	public String getBeschreibung() {
		return beschreibung;
	}


	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
}
