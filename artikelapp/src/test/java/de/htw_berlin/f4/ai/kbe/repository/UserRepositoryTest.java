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

import de.htw_berlin.f4.ai.kbe.model.User;
import de.htw_berlin.f4.ai.kbe.traditionalrepository.UserRepository;

/* jede Methode findet in einer Transaction statt*/ 
@Transactional
/* Spring Junit Runner - laed spring configuration */
@RunWith(SpringJUnit4ClassRunner.class)
/* Angabe welche Art von Configuration hier Java Annotationen
  und welche Klasse die Spring Java Konfiguration enthaelt*/
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
	classes = de.htw_berlin.f4.ai.config.PersistenceJpaTestConfig.class)
public class UserRepositoryTest {
	 
	 
	@Autowired 
	UserRepository userRepository;
	
	private User userKlaus;
	
	@Before
	public void setUp(){
		userKlaus = new User("Meier", "Klaus", "klaus", "klaus@user.de");
		userRepository.save(userKlaus);
	}
	
	@After
	public void tearDown(){
		userRepository.delete(userKlaus);
	}
	
	@Test
	public void findUserTest(){
		List<User> users = userRepository.findAll();
		assertEquals(1, users.size());
		assertEquals("Meier", users.get(0).getName());
	}	
		
	
	
}
