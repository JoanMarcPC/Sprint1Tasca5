package tasca5n3ex1;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tasca5n3ex1 {

	public static void main(String[] args) {

		final String DIRECTORY_PATH = ".\\src\\tasca5n3ex1"; // encriptara tot arxiu isRegular que contigui aquest
																// directori, si en aquest directori ja hi ha arxius ja
																// encriptats, entra en bucle infinit, no tinc clar com
																// solucionar-ho (despres d'executar, recordar borrar
																// els arxius encrypted/decrypted abans d'una nova
																// execució)
		try {

			// Files.list retorna Stream<Path> pero com faig .collect(Collectors.tolist) ho
			// tinc en un List<Path> de direccions, filtro pq nomes tingui els arxius que no
			// son carpetes

			List<Path> listaInput = Files.list(Paths.get(DIRECTORY_PATH)).filter(Files::isRegularFile)
					.collect(Collectors.toList());

			List<Path> listaEncrypt = new ArrayList<Path>();
			List<Path> listaDecrypt = new ArrayList<Path>();

			for (int i = 0; i < listaInput.size(); i++) {
				String path = listaInput.get(i).getFileName().toString();
				String dPath;
				path = path.substring(0, path.lastIndexOf("."));
				dPath = path;
				path += ".encrypted";
				dPath += ".decrypted";

				listaEncrypt.add(Path.of(listaInput.get(i).getParent().toString(), path));
				listaDecrypt.add(Path.of(listaInput.get(i).getParent().toString(), dPath));
				// System.out.println(listaEncrypt.toString());

			}
			for (int i = 0; i < listaInput.size(); i++) {
				Encriptar.givenFile_whenEncrypt_thenSuccess(listaInput.get(i), listaEncrypt.get(i),
						listaDecrypt.get(i));
			}
			System.out.println("Encriptació/desencriptació exitosa");

		} catch (FileNotFoundException e) {

			System.out.println("Error en path");
		} catch (Exception e) {
			System.out.println("ERROR");
			System.out.println(e.initCause(e));

		}
	}
}
