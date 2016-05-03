package presentacion;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnaListaRecursos extends DefaultTableColumnModel{

	TrazadorTablaListaRecursos trazador;

	public ModeloColumnaListaRecursos(TrazadorTablaListaRecursos trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("ID",0,50));
		this.addColumn(crearColumna("Nombre",1,100));
		this.addColumn(crearColumna("Fecha Inicio",2,200));
		this.addColumn(crearColumna("Fecha Entrega",3,100));
	
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);

		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);

		return columna;
	}

}
