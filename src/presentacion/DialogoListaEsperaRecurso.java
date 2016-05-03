package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dominio.RecursoExtendido;

public class DialogoListaEsperaRecurso extends JDialog implements ListSelectionListener{
	
	RecursoExtendido recurso;
	JTextField tFechaInic, tFechaEntrega;
	JLabel lFechaInic, lFechaEntrega;
	JButton bReserva;
	JScrollPane panelScroll;
	TrazadorTablaListaRecursos trazador;
	ModeloColumnaListaRecursos columnas;
	ModeloTablaListaRecursos modelo;
	JTable tablaRecursos;
	

	public DialogoListaEsperaRecurso (JFrame frame, RecursoExtendido recurso){
		super ( frame,"Lista Espera Recurso",true );
		this.recurso = recurso;
		this.setLocation(200, 100);
		this.setSize(600,450);
		this.setContentPane(crearPanelVentana());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(crearPanelDatos(),BorderLayout.NORTH);
		panel.add(crearPanelTabla(),BorderLayout.CENTER);
		panel.add(crearPanelBoton(),BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		return panel;
	}

	private Component crearPanelBoton() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		bReserva = new JButton("HACER RESERVA");
		panel.add(bReserva);
		return panel;
	}

	private Component crearPanelTabla() {
		panelScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		crearTabla();
		panelScroll.setViewportView(tablaRecursos);
		return panelScroll;
	}

	private void crearTabla() {
		trazador = new TrazadorTablaListaRecursos();
		columnas = new ModeloColumnaListaRecursos(trazador);
		try{
			modelo = new ModeloTablaListaRecursos();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		tablaRecursos = new JTable(modelo,columnas);
		tablaRecursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRecursos.getSelectionModel().addListSelectionListener(this);
		tablaRecursos.setFillsViewportHeight(true);
		tablaRecursos.getTableHeader().setReorderingAllowed(false);
		//tablaRecursos.setRowSelectionInterval(0, 0);
		tablaRecursos.setFillsViewportHeight(true);
		
	}

	private Component crearPanelDatos() {
		JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,crearPanelFechaInic(),crearPanelFechaEntrega());
		panel.setDividerLocation(272);
		panel.setEnabled(false);
		panel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		
		return panel;
	}

	private Component crearPanelFechaEntrega() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Producto"));
		
		lFechaEntrega = new JLabel("Fecha de Entrega : ");
		tFechaEntrega = new JTextField();
		tFechaEntrega.setPreferredSize(new Dimension(80,20));
		
		panel.add(lFechaEntrega);
		panel.add(tFechaEntrega);
		
		return panel;
	}

	private Component crearPanelFechaInic() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Lista de espera para"));
		
		lFechaInic = new JLabel("Fecha de Inicio : ");
		tFechaInic = new JTextField();
		tFechaInic.setPreferredSize(new Dimension(80,20));
		
		panel.add(lFechaInic);
		panel.add(tFechaInic);
		
		return panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
