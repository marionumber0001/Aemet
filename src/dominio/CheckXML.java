package dominio;

import java.awt.image.RescaleOp;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.w3c.dom.*;

public class CheckXML {

	private Document doc;

	public CheckXML(File fileXML) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			this.doc = dBuilder.parse(fileXML);
			this.doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Date> LeerFechas() {

		ArrayList<Date> resultado = new ArrayList<Date>();

		NodeList list = this.doc.getElementsByTagName("prediccion");

		Element element = (Element) list.item(0);
		list = element.getElementsByTagName("dia");

		for (int i = 0; i < list.getLength(); i++) {
			element = (Element) list.item(i);
			resultado.add(ParseFecha(element.getAttribute("fecha"), "yyyy-MM-dd"));

		}
		return resultado;
	}

	public ArrayList<Double> LeerTemperatura(Date fecha) {
		return null;
	}

	private static Date ParseFecha(String fecha, String formato) {
		SimpleDateFormat format = new SimpleDateFormat(formato);
		Date fechaDate = null;
		try {
			fechaDate = format.parse(fecha);
		} catch (ParseException ex) {
			System.out.println(ex);
		}
		return fechaDate;
	}

}
