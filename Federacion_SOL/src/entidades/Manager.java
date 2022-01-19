package entidades;

import java.util.Scanner;

import utils.Datos;
import validaciones.Validacion;

public class Manager {
	private long id;
	private String telefono;
	private String direccion;

	private DatosPersona persona;

	public static Manager nuevoManager() {
		Manager ret = new Manager();
		Scanner teclado = new Scanner(System.in);
		
		boolean idValido = false;
		do {
		System.out.println("Introduce el id del manager: ");
		ret.setId(teclado.nextLong());
		Validacion.validarId(ret.id);
		if(ret.id>0 && ret.id<500)
			idValido = true;
		else
			idValido = false;
		}while(!idValido);
		
		System.out.println("Introduce el teléfono del manager: ");
		ret.setTelefono(teclado.next());
		Validacion.validarTelefono(ret.telefono);
		
		boolean direccionValida = false;
		do {
		System.out.println("Introduce la dirección del manager: ");
		ret.setDireccion(teclado.next());
		if(ret.direccion.length()>0 && ret.direccion.length()<100)
			direccionValida = true;
		else
			direccionValida = false;
		}while(!direccionValida);
		ret.persona = DatosPersona.nuevaPersona();
		return ret;
		
		
		
	}
	
	public Manager(long id, String telefono, String direccion) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Manager(long id, String telefono, String direccion, DatosPersona dp) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = dp;
	}

	public Manager() {
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	@Override
	public String toString() {
		return id + " " + persona.getNombre() + "(" + persona.getNifnie() + ")" + "del año" +persona.getFechaNac()+" tlfno1: "+telefono+" tlfno2: "+persona.getTelefono()
				;
	}

}
