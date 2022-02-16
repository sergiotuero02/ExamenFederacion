package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import entidades.*;
import entidades.Categoria;

/**
 *
 * @author luis
 */
public class Utilidades {
//Examen 7 ejercicio 2
	/*
	 * Método que exporta todos los colegiados a un fichero en función de su
	 * categoría
	 * 
	 */
	public static void exportarColegiados() {

		try {
			for (Colegiado c : Datos.COLEGIADOS) {
				if (c.getCategoria() == Categoria.JUNIOR) {
					String path = "colegiadoJunior.dat";
					FileOutputStream fos = new FileOutputStream(path, false);
					ObjectOutputStream escritor = new ObjectOutputStream(fos);
					escritor.writeObject((Colegiado) c);

					escritor.flush();
					escritor.close();
				}
				if (c.getCategoria() == Categoria.SENIOR) {
					String path = "colegiadoSenior.dat";
					FileOutputStream fos = new FileOutputStream(path, false);

					ObjectOutputStream escritor = new ObjectOutputStream(fos);
					escritor.writeObject((Colegiado) c);
					escritor.flush();
					escritor.close();
				}
				if (c.getCategoria() == Categoria.ESPECIAL) {
					String path = "colegiadoEspecial.dat";
					FileOutputStream fos = new FileOutputStream(path, false);
					ObjectOutputStream escritor = new ObjectOutputStream(fos);
					escritor.writeObject((Colegiado) c);

					escritor.flush();
					escritor.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Excepcion FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Excepcion IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception" + e.getMessage());
		}
	}

	// Examen 7 ejercicio 3
	/*
	 * Método que importa un documento manager, determina si el mánager tiene un
	 * equipo asignado y devuelve una cadena de caracteres con datos del manager y
	 * del equipò
	 * 
	 */
	public static String DataManagerEquipo() {
		String ret = "";
		File fichero = new File("manager.txt");
		FileReader lector = null;
		BufferedReader buffer = null;
		try {
			try {
				lector = new FileReader(fichero);
				buffer = new BufferedReader(lector);
				String linea;
				while ((linea = buffer.readLine()) != null) {
					String[] datos = linea.split("\\|");
					String idPersona = datos[0];
					String nombreManager = datos[1];
					String documentacionManager = datos[2];
					String fechaNacManager = datos[3];
					String telefonoPersona = datos[4];
					String idManager = datos[5];
					String telefonoManager = datos[6];
					String direccionManager = datos[7];

					for (Equipo eq : Datos.EQUIPOS) {
						if (Long.valueOf(idManager) == eq.getManager().getId()) {
							eq.getAtletas();
							ret = ("D./Dña. " + nombreManager + " con NIF:NIE " + documentacionManager + " nacido el "
									+ fechaNacManager + " representa al equipo " + eq.getNombre() + " de id"
									+ eq.getId() + " durante el año " + eq.getAnioinscripcion()
									+ ", el cual está formado por los siguientes atletas: \t"
									+ eq.getAtletas().toString() + "\n");
						}

					}

				}

			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (lector != null) {
					lector.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}

		return ret;
	}

	/**
	 * Función que pide un float al usuario y valida que lo que el usuario introduce
	 * es un float en caso de que el usuario no introduzca un float vuelve a pedirle
	 * que introduza un valor
	 * 
	 * @return true si el usuario introduce un floa; false si el usuario no
	 *         introduce un float
	 */
	public static float leerFloat() {

		float ret = 0.0f;
		boolean valido = false;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println("Introduzca un float ejemplo 0.0f");

			try {
				ret = teclado.nextFloat();
				valido = true;
			} catch (InputMismatchException ime) {
				System.out.println("No ha introducido un float, asegurese de que añade un a 'f' al final");
				valido = false;
			}
		} while (!valido);
		return ret;
	}

	/**
	 * Función que pide al usuario que introduzca 's' o 'S' para Sí o 'n' o 'N' para
	 * No y devuelve true o false en cada caso. Si el usuario no introduce ni 's' ni
	 * 'S' ni 'n' ni 'N' entonces avisa al usuario y le vuelve a pedir a que lo
	 * introduzca de nuevo.
	 *
	 * @return true si el usuario introduce 's' o 'S'; false si el usuario introduce
	 *         'n' o 'N'
	 */
	public static boolean leerBoolean() {
		boolean ret;
		Scanner in;
		char resp;
		do {
			System.out.println("Pulse s para Sí o n para No");
			in = new Scanner(System.in, "ISO-8859-1");
			resp = in.nextLine().charAt(0);
			if (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N') {
				System.out.println("Valor introducido incorrecto.");
			}
		} while (resp != 's' && resp != 'S' && resp != 'n' && resp != 'N');
		if (resp == 's' || resp != 'S') {
			ret = true;
		} else {
			ret = false;
		}
		return ret;
	}

	/**
	 * Función que pide al usuario que introduzca un valor decimal por la entrada
	 * estándar. Si el formato introducido no es correcto, avisa al usuario y le
	 * vuelve a pedir que lo introduzca de nuevo.
	 *
	 * @return el valor double introducido por el usuario
	 */
	public static double leerDouble() {
		double ret = 0.0;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor decimal (xx.xx)");
			in = new Scanner(System.in, "ISO-8859-1");
			try {
				ret = in.nextDouble();
				correcto = true;
			} catch (InputMismatchException ime) {
				System.out.println("Formato introducido incorrecto.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/**
	 * Función que pide al usuario que introduce un valor para una fecha a partir de
	 * 3 enteros para el día, mes y año respectivamente. Si los valores introducidos
	 * no producen una fecha correcta, avisa al usuario y le pide que los introduzca
	 * de nuevo. Si no lo consigue, devolverá null
	 *
	 * @return una fecha de la clase java.time.LocalDate o null si hay error
	 */
	public static java.time.LocalDate leerFecha() {
		LocalDate ret = null;
		int dia, mes, anio;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor para el día (1...31)");
			in = new Scanner(System.in, "ISO-8859-1");
			dia = in.nextInt();
			System.out.println("Introduzca un valor para el mes (1...12)");
			in = new Scanner(System.in, "ISO-8859-1");
			mes = in.nextInt();
			System.out.println("Introduzca un valor para el año");
			in = new Scanner(System.in, "ISO-8859-1");
			anio = in.nextInt();

			try {
				ret = LocalDate.of(anio, mes, dia);
				correcto = true;
			} catch (Exception e) {
				System.out.println("Fecha introducida incorrecta.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/**
	 * Función que pide al usuario que introduce un valor para una fecha a partir de
	 * 3 enteros para el día, mes y año respectivamente Y una hora a partir de ptrps
	 * 3 valores para la hora, minutos y segundos. Si los valores introducidos no
	 * producen una fecha u hora correctas, avisa al usuario y le pide que los
	 * introduzca de nuevo. Si no lo consigue, devolverá null
	 *
	 * @return una fecha-hora de la clase java.time.LocalDateTime o null si hay
	 *         error
	 */
	public static java.time.LocalDateTime leerFechaHora() {
		LocalDateTime ret = null;
		int dia, mes, anio;
		int hora, min, seg;
		boolean correcto = false;
		Scanner in;
		do {
			System.out.println("Introduzca un valor para el día (1...31)");
			in = new Scanner(System.in, "ISO-8859-1");
			dia = in.nextInt();
			System.out.println("Introduzca un valor para el mes (1...12)");
			in = new Scanner(System.in, "ISO-8859-1");
			mes = in.nextInt();
			System.out.println("Introduzca un valor para el año");
			in = new Scanner(System.in, "ISO-8859-1");
			anio = in.nextInt();
			System.out.println("Introduzca un valor para la hora del día (0...23)");
			in = new Scanner(System.in, "ISO-8859-1");
			hora = in.nextInt();
			System.out.println("Introduzca un valor para los minutos (0...59)");
			in = new Scanner(System.in, "ISO-8859-1");
			min = in.nextInt();
			System.out.println("Introduzca un valor para los segundos (0...59)");
			in = new Scanner(System.in, "ISO-8859-1");
			seg = in.nextInt();

			try {
				ret = LocalDateTime.of(anio, mes, dia, hora, min, seg);
				correcto = true;
			} catch (Exception e) {
				System.out.println("Fecha-hora introducida incorrecta.");
				correcto = false;
			}
		} while (!correcto);
		return ret;
	}

	/**
	 * Función que quita los espacios en blanco del comienzo y del final de una
	 * cadena de caracteres que se pasa como parámetro y, además, sustituye todas
	 * las vocales que tengan tilde por la correspondiente sin tilde, devolviendo la
	 * cadena resultante
	 * 
	 * @param s cadena original
	 * @return cadena original sin espacios en blanco al comienzo y final de la
	 *         cadena y sin vocales con tildes
	 */
	public static String quitarEspaciosTildes(String s) {
		String ret = s.trim();
		return ret.replace('á', 'a').replace('é', 'e').replace('í', 'i').replace('ó', 'o').replace('ú', 'u')
				.replace('Á', 'A').replace('É', 'E').replace('Í', 'I').replace('Ó', 'O').replace('Ú', 'U');
	}

	public static String removeDiacriticalMarks(String string) {
		// Form.NFC acepta ñ y distingue las tildes en español
		return Normalizer.normalize(string, Form.NFC).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	/**
	 * Valida que una cadena de caracteres contiene dígitos únicamente
	 * 
	 * @param tfn cadena con el telefono a validar
	 * @return true si es un telefono válido o false en caso contrario
	 */
	public static boolean validarTelefono(String tfn) {
		return tfn.trim().chars().allMatch(Character::isDigit);
	}

	/**
	 * Valida que una cadena de caracteres contiene letras o espacios únicamente,
	 * longitud entre 3 y 50 caractreres
	 * 
	 * @param nombre cadena con el nombre a validar
	 * @return true si es un nombre válido o false en caso contrario
	 */
	public static boolean validarNombre(String nombre) {
		// regEx general para cadena de caracteres con longitud entre 1 y 50 caracteres,
		// aceptando dígitos, letras MAYUS y minúsculas, con tildes, diréresis y
		// diferentes símbolos especiales
		// Pattern patron = Pattern.compile("[
		// 0-9A-Za-zñÑáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ¡!¿?@#$%()=+-€/.,]{1,50}");
		Pattern patron = Pattern.compile("[ A-Za-zñÑáéíóúÁÉÍÓÚäëïöüÄËÏÖÜ-]{3,50}");
		Matcher comprobacion = patron.matcher(nombre);
		return comprobacion.matches();//
	}
}
