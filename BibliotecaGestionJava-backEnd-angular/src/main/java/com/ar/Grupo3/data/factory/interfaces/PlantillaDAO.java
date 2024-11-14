package com.ar.Grupo3.data.factory.interfaces;

import java.util.List;
import java.util.Optional;

//Interfaz madre que permitira el el comportamiento basico de una tabla
public interface PlantillaDAO<PDAO>{

	public Optional<?> selectPorId(Long id);

    public Long contarTodos();

    public void agregar(PDAO p);

    public void modificar(PDAO p);

    public void borrar(PDAO p);

    public List<PDAO> SelectTodos();
	
}
