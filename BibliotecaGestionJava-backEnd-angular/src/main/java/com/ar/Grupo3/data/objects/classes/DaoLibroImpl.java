package com.ar.Grupo3.data.objects.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoLibroIntf;
import com.ar.Grupo3.data.objects.repositorio.LibroRepositorio;
import com.ar.Grupo3.model.Libro;

@Service
public class DaoLibroImpl implements DaoLibroIntf, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private LibroRepositorio dao;

	@Override
	public Optional<?> selectPorId(Long id) {
		
		Optional<?> aux = null;
		try {
			aux = dao.findById(id);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		
		return aux;
	}

	@Override
	public Long contarTodos() {
		long cantidad = 0;
		
		try {
			cantidad = dao.count();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}		
		
		return cantidad;
	}

	@Override
	public void agregar(Libro p) {
		try {
			dao.save(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Libro p) {
		try {
			if(p.getIdLibro() == null) {
				throw new Exception("El objeto a persistir no tiene identificador");
			}
			dao.save(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void borrar(Libro p) {
		try {
			if (p.getIdLibro() == null) {
				throw new Exception("El Libro a eliminar no tiene identificador");
			}
			//Revisamos que el Libro exista
			Optional<Libro> aux = dao.findById(p.getIdLibro());
			p =  aux.get();
			dao.delete(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public List<Libro> SelectTodos() {
		List<Libro> list = null;
		try {
			list = dao.findAll();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return list;
	}

}
