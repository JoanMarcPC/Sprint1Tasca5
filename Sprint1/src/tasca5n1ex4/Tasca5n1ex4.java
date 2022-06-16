package tasca5n1ex4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tasca5n1ex4 {
	public static void main(String[] args) {
		llegirImostrar(
				"C:\\Users\\formacio\\git\\Itacademy\\Sprint1\\src\\tasca5n1ex1\\DirectoriPare\\llistatArxius.txt");
	}

	public static void llegirImostrar(String path) {
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
}