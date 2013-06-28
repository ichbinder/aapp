package de.htw_berlin.f4.ai.kbe.traditionalrepository;

import java.util.List;

import de.htw_berlin.f4.ai.kbe.model.User;

public interface UserRepository {

	public User findOne( Long id );
	   
	public List<User> findAll();
	 
	public void save(User entity);
	 
	public User update(User entity);
	 	 
	public void delete(User entity);

	public void deleteById(Long entityId);
		
}
