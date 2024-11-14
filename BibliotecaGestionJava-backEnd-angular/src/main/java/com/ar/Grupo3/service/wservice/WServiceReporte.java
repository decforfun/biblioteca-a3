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
import com.ar.Grupo3.data.objects.interfaces.DaoReportesInt;
import com.ar.Grupo3.model.Prestamos;
import com.ar.Grupo3.model.Reporte;

@RestController
@RequestMapping({ "/Biblioteca" })
public class WServiceReporte implements Serializable {

	private static final long serialVersionUID = 6167268357511330677L;

	@Autowired
	private DaoReportesInt servicioReporte = FabricaDAO.obtenerReporte(); 

	/*
	 * ----------------------------------------------------------------------------
	 * Reporte
	 * ----------------------------------------------------------------------------
	 */
	@GetMapping(path = "/reporte")
	public List<Reporte> mostrarReportes() {
		List<Reporte> listaReporte = mostrar();
		return listaReporte;
	}

	@GetMapping(path = "/reporte/{id}")
	public Optional<?> buscarReportes(@PathVariable("id") Long id) {
		return buscarPorId(id);
	}

	@PostMapping(path = "/reporte/agregar")
	public Reporte AgregarReporte(@RequestBody Reporte reporte) {
		return agregamos(reporte);
	}

	@PutMapping(path = "/reporte/modificar/{id}")
	public ResponseEntity<Reporte> modificarReportes(@PathVariable("id") Long id, @RequestBody Reporte reporte) {
		if (reporte != null) {
			modificamos(reporte, id);
			return new ResponseEntity<Reporte>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Reporte>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/reporte/eliminar/{id}")
	public ResponseEntity<Prestamos> eliminarReporte(@PathVariable("id") Long id) {

		int var = eliminar(id);
		if (var != 0) {
			return new ResponseEntity<Prestamos>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Prestamos>(HttpStatus.NOT_FOUND);
		}
	}

	// __________________________________________________________________________________
	// Logica
	// __________________________________________________________________________________

	// Bucamos todos las Reportees
	public List<Reporte> mostrar() {
		return servicioReporte.SelectTodos();
	}

	// Buscamos un Reporte por ID
	public Optional<?> buscarPorId(Long id) {
		return servicioReporte.selectPorId(id);
	}

	// Agregamos un por ID
	public Reporte agregamos(Reporte Reporte) {
		servicioReporte.agregar(Reporte);
		return Reporte;
	}

	// modificamos Reporte
	public void modificamos(Reporte ReporteModificado, Long id) {
		// Verificamos que no nos llegue nulo el id
		if (id != null) {
			Optional<?> busca = servicioReporte.selectPorId(id);
			// Ahora si modificamos la Reporte
			if (busca != null) {
				servicioReporte.modificar(ReporteModificado);
			}
		}
	}

	// Eliminamos la Reporte, no se para que esto dudo que pase a menos que...
	public Integer eliminar(Long id) {
		Integer aux = 1;
		// nos fijamos que no nos llegue nulo
		try {
			if (id != null) {
				Reporte elimina = new Reporte(id);
				// eliminar el Reporte
				if (elimina != null) {
					servicioReporte.borrar(elimina);
					aux = 0;
				}
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage() + " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return aux;
	}

}
