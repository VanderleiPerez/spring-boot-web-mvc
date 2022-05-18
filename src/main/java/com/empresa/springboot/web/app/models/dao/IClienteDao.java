package com.empresa.springboot.web.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.empresa.springboot.web.app.models.entity.Cliente;

//CrudRepository<ClaseEntity, id>
//Sin anotación, pero se logra inyectar en ClienteServiceImp, por ser una interfaz especial
//Ya es un componente especial
public interface IClienteDao extends CrudRepository<Cliente, Long> {
	
}
