package com.ar.Grupo3.data.objects.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoDevolucionIntf;
import com.ar.Grupo3.data.objects.repositorio.DevolucionRepositorio;
import com.ar.Grupo3.data.objects.repositorio.LibroRepositorio;
import com.ar.Grupo3.data.objects.repositorio.PrestamosRepositorio;
import com.ar.Grupo3.data.objects.repositorio.UsuarioRepositorio;
import com.ar.Grupo3.model.Devolucion;
import com.ar.Grupo3.model.Libro;
import com.ar.Grupo3.model.Prestamos;
import com.ar.Grupo3.model.Usuario;

@Service
public class DaoDevolucionImpl implements Serializable, DaoDevolucionIntf {

	private static final long serialVersionUID = -1371128702457337684L;
	
	@Autowired
	private DevolucionRepositorio dao;
	
	@Autowired
	private LibroRepositorio libro;
	
	@Autowired
	private UsuarioRepositorio usuario;
	
	@Autowired
	private PrestamosRepositorio prestamos;

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
	public void agregar(Devolucion p) {
		try {			
			if(p.getIdLibro() == null) {
				throw new Exception("La devolucion que va a registrar no tiene identificador de Libro");
			}
			if(p.getIdPrestamo() == null) {
				throw new Exception("La devolucion que va a registrar no tiene identificador de Prestamo");
			}
			if(p.getIdUsuario() == null) {
				throw new Exception("La devolucion que va a registrar no tiene identificador de Usuario");
			}
			//Revisamos que el usuario exista
			Optional<Usuario> usBusqueda = usuario.findById(p.getIdUsuario());	
			if(usBusqueda.get().getIdUsuario().equals(p.getIdUsuario())) {
				//Revisamos que el Libro exista
				Optional<Libro> liBusqueda = libro.findById(p.getIdLibro());
				if(liBusqueda.get().getIdLibro().equals(p.getIdLibro())) {
					//Revisamos que el Prestamo exista
					Optional<Prestamos> prBusqueda = prestamos.findById(p.getIdPrestamo());
					if(prBusqueda.get().getIdPrestamo().equals(p.getIdPrestamo())) {
						dao.save(p);
					}else {
						throw new Exception("La Devolucion que va a registrar no tiene identificador de Prestamo registrado");
					}					
				}else {
					throw new Exception("La Devolucion que va a registrar no tiene Libro registrado");
				}
			}else {
				throw new Exception("La Devolucion que va a registrar no tiene Usuario registrado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Devolucion p) {
		try {
			if(p.getIdDevolucion() == null) {
				throw new Exception("El objeto a persistir no tiene identificador");
			}
			if(p.getIdLibro() == null) {
				throw new Exception("La devolucion que va a registrar no tiene identificador de Libro");
			}
			if(p.getIdPrestamo() == null) {
				throw new Exception("La devolucion que va a registrar no tiene identificador de Prestamo");
			}
			if(p.getIdUsuario() == null) {
				throw new Exception("La devolucion que va a registrar no tiene identificador de Usuario");
			}
			//Revisamos que el usuario exista
			Optional<Usuario> usBusqueda = usuario.findById(p.getIdUsuario());	
			if(usBusqueda.get().getIdUsuario().equals(p.getIdUsuario())) {
				//Revisamos que el Libro exista
				Optional<Libro> liBusqueda = libro.findById(p.getIdLibro());
				if(liBusqueda.get().getIdLibro().equals(p.getIdLibro())) {
					//Revisamos que el Prestamo exista
					Optional<Prestamos> prBusqueda = prestamos.findById(p.getIdPrestamo());
					if(prBusqueda.get().getIdPrestamo().equals(p.getIdPrestamo())) {
						dao.save(p);
					}else {
						throw new Exception("La Devolucion que va a registrar no tiene identificador de Prestamo registrado");
					}					
				}else {
					throw new Exception("La Devolucion que va a registrar no tiene Libro registrado");
				}
			}else {
				throw new Exception("La Devolucion que va a registrar no tiene Usuario registrado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void borrar(Devolucion p) {
		try {
			if (p.getIdDevolucion() == null) {
				throw new Exception("La Devolucion a eliminar no tiene identificador");
			}
			//Revisamos que La Devolucion exista
			Optional<Devolucion> aux = dao.findById(p.getIdDevolucion());
			p =  aux.get();
			dao.delete(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}
	}

	@Override
	public List<Devolucion> SelectTodos() {
		List<Devolucion> list = null;
		try {
			list = dao.findAll();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return list;
	}

}
