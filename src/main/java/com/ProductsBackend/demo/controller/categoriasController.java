package com.ProductsBackend.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ProductsBackend.demo.dao.daoCategorias;
import com.ProductsBackend.demo.entity.Categoria;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class categoriasController {
	
	@Autowired
	daoCategorias datCategoria;
	
	@GetMapping("categorias")
	public List<Categoria> listaCategoria(){
		return datCategoria.findAll();
	}

}
