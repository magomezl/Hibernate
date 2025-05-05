package modelo.clasesJAXB_v2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(propOrder= {"kmagua", "kmtierra", "kmlineacosta"})
public class Superficie {
	private double superficieV;
	private double kmlineacosta;
	private double kmagua;
	private double kmtierra;
	
	
	
	public Superficie() {
		super();
	}



	public Superficie(double superficieV, double kmlineacosta, double kmagua, double kmtierra) {
		super();
		this.superficieV = superficieV;
		this.kmlineacosta = kmlineacosta;
		this.kmagua = kmagua;
		this.kmtierra = kmtierra;
	}


	@XmlValue()
	public double getSuperficieV() {
		return superficieV;
	}



	public void setSuperficieV(double superficieV) {
		this.superficieV = superficieV;
	}


	@XmlAttribute(name="km_linea_costa")
	public double getKmlineacosta() {
		return kmlineacosta;
	}



	public void setKmlineacosta(double kmlineacosta) {
		this.kmlineacosta = kmlineacosta;
	}


	@XmlAttribute(name="km2_agua")
	public double getKmagua() {
		return kmagua;
	}



	public void setKmagua(double kmagua) {
		this.kmagua = kmagua;
	}


	@XmlAttribute(name="km2_tierra")
	public double getKmtierra() {
		return kmtierra;
	}



	public void setKmtierra(double kmtierra) {
		this.kmtierra = kmtierra;
	}



	@Override
	public String toString() {
		return "Superficie [superficieV=" + superficieV + ", kmlineacosta=" + kmlineacosta + ", kmagua=" + kmagua
				+ ", kmtierra=" + kmtierra + "]";
	}
	
	
	
	
}
