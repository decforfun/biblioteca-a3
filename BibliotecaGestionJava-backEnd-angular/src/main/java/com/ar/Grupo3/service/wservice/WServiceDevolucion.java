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
import com.ar.Grupo3.data.objects.interfaces.DaoDevolucionIntf;
import com.ar.Grupo3.model.Devolucion;

@RestController
@RequestMapping({ "/Biblioteca" })
public class WServiceDevolucion implements Serializable {

	private static final long serialVersionUID = -6850270540912061541L;

	@Autowired
	private DaoDevolucionIntf servicioDevolucion = FabricaDAO.obtenerDevolucion();

	/*
	 * ----------------------------------------------------------------------------
	 * Devolucion
	 * ----------------------------------------------------------------------------
	 */
	@GetMapping(path = "/devolucion")
	public List<Devolucion> mostrarDevoluciones() {
		List<Devolucion> listaDevoluciones = listar();
		return listaDevoluciones;
	}

	@GetMapping(path = "/devolucion/{id}")
	public Optional<?> buscarDevolucion(@PathVariable("id") Long id) {
		return buscarPorId(id);
	}

	@PostMapping(path = "/devolucion/agregar")
	public Devolucion AgregarDevolucion(@RequestBody Devolucion devolucion) {
		return agregamos(devolucion);
	}

	@PutMapping(path = "/devolucion/modificar/{id}")
	public ResponseEntity<Devolucion> modificarDevolucion(@PathVariable("id") Long id,
			@RequestBody Devolucion devolucion) {
		if (devolucion != null) {
			modificamos(devolucion, id);
			return new ResponseEntity<Devolucion>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Devolucion>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/devolucion/eliminar/{id}")
	public ResponseEntity<Devolucion> eliminarDevolucion(@PathVariable("id") Long id) {

		int var = eliminar(id);
		if (var != 0) {
			return new ResponseEntity<Devolucion>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Devolucion>(HttpStatus.NOT_FOUND);
		}
	}
	
	//__________________________________________________________________________________
	//Logica
	//__________________________________________________________________________________

	// Bucamos todos las Devoluciones
	public List<Devolucion> listar() {
		return servicioDevolucion.SelectTodos();
	}

	// Buscamos un devolucion por ID
	public Optional<?> buscarPorId(Long id) {
		return servicioDevolucion.selectPorId(id);
	}

	// Agregamos un por ID
	public Devolucion agregamos(Devolucion devolucion) {
		servicioDevolucion.agregar(devolucion);
		return devolucion;
	}

	// modificamos devolucion
	public void modificamos(Devolucion devolucionModificado, Long id) {
		// Verificamos que no nos llegue nulo el id
		if (id != null) {
			Optional<?> busca = servicioDevolucion.selectPorId(id);
			// Ahora si modificamos la devolucion
			if (busca != null) {
				servicioDevolucion.modificar(devolucionModificado);
			}
		}
	}

	// Eliminamos la devolucion, no se para que esto dudo que pase a menos que...
	public Integer eliminar(Long id) {
		Integer aux = 1;
		// nos fijamos que no nos llegue nulo
		try {
			if (id != null) {
				Devolucion elimina = new Devolucion(id);
				// eliminar el Devolucion
				if (elimina != null) {
					servicioDevolucion.borrar(elimina);
					aux = 0;
				}
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return aux;
	}

}
