package com.empresa.springboot.web.app.models.service;

import java.util.List;

import com.empresa.springboot.web.app.models.entity.Cliente;

public interface IClienteService {
	/*---------------------- LISTAR CLIENTES ----------------------*/
	public List<Cliente> findAll();
	/*---------------------- AGREGAR CLIENTE ----------------------*/
	public void save(Cliente cliente);
	/*---------------------- EDITAR CLIENTE ----------------------*/
	public Cliente finOne(Long id);
	/*---------------------- ELIMINAR CLIENTE ----------------------*/
	public void delete(Long id);
}
