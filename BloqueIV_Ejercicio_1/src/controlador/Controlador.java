package controlador;

import modelo.Proceso;
import modelo.UtilidadHibernate;

import vista.VistaDptos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;


public class Controlador implements ActionListener{
	private Proceso modelo;
	private VistaDptos vista;
	
	public Controlador(Proceso modelo, VistaDptos vista) {
		this.modelo = modelo;
		this.vista = vista;
		
		// Le decimos que nosotros (el controlador) vamos a gestionar los eventos (clics) sobre los botones de la vista
		this.vista.btnGuardar.addActionListener(this);
		
	}
	
	public void abrirVentana() {
		vista.setTitle("ALTA DEPARTAMENTOS");
		// Posicion centrada
		vista.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==vista.btnGuardar) {
			try {
				Proceso.anadirDepartamento(UtilidadHibernate.getSesionfactoria(), Byte.parseByte(vista.txtDptoNum.getText()), vista.txtNombre.getText(), vista.txtLocalidad.getText());
			}catch (Exception ex) {
				System.out.println(ex.getStackTrace());
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}finally {
				//UtilidadHibernate.closeSesionfactoria();
			}
		}
	}
}
