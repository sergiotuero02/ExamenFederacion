package entidades;

import java.time.format.DateTimeFormatter;

public class Palmares<T extends Metal, S extends Participante> {
	private long id;
	private T medalla;
	private S participante;
	private Prueba prueba;
	private String obsevaciones;

	public Palmares() {

	}

	// Constructor que recibe como parametros todos los campos de la clase palmares
	public Palmares(long id, T medalla, S participante, Prueba prueba, String observaciones) {
		this.id = id;
		this.medalla = medalla;
		this.participante = participante;
		this.prueba = prueba;
		this.obsevaciones = observaciones;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getMedalla() {
		return medalla;
	}

	public void setMedalla(T medalla) {
		this.medalla = medalla;
	}

	public S getParticipante() {
		return participante;
	}

	public void setParticipante(S participante) {
		this.participante = participante;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public String getObsevaciones() {
		return obsevaciones;
	}

	public void setObsevaciones(String obsevaciones) {
		this.obsevaciones = obsevaciones;
	}

	/*
	 * Metodo que muestra todos los datos del palmares y el participante, en función
	 * de si este es un atleta o un equipo
	 * 
	 * @return ret, un string con los datos del palmares
	 */
	public String MostrarPalmares() {
		String ret = "";
		Atleta a = null;
		Equipo eq = null;
		if (participante.getClass().equals((Atleta) a)) {
			String ret1 = (a.getPersona().toString());
			ret = " Id del palmarés" + this.id + " Datos medalla, " + this.medalla.toString() + " Prueba "
					+ this.prueba.getNombre() + " Fecha de la prueba "
					+ prueba.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " Lugar= "
					+ this.prueba.getLugar() + " Dorsal del participante " + this.participante.getDorsal()
					+ this.participante.getCalle() + "Datos persona " + ret1;

		}
		if (participante.getClass().equals((Equipo) eq)) {
			String ret2 = (a.getPersona().toString());
			ret = " Id del palmarés" + this.id + " Datos medalla, " + this.medalla.toString() + " Prueba "
					+ this.prueba.getNombre() + " Fecha de la prueba "
					+ prueba.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " Lugar= "
					+ this.prueba.getLugar() + " Dorsal del participante " + this.participante.getDorsal()
					+ this.participante.getCalle() + "Datos persona " + ret2;

		}
		return ret;
	}
}
