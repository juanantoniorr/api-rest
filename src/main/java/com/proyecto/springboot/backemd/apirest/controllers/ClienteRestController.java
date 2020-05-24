package com.proyecto.springboot.backemd.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null; 
		//Map es la interfaz, hashmap es la implementacion
				Map<String,Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		}
		catch(DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta en base de datos");
			response.put("error", ex.getMessage() + " " + ex.getMostSpecificCause());
			//Se regresa un responseEntity de tipo hashmap con el mensaje
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		catch (NoSuchElementException e) {
			response.put("mensaje", "El cliente no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
		
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
