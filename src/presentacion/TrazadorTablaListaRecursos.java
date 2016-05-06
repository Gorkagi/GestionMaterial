package presentacion;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrazadorTablaListaRecursos extends DefaultTableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor,
			boolean isSelected, boolean hasFocus, int fila, int columna) {

		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		
		switch (columna ){
		case 0: super.setHorizontalAlignment(CENTER);break;
		case 1: super.setHorizontalAlignment(CENTER);break;
		case 2: super.setHorizontalAlignment(CENTER);break;
		case 3: super.setHorizontalAlignment(CENTER);break;

		}
		return this;
	}

}
