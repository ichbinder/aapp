package de.htw_berlin.f4.ai.kbe.traditionalrepository;

import org.springframework.stereotype.Repository;

import de.htw_berlin.f4.ai.kbe.model.User;



@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
 
   public UserRepositoryImpl(){
      setClazz(User.class );
   }
}
