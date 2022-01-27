package modelo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * 
 * Singleton patrón de diseño que se encarga de que una clase  solo pueda tener un único objeto.
 * habituales en temas como configurar parámetros generales de la aplicación ya que una vez instanciado el objeto los valores se mantienen y son compartidos  por toda la aplicación
 * Nuestro singleton será una clase para acceder a SessionFactory para obtener objetos sesion
 * Hay una sola SessionFactory para toda la aplicacion
 * Definimos una variable estática que recoge un objeto SessionFactory que se crea a partir del fichero de configuración
 * hibernate.cfg.xml
 * Con esta clase podemos obtener la sesión actual desde cualquier parte de nuestra aplicación 
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

