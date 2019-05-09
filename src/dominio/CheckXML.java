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
		NodeList lista = this.doc.getElementsByTagName("prediccion");
		Element element = (Element) lista.item(0);
		lista = element.getElementsByTagName("dia");

		for (int i = 0; i < lista.getLength(); i++) {
			element = (Element) lista.item(i);
			resultado.add(ParseFecha(element.getAttribute("fecha"), "yyyy-MM-dd"));
		}
		return resultado;
	}

	public String LeerTemperatura(Date fecha) {
		Element element = LeerDia(fecha);

		if (LeerDia(fecha) == null)
			return null;

		NodeList lista = element.getElementsByTagName("temperatura");
		element = (Element) lista.item(0);

		String maximaFM = element.getElementsByTagName("maxima").item(0).getTextContent();
		String minimaFM = element.getElementsByTagName("minima").item(0).getTextContent();

		return maximaFM + " / " + minimaFM;

	}

	public String LeerChanceOfPrecipitation(Date datum) {
		Element element = LeerDia(datum);

		if (LeerDia(datum) == null)
			return null;

		return element.getElementsByTagName("prob_precipitacion").item(0).getTextContent();

	}

	public String LeerEstadoCielo(Date datum) {
		Element element = LeerDia(datum);

		if (LeerDia(datum) == null)
			return null;

		return ((Element) element.getElementsByTagName("estado_cielo").item(0)).getAttribute("descripcion");
	}

	public String LeerCotaNieve(Date datum) {
		Element element = LeerDia(datum);

		if (LeerDia(datum) == null)
			return null;

		return element.getElementsByTagName("cota_nieve_prov").item(0).getTextContent();
	}

	private Element LeerDia(Date dia) {
		NodeList lista = this.doc.getElementsByTagName("prediccion");
		Element element = (Element) lista.item(0);
		lista = element.getElementsByTagName("dia");

		boolean find = false;
		for (int i = 0; i < lista.getLength(); i++) {
			element = (Element) lista.item(i);

			if ((ParseFecha(element.getAttribute("fecha"), "yyyy-MM-dd")).equals(dia)) {
				find = true;
				break;
			}
		}

		if (!find)
			return null;

		return element;
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
