package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import modelo.clasesJAXB.Geografia_v1;
import modelo.clasesJAXB_v2.Idioma;
import modelo.clasesJAXB_v2.Pais;
import modelo.clasesJAXB_v2.Paises;
import modelo.clasesJAXB_v2.Superficie;

public class ExcelJaxB {

	public static void main(String[] args) {
		try {
			JAXBContext jC = JAXBContext.newInstance(Paises.class);
			Unmarshaller jUM = jC.createUnmarshaller();
			
			Paises paises = (Paises) jUM.unmarshal(new File("./src/main/resources/paises.xml"));
//			for(Pais pais: paises.getPaises()) {
//				System.out.println(pais);
//			}
//			
			//Leer el excel y añadir sus datos al javabean
			Workbook wb = new HSSFWorkbook(new FileInputStream(new File("./src/main/resources/paises.xls")));
			Sheet hoja = wb.getSheetAt(0);
			int numFila = 1;
			Row fila = hoja.getRow(numFila);
			
			while(fila!=null) {
				Pais pais = new Pais();
				pais.setNombre(fila.getCell(0).getStringCellValue());
				pais.setHabitantes(fila.getCell(1).getNumericCellValue());
				Superficie superficie = new Superficie(
						fila.getCell(6).getNumericCellValue(),
						fila.getCell(7).getNumericCellValue(),
						fila.getCell(8).getNumericCellValue(),
						fila.getCell(9).getNumericCellValue());
				pais.setSuperficie(superficie);
				pais.setDensidad_poblacion(fila.getCell(1).getNumericCellValue()/fila.getCell(9).getNumericCellValue());
				 
				for(int i=2; i<=5; i++) {
					if (fila.getCell(i)!=null){
						if (!fila.getCell(i).getStringCellValue().isBlank()) {
							Idioma idioma = new Idioma(fila.getCell(i).getStringCellValue());
							pais.getIdiomas().add(idioma);
						}
					}
				}
				fila = hoja.getRow(++numFila);
				paises.getPaises().add(pais);
			}
	
			Marshaller jM = jC.createMarshaller();
			jM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jM.marshal(paises, new File("./src/main/resources/paises2.xml"));

			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
