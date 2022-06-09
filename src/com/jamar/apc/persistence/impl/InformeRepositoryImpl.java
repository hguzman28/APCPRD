package com.jamar.apc.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jamar.apc.persistence.entities.Cenclientesconsultar;
import com.jamar.apc.persistence.generics.AbstractRepository;
import com.jamar.apc.persistence.port.InformeRepository;


@Repository
@Transactional
public class InformeRepositoryImpl  extends AbstractRepository<Long,Cenclientesconsultar> implements InformeRepository {

	
	public InformeRepositoryImpl() {
		super(Cenclientesconsultar.class);
	}

	@PersistenceContext(unitName = "DataCredito")
	private EntityManager em;
	
	
}
