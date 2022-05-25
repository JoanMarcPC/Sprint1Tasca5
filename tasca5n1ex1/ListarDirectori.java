package tasca5n1ex1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class ListarDirectori {

	//Exercici1
	public static ArrayList<String> ordenarArxius(File directori) {
		if (directori.isDirectory()) {
			String arrayArxius[] = directori.list();
			
			ArrayList<String> llistaDirectori = new ArrayList<String>(Arrays.asList(arrayArxius));

			Collator primaryCollator = Collator.getInstance(new Locale("es"));
			primaryCollator.setStrength(Collator.PRIMARY);
			llistaDirectori.sort(primaryCollator);
			for (String nom : llistaDirectori) {
				System.out.println(nom);
			}
			return llistaDirectori;

		} else {
			System.out.println("En la ruta indicada no hi ha un directori");
			return null;
		}
	}

	public static void mostrarPerNomArxiusRecursiu(File directori, String separador) {
		File[] arxius = directori.listFiles();
        //List<File> arxiusList =  Arrays.asList(arxius);
		if (arxius != null) {
			/* No soc capaç d'incorporar l'ordenació en Español
			 * String arxiusOrdenats[] =directori.list(); ArrayList<String> llistaDirectori
			 * = new ArrayList<String>(Arrays.asList(arxiusOrdenats)); Collator
			 * primaryCollator = Collator.getInstance(new Locale("es"));
			 * primaryCollator.setStrength(Collator.PRIMARY);
			 * llistaDirectori.sort(primaryCollator);
			 * 
			 * for(String nomArxiu:llistaDirectori ) { System.out.println(separador +
			 * nomArxiu);
			 */

			for (int i = 0; i < arxius.length; i++) {
				Date d = new Date(arxius[i].lastModified());
				if (arxius[i].isFile()) {

					System.out.println(separador + arxius[i].getName() + "(F) " + d);

				} else {

					System.out.println(separador + arxius[i].getName() + "(D) " + d);
					String nouSeparador = separador + "\t";
					mostrarPerNomArxiusRecursiu(arxius[i], nouSeparador);

					/*
					 * Una altra opcio per format les dates
					 * String pattern = "yyyy-MM-dd hh:mm aa"; SimpleDateFormat simpleDateFormat =
					 * new SimpleDateFormat(pattern);
					 * 
					 * Date lastModifiedDate = new Date( lastModified );
					 * 
					 * System.out.println( "The file " + file + " was last modified on " +
					 * simpleDateFormat.format( lastModifiedDate ) );
					 */
				}
			}
		}
	}

	public static void guardarPerNomArxiusRecursiu(File directori, String separador, String rutaGuardat) {
		File[] arxius = directori.listFiles();

		if (arxius != null) {

			for (int i = 0; i < arxius.length; i++) {
				Date d = new Date(arxius[i].lastModified());
				if (arxius[i].isFile()) {

					// System.out.println(separador + arxius[i].getName() + "(F) "+ d);
					write(rutaGuardat, separador + arxius[i].getName() + "(F) " + d, true);

				} else {

					// System.out.println(separador + arxius[i].getName() + "(D) " + d);
					write(rutaGuardat, separador + arxius[i].getName() + "(D) " + d, true);
					String nouSeparador = separador + "\t";
					guardarPerNomArxiusRecursiu(arxius[i], nouSeparador, rutaGuardat);

				}
			}
		}
	}

	public static void write(String directori, String aEscriure, boolean append) {
		BufferedWriter bf;
		try {
			bf = new BufferedWriter(new FileWriter(directori + "\\llistatArxius.txt", append));
			bf.write(aEscriure);
			bf.close();
		} catch (IOException e) {
			System.out.println("Buffer problem (Write)");
		}
	}
 
	
	public static void read(String path) {
		String acumulador = "";
		BufferedReader bf;
		try {

			bf = new BufferedReader(new FileReader(path));
			while (acumulador != null) {
				acumulador = "";
				acumulador = bf.readLine();
				if (acumulador != null) {
					System.out.println(acumulador);
				}
			}

		} catch (IOException e) {
			System.out.println("Buffer problem (Read)");
		}
	}

	public static void delete(String path) {
		File aBorrar = new File(path);
		if (aBorrar.delete())
			System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
			System.out.println("El fichero no puede ser borrado");

	}

}
