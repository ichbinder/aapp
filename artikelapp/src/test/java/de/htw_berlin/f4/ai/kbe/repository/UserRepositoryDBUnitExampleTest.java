package de.htw_berlin.f4.ai.kbe.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import de.htw_berlin.f4.ai.kbe.model.User;
import de.htw_berlin.f4.ai.kbe.traditionalrepository.UserRepository;


@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
	classes = de.htw_berlin.f4.ai.config.PersistenceJpaTestConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class }) 
public class UserRepositoryDBUnitExampleTest {
	 
	 
	@Autowired 
	UserRepository userRepository;
	
	@Test
	@ExpectedDatabase(assertionMode=DatabaseAssertionMode.NON_STRICT ,value="/dbUnit/user.xml")
	public void saveUserTest(){
		User user = new User("Meier", "Klaus", "klaus", "klaus.meier@htw-berlin.de");
		userRepository.save(user);
	}
	 
	
	@Test
	@DatabaseSetup("/dbUnit/userAndArtikel.xml")
	//zusaetzlich moeglich: 
	//@ExpectedDatabase(assertionMode=DatabaseAssertionMode.NON_STRICT ,value="/pathFromResources/dbEndState.xml")
	public void findUserTest(){
		List<User> users = userRepository.findAll();
		assertEquals(1, users.size());
		assertEquals("Meier", users.get(0).getName());
	}	
		
	
	
}
