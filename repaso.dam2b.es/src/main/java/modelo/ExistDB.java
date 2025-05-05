package modelo;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;

import net.xqj.exist.ExistXQDataSource;

public class ExistDB {

	public static void main(String[] args) {
		try {
			subeRecurso("admin", "toor", "EjerciciosRepaso", "./src/main/resources/religiones.xml");
			
			//Iniciamos el árbol DOM
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			
			//Creamos el elemento raíz
			Element raiz = doc.createElement("religiones");
			doc.appendChild(raiz);
			
			//TODO Creamos la consulta XQuery de la que extraeremos los datos con los que crearemos el árbol DOM
			//recorrer los elementos del resultado añadiéndolos al árbol DOM según la estructura dada
			String query = "for $pr in doc('EjerciciosRepaso/religiones.xml')/geografia/religiones_en_paises/practica\r\n"
					+ "    let $p := $pr/../..//pais[@id_pais=$pr/@id_pais]/@nombre\r\n"
					+ "    let $r := $pr/../..//religion[@id_religion=$pr/@id_religion]/@denominacion\r\n"
					+ "    return\r\n"
					+ "        <practicaReligion> \r\n"
					+ "            <pais nombre='{$p}'/>\r\n"
					+ "            <religion denominacion='{$r}'/>\r\n"
					+ "            <devotos practicantes='{$pr/@practicantes}'/>\r\n"
					+ "        </practicaReligion>";
			XQPreparedExpression expr = ConexionXQJ.getInstancia("admin", "toor").getCon().prepareExpression(query);
			XQResultSequence result = expr.executeQuery();
			while(result.next()) {
				Node nodo = result.getNode();
				Node importedNodo = doc.importNode(nodo, true);
				raiz.appendChild(importedNodo);
			}

			//Hacemos la transformación del árbol DOM que está en memoria a un fichero físico
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(doc), new StreamResult(new File("./src/main/resources/religionesSintesis.xml")));
			
			subeRecurso("admin", "toor", "EjerciciosRepaso", "./src/main/resources/religionesSintesis.xml");
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private static void subeRecurso(String usuario, String contrasenia, String colName, String fileURL) {

		try {
			Collection col, col_padre = ConexionXMLDB.getInstancia(usuario, contrasenia).getCol();


			if ((col=(col_padre.getChildCollection(colName)))==null) {
				CollectionManagementService servicio = (CollectionManagementService) col_padre.getService("CollectionManagementService", "1.0");
				col = servicio.createCollection(colName);
			}


			// Comprobamos si el fichero (recurso en eXistdb) ya está en la colección creada en eXistbd
			File file = new File(fileURL);

			XMLResource recurso = (XMLResource) col.getResource(file.getName());
			if (recurso==null) {
				//No existe el recurso en la colección de existdb
				//Lo creamos, le asinamos el contenido del fichero y lo situamos en la coleccioón
				recurso = (XMLResource) col.createResource(file.getName(), "XMLResource");
				recurso.setContent(file);
				col.storeResource(recurso);
			}

		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
