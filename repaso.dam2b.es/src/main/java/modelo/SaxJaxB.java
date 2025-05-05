package modelo;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SaxJaxB {

	public static void main(String[] args) {
		
		
		try {
//			SAXParserFactory sPF = SAXParserFactory.newInstance();
//			SAXParser sP = sPF.newSAXParser();
			
			SAXParser sP = SAXParserFactory.newInstance().newSAXParser();
			
			MiManejador gestor = new MiManejador();
			InputSource fileXML = new InputSource("./src/main/resources/paises.xml");
			sP.parse(fileXML, gestor);
			
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
