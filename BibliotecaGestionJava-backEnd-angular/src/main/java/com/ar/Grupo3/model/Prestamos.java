package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "prestamos")
public class Prestamos implements Serializable {

	private static final long serialVersionUID = -7029446545042595766L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prestamo")
    private Long idPrestamo;
    
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @Basic(optional = false)
    @Column(name = "id_libro")
    private Long idLibro;
    
    @Basic(optional = false)
    @Column(name = "dia_prestamo")
    private Date dia_prestamo;
    
    @Basic(optional = false)
    @Column(name = "dia_devolucion")
    private Date dia_devolucion_estimativo;
    
    @Column(name = "fecha_prestamo")
    private String fechaPrestamo;
    
    @Column(name = "fecha_regreso")
    private String fechaRegreso;

    public Prestamos() {
        //Constructor Vacio
    }

    public Prestamos(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Prestamos(Long idPrestamo, Long idUsuario, Long idLibro) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
    }

    public Prestamos(Long idPrestamo, Long idUsuario, Long idLibro, 
            String fechaPrestamo, String fechaRegreso) {
        this.idPrestamo = idPrestamo;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaRegreso = fechaRegreso;
    }

    //Gettes Ans Setters
    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(String fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }
    
    public Date getDia_prestamo() {
		return dia_prestamo;
	}

	public void setDia_prestamo(Date dia_prestamo) {
		this.dia_prestamo = dia_prestamo;
	}

	public Date getDia_devolucion_estimativo() {
		return dia_devolucion_estimativo;
	}

	public void setDia_devolucion_estimativo(Date dia_devolucion_estimativo) {
		this.dia_devolucion_estimativo = dia_devolucion_estimativo;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrestamo != null ? idPrestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Prestamos)) {
            return false;
        }
        Prestamos other = (Prestamos) object;
        if ((this.idPrestamo == null && other.idPrestamo != null) || (this.idPrestamo != null && !this.idPrestamo.equals(other.idPrestamo))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Prestamos [idPrestamo=" + idPrestamo + ", idUsuario=" + idUsuario + ", idLibro=" + idLibro
				+ ", dia_prestamo=" + dia_prestamo + ", dia_devolucion_estimativo=" + dia_devolucion_estimativo
				+ ", fechaPrestamo=" + fechaPrestamo + ", fechaRegreso=" + fechaRegreso + "]";
	}

    
}
