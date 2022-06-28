package tasca5n2ex1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class Tasca5n2ex1 {
	public static void main(String[] args) {
		//".\\src\\tasca5n2ex1\\config.propierties";
		if(args.length == 0) {
        	System.out.println("Has d'introduir la ruta de l'arxiu propierties per linea de comandes. Es tancarà l'apliació");
            System.exit(0);
        }
		final String propiertiesPath = args[0];

		String[] paths = { "", "" };
		String directoryPath = "";
		String llistatArxiusPath = "";
		try {
			paths = loadConfig(propiertiesPath);
			directoryPath = paths[0];
			llistatArxiusPath = paths[1];

			File arxiuPare = new File(directoryPath);

			delete(llistatArxiusPath); // esborro l'arxiu d'anteriors execucions abans de començar
			guardarPerNomArxiusRecursiu(arxiuPare, "\n", directoryPath);

		} catch (NullPointerException e) {
			System.out.println("Ruta incorrecta");
		}

	}

	public static void guardarPerNomArxiusRecursiu(File directori, String separador, String rutaGuardat) {

		File[] arxius = directori.listFiles();
		List<File> arxiusList = Arrays.asList(arxius);

		Collections.sort(arxiusList, new ComparatorNombreArchivo());
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

	public static void delete(String path) {

		File aBorrar = new File(path);

		if (aBorrar.delete())
			System.out.println("El fichero ha sido borrado satisfactoriamente");
		else
			System.out.println("El fichero no puede ser borrado");

	}

	public static String[] loadConfig(String path) {
		String[] paths = { "", "" };
		Properties config = new Properties();
		InputStream configInput = null;
		try {
			configInput = new FileInputStream(path);
			config.load(configInput);
			paths[0] = config.getProperty("Directori");
			paths[1] = config.getProperty("FitxerTxt");
			// System.out.println(config.getProperty("Directori"));
			// System.out.println(config.getProperty("FitxerTxt"));
			return paths;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
