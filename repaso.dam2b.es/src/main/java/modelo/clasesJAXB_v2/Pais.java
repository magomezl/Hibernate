package modelo.clasesJAXB_v2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder= {"habitantes", "idiomas", "superficie", "densidad_poblacion"})
public class Pais {
	
	private String nombre;
	private double habitantes;
	private ArrayList<Idioma> idiomas = new ArrayList<Idioma>();
	private Superficie superficie;
	private double densidad_poblacion;
	
	public Pais() {
		super();
	}
	public Pais(String nombre, double habitantes, ArrayList<Idioma> idiomas, Superficie superficie,
			double densidad) {
		super();
		this.nombre = nombre;
		this.habitantes = habitantes;
		this.idiomas = idiomas;
		this.superficie = superficie;
		this.densidad_poblacion = densidad;
	}
	@XmlAttribute()
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@XmlElement()
	public double getHabitantes() {
		return habitantes;
	}
	public void setHabitantes(double habitantes) {
		this.habitantes = habitantes;
	}
	@XmlElementWrapper(name="idiomas_oficiales")
	@XmlElement(name="idioma")
	public ArrayList<Idioma> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(ArrayList<Idioma> idiomas) {
		this.idiomas = idiomas;
	}
	
	@XmlElement()
	public Superficie getSuperficie() {
		return superficie;
	}
	public void setSuperficie(Superficie superficie) {
		this.superficie = superficie;
	}
	@XmlElement(name="densidad_poblacion")
	public double getDensidad_poblacion() {
		return densidad_poblacion;
	}
	public void setDensidad_poblacion(double densidad) {
		this.densidad_poblacion = densidad;
	}
	@Override
	public String toString() {
		return "Pais_v2 [nombre=" + nombre + ", habitantes=" + habitantes + ", idiomas=" + idiomas + ", superficie="
				+ superficie + ", densidad=" + densidad_poblacion + "]";
	}
	
	


}
