package de.htw_berlin.f4.ai.kbe.springdatarepository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.htw_berlin.f4.ai.kbe.model.Artikel;

public interface ArtikelRepository extends JpaRepository<Artikel, Long>{
	
	
	
}
