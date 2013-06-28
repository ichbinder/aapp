package de.htw_berlin.f4.ai.kbe.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Artikel {

	public Artikel(){
	}
	
	public Artikel(String titel, String inhalt, String zusammenfassung){
		this.titel = titel;
		this.inhalt = inhalt;
		this.zusammenfassung = zusammenfassung;
	}
	
	@Id 
	@GeneratedValue 
	@Column(updatable=false)
	Long id;
	
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getZusammenfassung() {
		return zusammenfassung;
	}

	public void setZusammenfassung(String zusammenfassung) {
		this.zusammenfassung = zusammenfassung;
	}

	public Set<Kategorie> getKategorien() {
		return kategorien;
	}

	public void setKategorien(Set<Kategorie> kategorien) {
		this.kategorien = kategorien;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String inhalt;
	
	private String titel;
	
	private String zusammenfassung;
	
	@ManyToMany
	private Set<Kategorie> kategorien;
	
	@ManyToOne
	//@Column(nullable=false)
	private User user;
	
}
