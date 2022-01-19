package validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aeat.valida.Validador;

import entidades.NIE;


public class Validacion {

	public static boolean validarId(long id) {
		boolean idValido = false;
		do {
		System.out.println("Introduce el id del manager:");
		
		if(id>0 && id<500)
			idValido = true;
		else
			idValido = false;
		}while(!idValido);
		return idValido;
				
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
