package modelo;

import java.lang.reflect.InvocationTargetException;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

public class ConexionXMLDB {
	private static ConexionXMLDB instancia;
	private static Collection col; 

	private ConexionXMLDB(String usuario, String contrasenia) {
		try {
			//Cargamos el driver eXist
			Class cl = Class.forName("org.exist.xmldb.DatabaseImpl");
			// Creamos una instancia de la bbdd
			Database database = (Database) cl.getDeclaredConstructor().newInstance();
			// Registro del driver
			DatabaseManager.registerDatabase(database);
			col = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db", usuario, contrasenia);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConexionXMLDB getInstancia(String usuario, String contrasenia) {
		if (instancia==null) {
			instancia = new ConexionXMLDB(usuario, contrasenia);
		}
		return instancia;
	}
	
	public static Collection getCol() {
		return col;
	}
	
	
}
