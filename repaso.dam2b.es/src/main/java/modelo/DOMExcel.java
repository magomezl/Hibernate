package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DOMExcel {

	public static void main(String[] args) {

		try {
			DocumentBuilder dB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dB.parse(new FileInputStream(new File("./src/main/resources/paises2.xml")));

			//TODO coger el elemento raiz
			Element raiz = doc.getDocumentElement();
			// Abrir el excel lo lee línea a línea y añadir al documento un elemento pais al elemento raiz del documento dom 
			Workbook wb = new HSSFWorkbook(new FileInputStream(new File("./src/main/resources/paises2.xls")));;
			Sheet hoja =wb.getSheetAt(0);
			int numFila = 1;
			Row fila = hoja.getRow(numFila);
			while(fila!=null) {
				//crear el elemnto pais y añadirlo al raiz
				Element pais = doc.createElement("pais");
				pais.setAttribute("nombre", fila.getCell(0).getStringCellValue());
				
				Element habitantes = doc.createElement("habitantes");
				habitantes.appendChild(doc.createTextNode(String.valueOf(fila.getCell(1).getNumericCellValue())));
				pais.appendChild(habitantes);
				
				Element idiomasOficiales = doc.createElement("idiomas_oficiales");
				for(int i=2; i<=5; i++) {
					if (fila.getCell(i)!=null){
						if (!fila.getCell(i).getStringCellValue().isBlank()) {
							Element idiomaE = doc.createElement("idioma");
							idiomaE.appendChild(doc.createTextNode(fila.getCell(i).getStringCellValue()));
							idiomasOficiales.appendChild(idiomaE);
						}
					}
				}
				pais.appendChild(idiomasOficiales);
				
				Element superficie = doc.createElement("superficie");
				superficie.setAttribute("km_linea_costa", String.valueOf(fila.getCell(7).getNumericCellValue()));
				superficie.setAttribute("km2_agua", String.valueOf(fila.getCell(8).getNumericCellValue()));
				superficie.setAttribute("km2_tierra", String.valueOf(fila.getCell(9).getNumericCellValue()));
				superficie.appendChild(doc.createTextNode(String.valueOf(fila.getCell(8).getNumericCellValue())));
				pais.appendChild(superficie);
				
				Element densidadPoblacion = doc.createElement("densidad_poblacion");
				densidadPoblacion.appendChild(doc.createTextNode(String.valueOf(
						fila.getCell(1).getNumericCellValue()/fila.getCell(9).getNumericCellValue() ) ));
				pais.appendChild(densidadPoblacion);
				
				// Añadimos el elemento pais al elemento raíz paises
				raiz.appendChild(pais);
				fila = hoja.getRow(++numFila);
			}
			// Hago transformación del árbol dom que estña en memoria al fichero (persisto el árbol)
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(doc), new StreamResult(new File("./src/main/resources/paises2.xml")));
			


		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		

	}

}
