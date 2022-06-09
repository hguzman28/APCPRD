package com.jamar.apc.persistence.generics;

import java.util.List;

public interface IGenericRepository<K, E> {

	public E save(E entity)throws com.jamar.exception.PersistenceException;

	public void delete(K key);

	public E update(E entity)throws com.jamar.exception.PersistenceException;
	
	public E find (K key);

}
