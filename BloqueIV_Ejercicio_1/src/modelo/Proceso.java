package modelo;

import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Proceso {
	
	public static void anadirDepartamento(SessionFactory sesionFabric, byte dptoNum, String nombre, String localidad) {
		// Creamos la sesion
		System.out.println("1");
		Session miSesion = sesionFabric.openSession();
		System.out.println("2");
		// Crear una transaccion en la sesion que inserta una fila en la tabla departamentos
		Transaction transacion = miSesion.beginTransaction();
		System.out.println("3");	
		Departamentos dep = new Departamentos();
		dep.setDeptno(dptoNum);
		dep.setDnombre(nombre);
		dep.setLoc(localidad);
		System.out.println("4");	
		// Guardamos el objeto. Pasamos como argumento el objeto a guardar a la interface Session
		miSesion.save(dep);
		// Hacemos un commit de la transaccion actual. Necesario para que los datos se almacenen en la bbdd
		transacion.commit();
		System.out.println("5");
		// Cerramos la sesion
		miSesion.close();
		JOptionPane.showMessageDialog(null, "Departamento Guardado");
	}
}
