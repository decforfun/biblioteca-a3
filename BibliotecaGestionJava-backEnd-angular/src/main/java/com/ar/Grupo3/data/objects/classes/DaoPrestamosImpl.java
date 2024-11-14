package com.ar.Grupo3.data.objects.classes;

import java.io.Serializable;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoPrestamosIntf;
import com.ar.Grupo3.data.objects.repositorio.LibroRepositorio;
import com.ar.Grupo3.data.objects.repositorio.PrestamosRepositorio;
import com.ar.Grupo3.data.objects.repositorio.UsuarioRepositorio;
import com.ar.Grupo3.model.Libro;
import com.ar.Grupo3.model.Prestamos;
import com.ar.Grupo3.model.Usuario;

@Service
public class DaoPrestamosImpl implements DaoPrestamosIntf, Serializable {

	private static final long serialVersionUID = 3374994688842723597L;

	@Autowired
	private PrestamosRepositorio dao;

	@Autowired
	private LibroRepositorio libro;

	@Autowired
	private UsuarioRepositorio usuario;

	@Override
	public Optional<?> selectPorId(Long id) {

		Optional<?> aux = null;
		try {
			aux = dao.findById(id);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return aux;
	}

	@Override
	public Long contarTodos() {
		long cantidad = 0;

		try {
			cantidad = dao.count();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return cantidad;
	}

	@Override
	public void agregar(Prestamos p) {
		try {
			if (p.getIdLibro() == null) {
				throw new Exception("El Prestamo para agregar no tiene identificador de Libro");
			}
			if (p.getIdUsuario() == null) {
				throw new Exception("El Prestamo para agregar no tiene identificador de Usuario");
			}
			
			//SI vos Andy no queres cargar las fechas que se haga como dice la consigna
			if(p.getDia_prestamo() == null || p.getDia_devolucion_estimativo() == null) {
				// Manejo de Fechas impuesto en consigna.
				LocalDate hoy = LocalDate.now();
				LocalDate entrega = LocalDate.now().plusDays(15L);
				Date fechaInicio = Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date fechaEntrega = Date.from(entrega.atStartOfDay(ZoneId.systemDefault()).toInstant());

				// Fecha de 15 dias como se solicita en consigna
				p.setDia_prestamo(fechaInicio);
				p.setDia_devolucion_estimativo(fechaEntrega);
			}		

			// Revisamos que el usuario exista
			Optional<Usuario> usBusqueda = usuario.findById(p.getIdUsuario());
			if (usBusqueda.get().getIdUsuario().equals(p.getIdUsuario())) {
				// Revisamos que el Libro exista
				Optional<Libro> liBusqueda = libro.findById(p.getIdLibro());
				if (liBusqueda.get().getIdLibro().equals(p.getIdLibro())) {
					dao.save(p);
				} else {
					throw new Exception("El Prestamo que va a registrar no tiene Libro registrado");
				}
			} else {
				throw new Exception("El Prestamo que va a registrar no tiene Usuario registrado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Prestamos p) {
		try {
			if (p.getIdPrestamo() == null) {
				throw new Exception("El Prestamo modificar no tiene identificador de Prestamo");
			}
			if (p.getIdLibro() == null) {
				throw new Exception("El Prestamo modificar no tiene identificador de Libro");
			}
			if (p.getIdUsuario() == null) {
				throw new Exception("El Prestamo modificar no tiene identificador de Usuario");
			}
			// Revisamos que el usuario exista
			Optional<Usuario> usBusqueda = usuario.findById(p.getIdUsuario());
			if (usBusqueda.get().getIdUsuario().equals(p.getIdUsuario())) {
				// Revisamos que el Libro exista
				Optional<Libro> liBusqueda = libro.findById(p.getIdLibro());
				if (liBusqueda.get().getIdLibro().equals(p.getIdLibro())) {
					dao.save(p);
				} else {
					throw new Exception("El Prestamo que va a registrar no tiene Libro registrado");
				}
			} else {
				throw new Exception("El Prestamo que va a registrar no tiene Usuario registrado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void borrar(Prestamos p) {
		try {
			if (p.getIdPrestamo() == null) {
				throw new Exception("El Prestamo a eliminar no tiene identificador");
			}
			// Revisamos que el Prestamo exista
			Optional<Prestamos> aux = dao.findById(p.getIdPrestamo());
			p = aux.get();
			dao.delete(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public List<Prestamos> SelectTodos() {
		List<Prestamos> list = null;
		try {
			list = dao.findAll();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return list;
	}

}
