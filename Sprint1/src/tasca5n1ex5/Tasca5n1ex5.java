package tasca5n1ex5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Tasca5n1ex5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String directoryPath = ".\\src\\tasca5n1\\DirectoriPare";
		final String serialPath = directoryPath + "\\serial.ser"; // amb .dat it works too

		serializar(serialPath);
		desSerializar(serialPath);
	}

	public static void serializar(String path) {

		FileOutputStream fos = null;
		ObjectOutputStream salida = null;
		ClaseSerializable s;

		try {
			// Se crea el fichero
			fos = new FileOutputStream(path);
			salida = new ObjectOutputStream(fos);

			// Se crea el primer objeto
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
			s = (ClaseSerializable) entrada.readObject(); // es necessari el casting
			System.out.println(s.getSerialversionuid() + " " + s.getPrueba());

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Wrong path");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("ClaseSerializable not found");
		} catch (InvalidClassException e) {
			System.out.println(e.getMessage());
			System.out.println("SerialVersion no coincide y debe coincidir)");
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
