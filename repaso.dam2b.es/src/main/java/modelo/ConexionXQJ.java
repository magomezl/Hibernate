package modelo;

import java.lang.reflect.InvocationTargetException;

import javax.xml.xquery.XQConnection;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;

import net.xqj.exist.ExistXQDataSource;

public class ConexionXQJ {
	private static ConexionXQJ instancia;
	private static XQConnection xqc; 

	private ConexionXQJ(String usuario, String contrasenia) {
		// Conectamos con Existdb con la API XQJ
		ExistXQDataSource xqs =  new ExistXQDataSource();
		xqs.setProperty("serverName", "localhost");
		xqs.setProperty("port", "8080");
		xqs.setProperty("user", usuario);
		xqs.setProperty("password", contrasenia);

		xqc = xqs.getConnection();
	}
	
	public static ConexionXQJ getInstancia(String usuario, String contrasenia) {
		if (instancia==null) {
			instancia = new ConexionXQJ(usuario, contrasenia);
		}
		return instancia;
	}
	
	public static XQConnection getCon() {
		return xqc;
	}
	
	
}
