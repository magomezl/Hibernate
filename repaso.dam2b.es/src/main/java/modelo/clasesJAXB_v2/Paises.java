package modelo.clasesJAXB_v2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="paises")
public class Paises {
	private ArrayList<Pais> paises = new ArrayList<Pais>();
	
	public Paises() {
		super();
	}

	public Paises(ArrayList<Pais> paises) {
		super();
		this.paises = paises;
	}

	@XmlElement(name="pais")
	public ArrayList<Pais> getPaises() {
		return paises;
	}

	public void setPaises(ArrayList<Pais> paises) {
		this.paises = paises;
	}
}
