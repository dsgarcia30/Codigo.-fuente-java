package com.ProductsBackend.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ProductsBackend.demo.entity.Categoria;


@Repository
public class daoCategorias {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Categoria> findAll(){
		List<Categoria> lista = new ArrayList<>();
		Query q = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
		lista = q.getResultList();
		return lista;
	}
}
