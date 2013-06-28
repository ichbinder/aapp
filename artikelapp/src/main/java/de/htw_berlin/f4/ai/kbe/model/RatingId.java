package de.htw_berlin.f4.ai.kbe.model;

import java.io.Serializable;

import javax.persistence.Embeddable;



 

@Embeddable
public class RatingId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long artikelId;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artikelId == null) ? 0 : artikelId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatingId other = (RatingId) obj;
		if (artikelId == null) {
			if (other.artikelId != null)
				return false;
		} else if (!artikelId.equals(other.artikelId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	private Long userId;

	 
	
}