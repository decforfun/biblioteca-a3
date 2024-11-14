package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoLibroIntf;
import com.ar.Grupo3.model.Libro;

@RestController
@RequestMapping({ "/Biblioteca" })
public class WServiceLibro implements Serializable {

	private static final long serialVersionUID = -872648384162231067L;

	@Autowired
	private DaoLibroIntf servicioLibro = FabricaDAO.obtenerLibro();

	/*
	 * ----------------------------------------------------------------------------
	 * Libros
	 * ----------------------------------------------------------------------------
	 */

	@GetMapping(path = "/libro")
	public List<Libro> listarLibros() {
		List<Libro> listaLibros = mostrar();
		return listaLibros;
	}

	@GetMapping(path = "/libro/{id}")
	public Optional<?> buscarLibro(@PathVariable("id") Long id) {
		return buscarPorId(id);
	}

	@PostMapping(path = "/libro/agregar")
	public Libro AgregarLibro(@RequestBody Libro libro) {
		return agregamos(libro);
	}

	@PutMapping(path = "/libro/modificar/{id}")
	public ResponseEntity<Libro> modificarLibro(@PathVariable("id") Long id, @RequestBody Libro libro) {
		if (libro != null) {
			modificamos(libro, id);
			return new ResponseEntity<Libro>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/libro/eliminar/{id}")
	public ResponseEntity<Libro> eliminarLibroporId(@PathVariable("id") Long id) {

		int var = eliminar(id);
		if (var == 0) {
			return new ResponseEntity<Libro>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);
		}
	}

	// __________________________________________________________________________________
	// Logica
	// __________________________________________________________________________________

	// Bucamos todos las Libroes
	public List<Libro> mostrar() {
		return servicioLibro.SelectTodos();
	}

	// Buscamos un Libro por ID
	public Optional<?> buscarPorId(Long id) {
		return servicioLibro.selectPorId(id);
	}

	// Agregamos un por ID
	public Libro agregamos(Libro Libro) {
		servicioLibro.agregar(Libro);
		return Libro;
	}

	// modificamos Libro
	public void modificamos(Libro LibroModificado, Long id) {
		// Verificamos que no nos llegue nulo el id
		if (id != null) {
			Optional<?> busca = servicioLibro.selectPorId(id);
			// Ahora si modificamos la Libro
			if (busca != null) {
				servicioLibro.modificar(LibroModificado);
			}
		}
	}

	// Eliminamos la Libro, no se para que esto dudo que pase a menos que...
	public Integer eliminar(Long id) {
		Integer aux = 1;
		// nos fijamos que no nos llegue nulo
		try {
			if (id != null) {
				Libro elimina = new Libro(id);
				// eliminar el Libro
				if (elimina != null) {
					servicioLibro.borrar(elimina);
					aux = 0;
				}
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return aux;
	}
}
