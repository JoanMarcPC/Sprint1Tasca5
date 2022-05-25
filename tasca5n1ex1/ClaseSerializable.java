package tasca5n1ex1;

import java.io.Serializable;

public class ClaseSerializable implements Serializable{
	private static final long serialVersionUID = 8799656478674716638L;
	private static  final String prueba = "prueba";
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getPrueba() {
		return prueba;
	}
}
