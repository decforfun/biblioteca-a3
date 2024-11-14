package com.ar.Grupo3.data.objects.classes;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoReportesInt;
import com.ar.Grupo3.data.objects.repositorio.DevolucionRepositorio;
import com.ar.Grupo3.data.objects.repositorio.LibroRepositorio;
import com.ar.Grupo3.data.objects.repositorio.PrestamosRepositorio;
import com.ar.Grupo3.data.objects.repositorio.ReporteRepositorio;
import com.ar.Grupo3.data.objects.repositorio.UsuarioRepositorio;
import com.ar.Grupo3.model.Devolucion;
import com.ar.Grupo3.model.Libro;
import com.ar.Grupo3.model.Prestamos;
import com.ar.Grupo3.model.Reporte;
import com.ar.Grupo3.model.Usuario;

@Service
public class DaoReporteImpl implements DaoReportesInt, Serializable {

	private static final long serialVersionUID = 3132062087653960830L;

	@Autowired
	private ReporteRepositorio dao;
	
	@Autowired
	private PrestamosRepositorio prestamos;
	
	@Autowired
	private DevolucionRepositorio devolucion;
	
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
	public void agregar(Reporte p) {
		try {
			if(p.getIdUsuario() == null) {
				throw new Exception("El reporte que va a registrar no tiene identificador de Usuario");
			}
			if(p.getIdLibro() == null) {
				throw new Exception("El reporte que va a registrar no tiene identificador de Libro");
			}
			if(p.getIdPrestamo() == null) {
				throw new Exception("El reporte que va a registrar no tiene identificador de Prestamo");
			}
			if(p.getIdDevolucion() == null) {
				throw new Exception("El reporte que va a registrar no tiene identificador de devolucion");
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
						//Revisamos que la Devolucion exista
						Optional<Devolucion> deBusqueda = devolucion.findById(p.getIdDevolucion());
						if(deBusqueda.get().getIdDevolucion().equals(p.getIdDevolucion())){
							dao.save(p);
						}else {
							throw new Exception("El reporte que va a registrar no tiene identificador de devolucion registrada");
						}
					}else {
						throw new Exception("El reporte que va a registrar no tiene identificador de Prestamo registrado");
					}
				}else {
					throw new Exception("El reporte que va a registrar no tiene Libro registrado");
				}
			}else {
				throw new Exception("El reporte que va a registrar no tiene Usuario registrado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Reporte p) {
		try {
			if (p.getIdReporte() != null) {
				throw new Exception("El reporte a persistir no tiene identificador");
			}
			if(p.getIdUsuario() == null) {
				throw new Exception("El reporte que va a registrar no tiene identificador de Usuario");
			}
			if(p.getIdLibro() == null) {
				throw new Exception("El reporte que va a registrar no tiene identificador de Libro");
			}
			if(p.getIdPrestamo() == null) {
				throw new Exception("El reporte que va a registrar no tiene identificador de Usuario");
			}
			if(p.getIdDevolucion() == null) {
				throw new Exception("El reporte que va a registrar no tiene identificador de devolucion");
			}
	
			//Revisamos que el usuario exista
			Optional<Usuario> usBusqueda = usuario.findById(p.getIdUsuario());
			if(usBusqueda.get().getIdUsuario().equals(p.getIdUsuario())) {
				//Revisamos que el Libro exista
				Optional<Libro> liBusqueda = libro.findById(p.getIdLibro());
				if(liBusqueda.get().getIdLibro().equals(p.getIdLibro())) {
					Optional<Prestamos> prBusqueda = prestamos.findById(p.getIdPrestamo());
					if(prBusqueda.get().getIdPrestamo().equals(p.getIdPrestamo())) {
						Optional<Devolucion> deBusqueda = devolucion.findById(p.getIdDevolucion());
						if(deBusqueda.get().getIdDevolucion().equals(p.getIdDevolucion())){
							dao.save(p);
						}else {
							throw new Exception("El reporte que va a registrar no tiene identificador de devolucion registrada");
						}
					}else {
						throw new Exception("El reporte que va a registrar no tiene identificador de Prestamo registrado");
					}
				}else {
					throw new Exception("El reporte que va a registrar no tiene Libro registrado");
				}
			}else {
				throw new Exception("El reporte que va a registrar no tiene Usuario registrado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void borrar(Reporte p) {
		try {
			if (p.getIdReporte() == null) {
				throw new Exception("El Reporte a eliminar no tiene identificador");
			}
			//Revisamos que el Reporte exista
			Optional<Reporte> aux = dao.findById(p.getIdReporte());
			p =  aux.get();
			dao.delete(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public List<Reporte> SelectTodos() {
		List<Reporte> list = null;
		try {
			list = dao.findAll();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return list;
	}

}
