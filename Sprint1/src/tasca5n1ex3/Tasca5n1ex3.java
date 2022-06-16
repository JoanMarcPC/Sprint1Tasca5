package tasca5n1ex3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Tasca5n1ex3 {
	public static void main(String[] args) {
		final String directoryPath = ".\\src\\tasca5n1\\DirectoriPare";
		final String llistatArxiusPath = directoryPath + "\\llistatArxius.txt";
		File arxiuPare = new File(directoryPath);

		delete(llistatArxiusPath); // esborro l'anterior arxiu abans de començar
		try {
			guardarPerNomArxiusRecursiu(arxiuPare, "\n", directoryPath);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			System.out.println("El archivo/directorio no existe");
		}

	}

	public static void guardarPerNomArxiusRecursiu(File directori, String separador, String rutaGuardat)
			throws NullPointerException {
		File[] arxius = directori.listFiles();
		List<File> arxiusList = Arrays.asList(arxius);

		Collections.sort(arxiusList, new ComparatorNombreArchivo());
		if (!arxiusList.isEmpty()) {

			for (int i = 0; i < arxiusList.size(); i++) {
				Date d = new Date(arxiusList.get(i).lastModified());
				if (arxiusList.get(i).isFile()) {

					// System.out.println(separador + arxius[i].getName() + "(F) "+ d);
					write(rutaGuardat, separador + arxiusList.get(i).getName() + "(F) " + d, true);// append true per no
																									// sobreescriure

				} else {

					// System.out.println(separador + arxius[i].getName() + "(D) " + d);
					write(rutaGuardat, separador + arxiusList.get(i).getName() + "(D) " + d, true); // append true per
																									// no sobreescriure
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

	public static void delete(String path) {
		File aBorrar = new File(path);
		if (aBorrar.delete())
			System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
			System.out.println("El fichero no puede ser borrado  (no existe)");

	}
}
