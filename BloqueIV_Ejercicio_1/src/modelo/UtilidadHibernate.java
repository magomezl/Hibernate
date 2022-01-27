package modelo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * 
 * Singleton patr�n de dise�o que se encarga de que una clase  solo pueda tener un �nico objeto.
 * habituales en temas como configurar par�metros generales de la aplicaci�n ya que una vez instanciado el objeto los valores se mantienen y son compartidos  por toda la aplicaci�n
 * Nuestro singleton ser� una clase para acceder a SessionFactory para obtener objetos sesion
 * Hay una sola SessionFactory para toda la aplicacion
 * Definimos una variable est�tica que recoge un objeto SessionFactory que se crea a partir del fichero de configuraci�n
 * hibernate.cfg.xml
 * Con esta clase podemos obtener la sesi�n actual desde cualquier parte de nuestra aplicaci�n 
 * @author Aurora
 *
 */

public class UtilidadHibernate {

		private static final SessionFactory sesionFactoria = new Configuration().configure().buildSessionFactory();

		public static SessionFactory getSesionfactoria() {
			return sesionFactoria;
		}
		
		public static void closeSesionfactoria() {
			sesionFactoria.close();
		}
}

