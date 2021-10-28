package com.notas.core.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.notas.core.converter.Convertidor;
import com.notas.core.entity.Nota;
import com.notas.core.model.MNota;
import com.notas.core.repository.NotaRepositorio;

@Service("servicio")
public class NotaService {

	@Autowired
	@Qualifier("repositorio")
	private NotaRepositorio repositorio;
	
	@Autowired
	@Qualifier("convertidor")
	private Convertidor convertidor;
	
	//Creacion de un log
	private static final Log logger = LogFactory.getLog(NotaService.class);
	
	//Metodo para crear un registro
	public boolean crear(Nota nota)
	{
		logger.info("CREANDO NOTA");
		try{
			repositorio.save(nota);
			logger.info("NOTA CREADA");
			return true;
		}catch(Exception e){
			logger.error("ERROR EN LA CREACION DE LA NOTA");
			return false;
		}
		
	}
	
	//Metodo para actualizar un registro
	public boolean actualizar(Nota nota)
	{
		logger.info("ACTUALIZANDO NOTA");
		try{
			repositorio.save(nota);
			logger.info("NOTA ACTUALIZADA");
			return true;
		}catch(Exception e){
			logger.info("ERROR EN LA ACTUALIZACION");
			return false;
		}
		
	}
	
	//Metodo para eliminar un registro con base en un id y su nombre
	public boolean borrar(String nombre, long id)
	{
		logger.warn("ELIMINANDO NOTA");
		try{
			Nota nota = repositorio.findByNombreAndId(nombre, id);
			repositorio.delete(nota);
			logger.info("NOTA ELIMINADA");
			return true;
		}catch(Exception e){
			logger.info("ERROR DE ELIMINACION");
			return false;
		}
		
	}
	
	//Metodo para devolver todas las notas
	public List<MNota> obtener()
	{
		logger.info("OBTENIENDO TODOS LOS ELEMENTOS");
		return convertidor.convertirLista(repositorio.findAll());
	}
	
	
	//Metodo para devolver una lista por 'nombre'
	public MNota obtenerNombre(String nombre)
	{
		logger.info("OBTENIENDO LISTA DE NOTAS POR NOMBRE");
		return new MNota(repositorio.findByNombre(nombre));
	}
	
	//Metodo para devolver una lista de notas por 'titulo'
	public List<MNota> obtenerTitulo(String titulo)
	{
		logger.info("OBTENIENDO LISTA DE NOTAS POR TITULO");
		return convertidor.convertirLista(repositorio.findByTitulo(titulo));
	}
	
	
	//Metodo para devolver un registro con base en 'nombre' y 'titulo'
	public MNota obtenerPorNombreTitulo(String nombre, String titulo)
	{
		logger.info("OBTENIENDO LISTA DE NOTAS POR NOMBRE Y TITULO");
		return new MNota(repositorio.findByNombreAndTitulo(nombre, titulo));
	}
	
	//Metodo para devolver una lista por 'nombre' e 'id'
	public MNota obtenerNombreId(String nombre, long id)
	{
		logger.info("OBTENIENDO LISTA DE NOTAS POR NOMBRE E ID");
		return new MNota(repositorio.findByNombreAndId(nombre, id));
	}
	

	
}
