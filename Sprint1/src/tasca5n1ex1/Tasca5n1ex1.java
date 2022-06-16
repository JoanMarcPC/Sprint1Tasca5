package tasca5n1ex1;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tasca5n1.ComparatorNombreArchivo;

public class Tasca5n1ex1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DIRECTORY_PATH = ".\\src\\tasca5n1\\DirectoriPare";
		File arxiuPare = new File(DIRECTORY_PATH);
		List<File> llistaArxius = ordenarArxius(arxiuPare);
		for (File file : llistaArxius) {
			System.out.println(file.getName());
		}

	}

	public static List<File> ordenarArxius(File directori) {
		if (directori.isDirectory()) {
			File[] arxius = directori.listFiles();
			List<File> arxiusList = Arrays.asList(arxius);
			Collections.sort(arxiusList, new ComparatorNombreArchivo());

			return arxiusList;
		} else {
			System.out.println("En la ruta indicada no hi ha un directori");
			return null;
		}
	}

}
