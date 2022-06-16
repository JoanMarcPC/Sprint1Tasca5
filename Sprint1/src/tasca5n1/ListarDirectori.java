package tasca5n1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListarDirectori {

	// Exercici1
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
		List<File> arxiusList = Arrays.asList(arxius);
		
		Collections.sort(arxiusList,new ComparatorNombreArchivo());
		if (!arxiusList.isEmpty()) {
			for (int i = 0; i < arxiusList.size(); i++) {
				Date d = new Date(arxiusList.get(i).lastModified());
	
				if (arxiusList.get(i).isFile()) {

					System.out.println(separador + arxiusList.get(i).getName() + "(F) " + d);

				} else {

					System.out.println(separador + arxiusList.get(i).getName() + "(D) " + d);
					String nouSeparador = separador + "\t"; // si pertany a un directori afegeixo tabulador al separador
					mostrarPerNomArxiusRecursiu(arxiusList.get(i), nouSeparador);

				}
			}
		}
	}

	public static void guardarPerNomArxiusRecursiu(File directori, String separador, String rutaGuardat) {
		File[] arxius = directori.listFiles();
		List<File> arxiusList = Arrays.asList(arxius);
		
		Collections.sort(arxiusList,new ComparatorNombreArchivo());
		if (!arxiusList.isEmpty()) {

			for (int i = 0; i < arxiusList.size(); i++) {
				Date d = new Date(arxiusList.get(i).lastModified());
				if (arxiusList.get(i).isFile()) {

					// System.out.println(separador + arxius[i].getName() + "(F) "+ d);
					write(rutaGuardat, separador + arxiusList.get(i).getName() + "(F) " + d, true);

				} else {

					// System.out.println(separador + arxius[i].getName() + "(D) " + d);
					write(rutaGuardat, separador + arxiusList.get(i).getName() + "(D) " + d, true);
					String nouSeparador = separador + "\t";
					guardarPerNomArxiusRecursiu(arxiusList.get(i), nouSeparador, rutaGuardat);

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
