package crud_mvc;

import controlador.Controlador;
import modelo.Proceso;
import vista.VistaDptos;


public class Principal {

	public static void main(String[] args) {
		System.out.println("Inciando");
		Proceso modelo = new Proceso();
		VistaDptos vista = new VistaDptos(); 
		Controlador ctrl = new Controlador(modelo, vista);
		System.out.println("Creado controlador");
		ctrl.abrirVentana();
		System.out.println("Ventana abierta");
		vista.setVisible(true);
		System.out.println("Ventana visible");
	
	}

}
