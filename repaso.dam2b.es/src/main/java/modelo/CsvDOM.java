package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.opencsv.CSVReader;

public class CsvDOM {

	public static void main(String[] args) {
		boolean primeraLinea = true;
		try {
			CSVReader reader = new CSVReader(new FileReader("./src/main/resources/paises.csv"), ',', '"');
			
//			DecimalFormat formatoDec = new DecimalFormat("#,###.##");
			
//			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dB = dBF.newDocumentBuilder();
//			Document doc = dB.newDocument();
			
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			
			String[] lineaPais = null;
			
			Element raiz = doc.createElement("paises");
			doc.appendChild(raiz);
			while((lineaPais=reader.readNext())!=null) {
				if(!primeraLinea) {
					Element pais = doc.createElement("pais");
					pais.setAttribute("nombre", lineaPais[0]);
					Element habitantes = doc.createElement("habitantes");
					habitantes.appendChild(doc.createTextNode(String.valueOf(Double.parseDouble(lineaPais[1]))));
					pais.appendChild(habitantes);
					
					Element idiomasOficiales = doc.createElement("idiomas_oficiales");
					String[] idiomas = lineaPais[2].split(",");
					for(String idioma: idiomas) {
						Element idiomaE = doc.createElement("idioma");
						idiomaE.appendChild(doc.createTextNode(idioma));
						idiomasOficiales.appendChild(idiomaE);
					}
					pais.appendChild(idiomasOficiales);
					
					Element superficie = doc.createElement("superficie");
					superficie.setAttribute("km_linea_costa", String.valueOf(Double.parseDouble(lineaPais[4])));
					superficie.setAttribute("km2_agua", String.valueOf(Double.parseDouble(lineaPais[5])));
					superficie.setAttribute("km2_tierra", String.valueOf(Double.parseDouble(lineaPais[6])));
					superficie.appendChild(doc.createTextNode(String.valueOf(Double.parseDouble(lineaPais[3]))));
					pais.appendChild(superficie);

					Element densidadPoblacion = doc.createElement("densidad_poblacion");
					densidadPoblacion.appendChild(doc.createTextNode(String.valueOf(
							Double.parseDouble(lineaPais[1])/Double.parseDouble(lineaPais[6]) ) ));
					pais.appendChild(densidadPoblacion);
					raiz.appendChild(pais);
				}else {
					primeraLinea=false;
				}
			}
			
			TransformerFactory tF = TransformerFactory.newInstance();
			Transformer t = tF.newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(doc), new StreamResult(new File("./src/main/resources/paises.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el documento csv");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error al leer el documento csv");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			System.out.println("Error al construir el documento DOM. Pasos previos");
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			System.out.println("Error al transformar DOM a fichero XML. Pasos previos");
			e.printStackTrace();
		} catch (TransformerException e) {
			System.out.println("Error al transformar DOM a fichero XML. Paso final");
			e.printStackTrace();
		}
	}

}
