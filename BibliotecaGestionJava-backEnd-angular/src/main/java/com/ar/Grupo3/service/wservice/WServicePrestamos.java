package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoPrestamosIntf;
import com.ar.Grupo3.model.Prestamos;

@RestController
@RequestMapping({ "/Biblioteca" })
public class WServicePrestamos implements Serializable {

	private static final long serialVersionUID = -2402851410237303555L;

	@Autowired
	private DaoPrestamosIntf servicioPrestamos = FabricaDAO.obtenerPrestamos();

	/*
	 * ----------------------------------------------------------------------------
	 * Prestamo
	 * ----------------------------------------------------------------------------
	 */
	@GetMapping(path = "/prestamo")
	public List<Prestamos> mostrarPrestamos() {
		List<Prestamos> listaPrestamos = mostrar();
		return listaPrestamos;
	}

	@GetMapping(path = "/prestamo/{id}")
	public Optional<?> buscarPrestamos(@PathVariable("id") Long id) {
		return buscarPorId(id);
	}

	@PostMapping(path = "/prestamo/agregar")
	public Prestamos AgregarPrestamos(@RequestBody Prestamos prestamos) {
		return agregamos(prestamos);
	}

	@PutMapping(path = "/prestamo/modificar/{id}")
	public ResponseEntity<Prestamos> modificarPrestamos(@PathVariable("id") Long id, @RequestBody Prestamos prestamos) {
		if (prestamos != null) {
			modificamos(prestamos, id);
			return new ResponseEntity<Prestamos>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Prestamos>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/prestamo/eliminar/{id}")
	public ResponseEntity<Prestamos> eliminarPrestamos(@PathVariable("id") Long id) {

		int var = eliminar(id);
		if (var == 0) {
			return new ResponseEntity<Prestamos>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Prestamos>(HttpStatus.NOT_FOUND);
		}
	}

	// __________________________________________________________________________________
	// Logica
	// __________________________________________________________________________________

	// Bucamos todos las Prestamoses
	public List<Prestamos> mostrar() {
		return servicioPrestamos.SelectTodos();
	}

	// Buscamos un Prestamos por ID
	public Optional<?> buscarPorId(Long id) {
		return servicioPrestamos.selectPorId(id);
	}

	// Agregamos un por ID
	public Prestamos agregamos(Prestamos Prestamos) {
		servicioPrestamos.agregar(Prestamos);
		return Prestamos;
	}

	// modificamos Prestamos
	public void modificamos(Prestamos PrestamosModificado, Long id) {
		// Verificamos que no nos llegue nulo el id
		if (id != null) {
			Optional<?> busca = servicioPrestamos.selectPorId(id);
			// Ahora si modificamos la Prestamos
			if (busca != null) {
				servicioPrestamos.modificar(PrestamosModificado);
			}
		}
	}

	// Eliminamos la Prestamos, no se para que esto dudo que pase a menos que...
	public Integer eliminar(Long id) {
		Integer aux = 1;
		// nos fijamos que no nos llegue nulo
		try {
			if (id != null) {
				Prestamos elimina = new Prestamos(id);
				// eliminar el Prestamos
				if (elimina != null) {
					servicioPrestamos.borrar(elimina);
					aux = 0;
				}
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return aux;
	}
}
