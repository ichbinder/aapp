package de.htw_berlin.f4.ai.kbe.traditionalrepository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

public abstract class AbstractRepository<T extends Serializable> {

	private Class<T> clazz;

	@PersistenceContext
	EntityManager entityManager;

	private static final Logger logger = Logger.getLogger(AbstractRepository.class); 
	
	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(Long id) {
		
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		logger.debug("example log4j message: findAll called!");
		return entityManager.createQuery("from " + clazz.getName())
				.getResultList();
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(Long entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}