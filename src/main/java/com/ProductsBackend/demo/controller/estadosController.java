package com.ProductsBackend.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ProductsBackend.demo.dao.daoEstados;
import com.ProductsBackend.demo.entity.Estado;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class estadosController {

	@Autowired
	daoEstados daoEstado;
	
	@GetMapping("estados")
	public List<Estado> listaEstado(){
		return daoEstado.findAll();
	}
}
