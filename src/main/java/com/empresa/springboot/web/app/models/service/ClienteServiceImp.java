package com.empresa.springboot.web.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.springboot.web.app.models.dao.IClienteDao;
import com.empresa.springboot.web.app.models.entity.Cliente;
//Patrón de diseño FACADE: Unico punto de acceso, hacia distintos DAO o Repository 
//Por cada método en clase DAO -> Método en SERVICE
//SERVICE: Manejar la transacción sin implementar anotación TRANSACTIONAL en DAO
//@Service es un Component
@Service("clienteServiceJPA")
public class ClienteServiceImp implements IClienteService {
	
	//PATRÓN FACADE: Inyectar CLIENTE DAO
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll(); //CrudRepository  (extends)
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);  //CrudRepository  (extends)
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente finOne(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null); //CrudRepository (extends)
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id); //CrudRepository
		
	}
	//Mediante un método se puede interactuar con diferentes DAO, en una misma transacción
}
