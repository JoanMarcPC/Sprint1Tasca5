package tasca5n1ex2;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Tasca5n1ex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// directoryPath = ".\\src\\tasca5n1\\DirectoriPare";
		File arxiuPare;
		// Check if a command line argument exists
        if(args.length == 0) {
        	System.out.println("Has d'introduir la ruta per linea de comandes. Es tancarà l'apliació");
            System.exit(0);
        }
        
        arxiuPare =new File(args[0]);

		try {
			mostrarPerNomArxiusRecursiu(arxiuPare, "");
		} catch (NullPointerException e) {

			System.out.println("El Directorio no existe");
			System.out.println(e.getMessage());
		}
	}

	public static void mostrarPerNomArxiusRecursiu(File directori, String separador) throws NullPointerException {

		File[] arxius = directori.listFiles();

		List<File> arxiusList = Arrays.asList(arxius);

		Collections.sort(arxiusList, new ComparatorNombreArchivo());
		if (!arxiusList.isEmpty()) {
			for (int i = 0; i < arxiusList.size(); i++) {
				Date d = new Date(arxiusList.get(i).lastModified());

				if (arxiusList.get(i).isFile()) {

					System.out.println(separador + arxiusList.get(i).getName() + "(F) " + d);

				} else {

					System.out.println(separador + arxiusList.get(i).getName() + "(D) " + d);
					String nouSeparador = separador + "\t"; // afegeixo tab al separador per als arxius seguents (pq
															// pertanyen a aquest directori
					mostrarPerNomArxiusRecursiu(arxiusList.get(i), nouSeparador);

				}
			}
		}
	}

}
