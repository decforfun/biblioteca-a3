package com.ar.Grupo3.data.objects.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoUsuarioIntf;
import com.ar.Grupo3.data.objects.repositorio.UsuarioRepositorio;
import com.ar.Grupo3.model.Usuario;

@Service
public class DaoUsuarioImpl implements DaoUsuarioIntf, Serializable {

	private static final long serialVersionUID = 3804112581323618271L;

	@Autowired
	private UsuarioRepositorio dao;

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
	public void agregar(Usuario p) {
		try {
			dao.save(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Usuario p) {
		try {
			if (p.getIdUsuario() == null) {
				throw new Exception("El objeto a persistir no tiene identificador");
			}
			dao.save(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void borrar(Usuario p) {
		try {
			if (p.getIdUsuario() == null) {
				throw new Exception("El Usuario a Eliminar no tiene identificador");
			}
			//Revisamos que el Usuario exista
			Optional<Usuario> aux = dao.findById(p.getIdUsuario());
			p =  aux.get();
			dao.delete(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public List<Usuario> SelectTodos() {
		List<Usuario> list = null;
		try {
			list = dao.findAll();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return list;
	}

}
