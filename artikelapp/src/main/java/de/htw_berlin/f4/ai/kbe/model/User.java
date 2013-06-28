package de.htw_berlin.f4.ai.kbe.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
 
import javax.persistence.Table;


@Entity 
@Table(name="USER_TABLE")
public class User implements Serializable{

	public User(){
	}
	public User(String name, String vorname, String nickname, String email){
		this.name = name;
		this.vorname = vorname;
		this.nickname = nickname;
		this.email = email;
		
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Long getId() {
		return id;
	}

	private static final long serialVersionUID = 4478854072137854541L;

	@Id 
	@GeneratedValue 
	@Column(updatable=false)
	Long id;
		
	private String email;
	
	private String name;
	
	private String vorname;
	
	private String nickname;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Artikel> artikel;

	public Set<Artikel> getArtikel() {
		return artikel;
	}

	public void setArtikel(Set<Artikel> artikel) {
		this.artikel = artikel;
	}

	
}
