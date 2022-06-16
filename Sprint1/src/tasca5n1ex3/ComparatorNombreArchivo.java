package tasca5n1ex3;

import java.io.File;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public class ComparatorNombreArchivo implements Comparator<File> {

	@Override
	public int compare(File f1, File f2) {

		// TODO Auto-generated method stub
		Collator collatorPrimari = Collator.getInstance(new Locale("es"));
		collatorPrimari.setStrength(Collator.PRIMARY);
		return collatorPrimari.compare(f1.getName(), f2.getName());

	}

}
