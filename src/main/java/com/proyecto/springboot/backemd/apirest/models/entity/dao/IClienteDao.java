package com.proyecto.springboot.backemd.apirest.models.entity.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.springboot.backemd.apirest.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
