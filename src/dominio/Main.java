package dominio;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		File file = new File("W:/Descargas/Alfafar.xml");

		CheckXML xml = new CheckXML(file);

		ArrayList<Date> array = xml.LeerFechas();
		
		Iterator it = array.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
	}

}
