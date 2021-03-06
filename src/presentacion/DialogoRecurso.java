package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dominio.Persona;
import dominio.RecursoExtendido;
import persistencia.DAOPersonas;
import persistencia.DAORecursos;

public class DialogoRecurso extends JDialog implements ActionListener{
	
	final static String  TITULO = "Crear nuevo recurso";
	JComboBox<String> comboResponsable, comboTipo;
	JTextField txNombre,txDescripcion,txUbicacion,txResponsable;
	ArrayList<Persona> listaPersonas= null; 
	ArrayList<String> listaTiposRecurso = null;
	boolean cambioRealizado = false;
	RecursoExtendido recurso = null;
	boolean editando = false;
	
	public DialogoRecurso (JFrame frame, boolean modo){
		super ( frame,TITULO,modo );
		crearVentana();
		this.setVisible(true);
	}

	private void crearVentana() {
		this.setLocation(280,200);
		this.setSize(300, 380);
		this.setContentPane(crearPanelDialogo());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public DialogoRecurso(FormRecursos frame, RecursoExtendido recurso, boolean modo) {
		super(frame,TITULO,modo);
		this.recurso = recurso;
		crearVentana();
		txNombre.setText(recurso.getNombre());
		txDescripcion.setText(recurso.getDescripcion());
		txUbicacion.setText(recurso.getUbicacion());
		for (Persona p :listaPersonas){
			if (p.getId() ==recurso.getIdResponsable()){
				comboResponsable.setSelectedIndex(listaPersonas.indexOf(p));
				break;
			}
		}
		editando = true;
		this.setVisible(true);
	}

	private Container crearPanelDialogo() {
		JPanel panel = new JPanel (new BorderLayout(0,20));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panel.add(crearPanelCampos (), BorderLayout.CENTER);
		panel.add(crearPanelBotones(), BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel (new FlowLayout(FlowLayout.CENTER,30,0));
		JButton bOk = new JButton ("Validar");
		bOk.setActionCommand("OK");
		bOk.addActionListener(this);
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("Cancelar");
		bCancel.addActionListener(this);
		
		panel.add(bOk);
		panel.add(bCancel);
		return panel;
	}

	private Component crearPanelCampos() {
		JPanel panel = new JPanel (new GridLayout(5,1,0,20));
		
		String nombres [];
		String tipos [];
		
		panel.add(txNombre = crearCampo("Nombre"));
		panel.add(txDescripcion = crearCampo("Descripci�n"));
		panel.add(txUbicacion = crearCampo("Ubicaci�n"));
		try {
			listaPersonas = DAOPersonas.obtenerPersonas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			listaTiposRecurso = DAORecursos.obtenerTiposRecursos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nombres = new String [listaPersonas.size()];
		for (int i = 0; i<nombres.length; i++){
			nombres [i] = listaPersonas.get(i).getNombre();
		}
		
		tipos = new String[listaTiposRecurso.size()];
		for (int i = 0; i<tipos.length; i++){
			tipos[i] = listaTiposRecurso.get(i);
		}
		
		comboResponsable = new JComboBox<>(nombres);
		comboResponsable.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.CYAN),"Responsable"));
		panel.add(comboResponsable);
		
		comboTipo = new JComboBox<String>(tipos);
		comboTipo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.CYAN),"Tipo Recurso"));
		panel.add(comboTipo);
		return panel;
	}

	private JTextField crearCampo(String titulo) {
		JTextField campo = new JTextField();
		campo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.CYAN),titulo));
		
		return campo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "OK" : if (camposIncompletos()){
						JOptionPane.showMessageDialog(this, "Es necesario rellenar todos los campos",
								"Error datos incompletos", JOptionPane.ERROR_MESSAGE);
					}else if (editando){
						try {
							DAORecursos.modificarRecurso(recurso.getId(),txNombre.getText(), txUbicacion.getText(),
									txDescripcion.getText(), listaPersonas.get(comboResponsable.getSelectedIndex()).getId(),
									comboTipo.getSelectedIndex()+1);
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(this, "Recurso actualizado",
								"Accion realizada", JOptionPane.INFORMATION_MESSAGE);
					}else{
						DAORecursos.InsertarRecurso(txNombre.getText(), txUbicacion.getText(),
								txDescripcion.getText(), listaPersonas.get(comboResponsable.getSelectedIndex()).getId(),
								comboTipo.getSelectedIndex()+1);
						JOptionPane.showMessageDialog(this, "Recurso a�adido",
								"Accion realizada", JOptionPane.INFORMATION_MESSAGE);
					}
					this.cambioRealizado = true;
					this.dispose();
					break;
					
		case "Cancelar":
					this.dispose();
		}
		
	}

	private boolean camposIncompletos() {
		
		return txNombre.getText().length()==0 ||txUbicacion.getText().length()==0 || txDescripcion.getText().length()==0;
	}

	public boolean isCambioRealizado() {
		return cambioRealizado;
	}

	
}
