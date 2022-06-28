package tasca5n1ex5;

import java.io.Serializable;

public class ClaseSerializable implements Serializable {
	private static final long serialVersionUID = 8799656478674716637L; // si alhora de deserialitzar no coincideix,
																		// llança InvalidClassException
	private String prueba = "Objecte des/serialitzat";

	public long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPrueba() {
		return prueba;
	}
}
