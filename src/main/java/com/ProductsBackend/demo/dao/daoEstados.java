package com.ProductsBackend.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ProductsBackend.demo.entity.Estado;
import com.ProductsBackend.demo.entity.Producto;

@Repository
public class daoEstados {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Estado> findAll(){
		List<Estado> lista = new ArrayList<>();
		Query q = em.createQuery("SELECT e FROM Estado e", Estado.class);
		lista = q.getResultList();
		return lista;
	}
}
