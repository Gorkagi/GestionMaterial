package dominio;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import persistencia.DAOReservas;
import presentacion.ModeloColumnaListaRecursos;
import presentacion.ModeloColumnasTablaRecursos;

public class ModeloTablaLista extends AbstractTableModel{
	
	ModeloColumnaListaRecursos columnas;
	ArrayList<Reserva> listaReservas;

	public ModeloTablaLista(ModeloColumnaListaRecursos columnas){
		super();
		this.columnas = columnas;
		listaReservas = DAOReservas.getListaReservas();
		for(Reserva r : listaReservas) {
			System.out.println(""+r.getId());
			System.out.println(""+r.getTipo());
			
		}
		
	}
	
	public Reserva getRecursoAt(int indice){
		return listaReservas.get(indice);
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaReservas.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.getColumnCount();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Reserva a = listaReservas.get(fila);
		return getFieldAt(a,columna);
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return false;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0,columnIndex).getClass();
	}

	private Object getFieldAt(Reserva a, int columna) {
		switch (columna){
		case 0: return new Integer(a.getId());
		case 1: return a.getRecurso().getNombre();
		case 2: return a.getDesde();
		case 3: return a.getHasta();
	
		}
		return null;
	}

}
