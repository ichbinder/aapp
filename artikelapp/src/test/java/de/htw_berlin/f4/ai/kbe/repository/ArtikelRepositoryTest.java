package de.htw_berlin.f4.ai.kbe.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import de.htw_berlin.f4.ai.kbe.model.Artikel;
import de.htw_berlin.f4.ai.kbe.springdatarepository.ArtikelRepository;
 

/* jede Methode findet in einer Transaction statt*/ 
@Transactional
/* Spring Junit Runner - laed spring configuration */
@RunWith(SpringJUnit4ClassRunner.class)
/* Angabe welche Art von Configuration hier Java Annotationen
  und welche Klasse die Spring Java Konfiguration enthaelt*/
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
	classes = de.htw_berlin.f4.ai.config.PersistenceJpaTestConfig.class)
public class ArtikelRepositoryTest {
	 
	 
	@Autowired 
	ArtikelRepository artikelRepository;
	
	private Artikel artikel;
	
	@Before
	public void setUp(){
		artikel = new Artikel("Titel", "Ein Text ..", 
				"Eine Zusammenfassung");
		artikelRepository.save(artikel);
	}
	
	@After
	public void tearDown(){
		artikelRepository.delete(artikel);
	}
	
	@Test
	public void findUserTest(){
		List<Artikel> artikel = artikelRepository.findAll();
		assertEquals(1, artikel.size());
		assertEquals("Titel", artikel.get(0).getTitel());
	}	
		
	
	
}
