package com.proyecto.springboot.backemd.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.backemd.apirest.models.entity.Cliente;
import com.proyecto.springboot.backemd.apirest.models.entity.services.IClienteService;

@CrossOrigin(origins = {"http://localhost:4200"} )
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping ("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
		
		
	}
	@GetMapping ("/clientes/{id}")
	public Cliente show(@PathVariable Long id) {
		
		return clienteService.findById(id);
	}
	
	@PostMapping("clientes")
	@ResponseStatus (HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) { //Nos envian el cliente por json por eso es el request body
		return clienteService.save(cliente);
		
	}
	
	@PutMapping("clientes/{id}")
	public Cliente update(@RequestBody Cliente cliente,@PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setEmail(cliente.getEmail());
		return clienteService.save(clienteActual);
		
	}
	@DeleteMapping("clientes/{id}")
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
		
	}
	
	
}
