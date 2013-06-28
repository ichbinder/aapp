package de.htw_berlin.f4.ai.kbe.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity; 
 
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Rating {

	@EmbeddedId
	private RatingId ratingId;
	
	@Version
	private long version;
	
	@Min(1)
	@Max(6)
	private int stars;
	
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@ManyToOne
	@PrimaryKeyJoinColumn(name="USERID", referencedColumnName="ID")
	User user;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="ARTIKELID", referencedColumnName="ID")
	Artikel artikel;

	
}
