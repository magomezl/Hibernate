package modelo.clasesJAXB_v2;

import javax.xml.bind.annotation.XmlValue;

public class Idioma {
	private String idioma;

	public Idioma() {
	}

	public Idioma(String idioma) {
		this.idioma = idioma;
	}
	@XmlValue()
	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
}
