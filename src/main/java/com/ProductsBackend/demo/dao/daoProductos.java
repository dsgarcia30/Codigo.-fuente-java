package com.ProductsBackend.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ProductsBackend.demo.entity.Producto;

@Repository
public class daoProductos {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public List<Producto> findAll(){
		List<Producto> lista = new ArrayList<>();
		Query q = em.createQuery("SELECT p FROM Producto p", Producto.class);
		lista = q.getResultList();
		return lista;
	}
	
	@Transactional
	public void registerProduct(Producto producto) {
		Query q = em.createNativeQuery("INSERT INTO producto (id, nombre, idestado, fecha, img, idcategoria) "
				+ "VALUES (?, ?, ?, ?, ?, ?)", Producto.class);
		q.setParameter(1, producto.getId());
		q.setParameter(2, producto.getNombre());
		q.setParameter(3, producto.getEstado().getId());
		q.setParameter(4, producto.getFecha());
		q.setParameter(5, producto.getImg());
		q.setParameter(6, producto.getCategoria().getId());
		q.executeUpdate();
	}
}
