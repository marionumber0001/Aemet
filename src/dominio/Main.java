package dominio;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		File file = new File("W:/Descargas/Alfafar.xml");

		CheckXML xml = new CheckXML(file);

		ArrayList<Date> array = xml.LeerFechas();

		System.out.println(xml.LeerCotaNieve(array.get(1)));

	}

}
