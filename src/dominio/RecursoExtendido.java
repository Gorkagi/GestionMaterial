package dominio;

import java.time.LocalDateTime;

import persistencia.DAOPersonas;
import persistencia.DAOPrestamos;
import persistencia.DAOReservas;

public class RecursoExtendido extends Recurso {
	 
	String nombreResponsable;
	Estado estado;
	public RecursoExtendido(int id, String nombre, String descripcion, String ubicacion, int idResponsable, String tipo) throws Exception {
		super(id, nombre, descripcion, ubicacion, idResponsable, tipo);
		nombreResponsable = DAOPersonas.buscarPorId(idResponsable).getNombre();
		estado = identificarEstadoActualRecurso(id);
	}
	public RecursoExtendido (Recurso recurso) throws Exception{
		super(recurso);
		nombreResponsable = DAOPersonas.buscarPorId(idResponsable).getNombre();
		estado = identificarEstadoActualRecurso(id);
	}
	private Estado identificarEstadoActualRecurso(int id) {
		LocalDateTime ahora = LocalDateTime.now();
		if (DAOPrestamos.estaPrestado(id,ahora)) return Estado.PRESTADO;
		if (DAOReservas.estaReservado(id,ahora)) return Estado.RESERVADO;
		
		return Estado.DISPONIBLE;
	}
	public String getNombreResponsable() {
		return nombreResponsable;
	}
	public Estado getEstado() {
		return estado;
	}
	

}
