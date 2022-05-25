package tasca5n1ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Tasca5n1ex1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Per borrar el llistat d'arxius abans de començar
		ListarDirectori.delete(
				"C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca5n1ex1\\DirectoriPare\\llistatArxius.txt");

		File arxiuPare = new File("C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca5n1ex1\\DirectoriPare");
		//Exercici2 i 3
		try {
			// ListarDirectori.mostrarPerNomArxiusRecursiu(arxiuPare,"");
			ListarDirectori.guardarPerNomArxiusRecursiu(arxiuPare, "\n",
					"C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca5n1ex1\\DirectoriPare");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		// Exercici4
		ListarDirectori.read(
				"C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca5n1ex1\\DirectoriPare\\llistatArxius.txt");
		// Exercici 5
		serializar("C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca5n1ex1\\DirectoriPare\\serial.dat");
		desSerializar("C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca5n1ex1\\DirectoriPare\\serial.dat");

	}

	public static void serializar(String path) {

		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		ClaseSerializable s;

		try {
			// Se crea el fichero
			fos = new FileOutputStream(path);
			salida = new ObjectOutputStream(fos);

			// Se crea el primer objeto Persona
			s = new ClaseSerializable();

			// Se escribe el objeto en el fichero
			salida.writeObject(s);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (salida != null) {
					salida.close();
				}
			} catch (IOException e) {
				System.out.println("Fallo al cerrar" + e.getMessage());
			}
		}

	}

	public static void desSerializar(String path) {
		FileInputStream fis = null;
		ObjectInputStream entrada = null;
		ClaseSerializable s;

		try {

			fis = new FileInputStream(path);
			entrada = new ObjectInputStream(fis);
			s = (ClaseSerializable) entrada.readObject(); // es necesario el casting
			System.out.println(s.getSerialversionuid() + " " + s.getPrueba());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (entrada != null) {
					entrada.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
