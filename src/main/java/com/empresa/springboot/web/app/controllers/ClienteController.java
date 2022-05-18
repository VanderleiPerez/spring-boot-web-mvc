package com.empresa.springboot.web.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.empresa.springboot.web.app.models.dao.IClienteDao;
import com.empresa.springboot.web.app.models.entity.Cliente;
import com.empresa.springboot.web.app.models.service.IClienteService;
//Marcar clase como controlador
@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	
	//Buscar bean con la siguiente interface para inyectarlo
	@Autowired
	@Qualifier("clienteServiceJPA") //Seleccionar bean concreto
	private IClienteService clienteService;
	
	/*---------------------- LISTAR CLIENTE ----------------------*/
	/*GET*/
	@RequestMapping(value="/listar",method = RequestMethod.GET) //Por defecto es GET
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());

		return "listar";
	}
	
	/*---------------------- AGREGAR CLIENTE ----------------------*/
	//crear la instancia de un nuevo OBJETO CLIENTE, luego sepasa vistar
	//Al crear cliente, se tiene que eliminar sesión
	/*GET*/
	@RequestMapping(value="/form", method = RequestMethod.GET)
	public String crear(Map<String, Object> model) { //Se puede pasar model o map
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo","Formulario de Cliente");
		return "form";
	}
	/*POST*/
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		//Manejo de errores (Argumentos juntos: @Valid -> @BindingResult)
		if(result.hasErrors()) {
			//cliente se pasa de forma automática, durante el fallo (mismo nombre del TIPO, sin minus) 
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	/*---------------------- EDITAR CLIENTE ----------------------*/
	//Buscar objeto en la BD, luego mostrar en el formulario
	@RequestMapping(value="/form/{id}")
	//@PathVariable: Inyectar valor del parametro de la ruta
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Cliente cliente=null; 
		if(id >0) {
			cliente =clienteService.finOne(id);
		}else {
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar cliente");
		return "form";
	}
	
	/*---------------------- ELIMINAR CLIENTE ----------------------*/
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			clienteService.delete(id);
		}
		return "redirect:/listar";
	}

}
