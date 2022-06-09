package com.jamar.apc.persistence.generics;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractRepository<K, E> {

	@PersistenceContext(unitName = "DataCredito")
	private EntityManager em;

	public AbstractRepository(Class<E> e) {
		entityClass = e;
	}

	private Class<E> entityClass;

	public E save(E entity) throws com.jamar.exception.PersistenceException {
//		try {
			em.persist(entity);
			em.flush();

//		} catch (PersistenceException e) {
//			throw new com.jamar.exception.PersistenceException("Error guardando el informe ", e);
//		}catch(Exception e){
//			throw new com.jamar.exception.PersistenceException("Error guardando el informe ", e);
//		}
		return entity;
	}

	public void delete(K key) {
		em.remove(em.find(entityClass, key));
	}

	public E update(E entity) throws com.jamar.exception.PersistenceException {
		try {
			em.merge(entity);
		} catch (PersistenceException e) {
			throw new com.jamar.exception.PersistenceException("Error actualizando el informe ", e);
		}
		return entity;
	}

	public E find(K key) {
		try {
			return em.find(entityClass, key);
		} catch (Exception e) {
			return null;
		}
	}

}
