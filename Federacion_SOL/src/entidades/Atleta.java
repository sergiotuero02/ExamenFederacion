package entidades;

import java.util.Scanner;

import utils.Datos;

public class Atleta extends Participante {
	private long idAtleta;
	private float altura;
	private float peso;

	private DatosPersona persona;

	/**
	 * Método que le pide al usuario todos los campos para crear un objeto atleta
	 * completo
	 * 
	 * @return un objeto completo de tipo atleta
	 */
	public static Atleta nuevoAtleta() {
		Atleta ret = new Atleta();
		Scanner teclado = new Scanner(System.in);
		boolean idAtletaValido = false;
		do {
			System.out.println("Introduce el id del atleta: ");
			ret.setId(teclado.nextLong());

			if (ret.id > 0 && ret.id < 500) {
				idAtletaValido = true;
			} else
				idAtletaValido = false;
		} while (!idAtletaValido);

		boolean alturaValida = false;
		do {
			System.out.println("Introduce la altura del atleta: ");
			ret.setAltura(teclado.nextLong());

			if (ret.altura > 120 && ret.altura < 250) {
				alturaValida = true;
			} else
				alturaValida = false;
		} while (!alturaValida);

		boolean pesoValido = false;
		do {
			System.out.println("Introduce el id del atleta: ");
			ret.setPeso(teclado.nextLong());

			if (ret.peso > 30 && ret.peso < 180) {
				pesoValido = true;
			} else
				pesoValido = false;
		} while (!pesoValido);

		ret.persona = DatosPersona.nuevaPersona();
		return ret;
	}

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso) {
		super(id, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Atleta(long id, int dorsal, char calle, long idAtleta, float altura, float peso, DatosPersona dp) {
		super(idAtleta, dorsal, calle);
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idAtleta, float altura, float peso, DatosPersona dp) {
		super();
		this.idAtleta = idAtleta;
		this.altura = altura;
		this.peso = peso;
		this.persona = dp;
	}

	public Atleta(long idParticipante, Atleta a, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idAtleta = a.idAtleta;
		this.altura = a.altura;
		this.peso = a.peso;
		this.persona = Datos.buscarPersonaPorId(a.idAtleta);
	}

	public Atleta() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public long getId() {
		return idAtleta;
	}

	@Override
	public void setId(long id) {
		this.idAtleta = id;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	@Override
	public String toString() {
		return persona.getNombre() + "(" + persona.getNifnie() + ")" + " del año: " + persona.getFechaNac() + "\n"
				+ peso + "kgs." + altura + " m";
	}

}
