package entidades;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import utils.Datos;

public class ComparadorMedallas implements Comparator<Metal> {

	
	public int compare(Metal m1, Metal m2) {
		return (int) (m1.maximaPurezaAlcanzada() - m2.maximaPurezaAlcanzada());
	}

	public static void mostrarmedalla() {
		LinkedList<Metal> list = new LinkedList<Metal>();

		for (Oro o : Datos.OROS) {
			list.add(o);
		}
		for (Plata p : Datos.PLATAS) {
			list.add(p);
		}
		for (Bronce b : Datos.BRONCES) {
			list.add(b);
		}
		Collections.sort(list, new ComparadorMedallas());
		Iterator<Metal> it = list.iterator();
		while (it.hasNext()) {
			System.out.println("Pureza :" + it.next());

		}
	}

}