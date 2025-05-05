package modelo;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import modelo.clasesJAXB.Geografia_v1;
import modelo.clasesJAXB.Idioma_v1;
import modelo.clasesJAXB.Pais_v1;

public class MiManejador extends DefaultHandler {
	private Marshaller jM;
	private Geografia_v1 geografia;
	private Pais_v1 pais; 
	private boolean esHabitantes = false, esIdioma = false;
	private Set<String> idiomas = new HashSet<String>();
	
	@Override
	public void startDocument() throws SAXException {
		try {
			jM = JAXBContext.newInstance(Geografia_v1.class).createMarshaller();
			jM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			geografia = new Geografia_v1();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endDocument() throws SAXException {
		try {
			for(String idioma: idiomas) {
				Idioma_v1 idiomaObj = new Idioma_v1(idioma);
				geografia.getIdiomas().add(idiomaObj);
			}
			jM.marshal(geografia, new File("./src/main/resources/paises_JAXB.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("pais")) {
			pais = new Pais_v1();
			pais.setNombre(attributes.getValue(0));
		}else if (qName.equalsIgnoreCase("habitantes")) {
			esHabitantes = true;
		}else if (qName.equalsIgnoreCase("idioma")) {
			esIdioma = true;
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("habitantes")) {
			esHabitantes = false;
		}else if (qName.equalsIgnoreCase("idioma")){
			esIdioma = false;
		}else if (qName.equalsIgnoreCase("pais")){
			geografia.getPaises().add(pais);
		}
			
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (esHabitantes) {
			pais.setHabitantes(new String(ch, start, length));
		}else if (esIdioma) {
			idiomas.add(new String(ch, start, length));
		}
	}

}
