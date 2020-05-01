package com.proyecto.springboot.backemd.apirest.models.entity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.springboot.backemd.apirest.models.entity.Cliente;
import com.proyecto.springboot.backemd.apirest.models.entity.dao.IClienteDao;

@Service
public class IClienteServiceImpl implements IClienteService {
	//IMPORTANTE SE INYECTA LA INTERFAZ Y SPRING EN AUTOMATICO BUSCA LA IMPLEMENTACION
	//SI HUBIERA MAS DE UNA IMPLEMENTACION HAY QUE USAR UN QUALIFIER EN AUTOWIRED
	@Autowired
	private IClienteDao clienteDao;

	@Override
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
		
	}

	@Override
	public Cliente findById(Long id) {
		
		return clienteDao.findById(id).get(); //Aqui te da varios metodos que retornan diferentes opciones 
		
	}

	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
		
	}

	
	

}
