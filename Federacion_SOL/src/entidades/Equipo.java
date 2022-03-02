package entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import utils.Datos;
import utils.Utilidades;

public class Equipo extends Participante {
	private long idEquipo;
	private int anioinscripcion;
	private Manager manager;
	private Atleta[] atletas;
	private String nombre;

	public static Equipo nuevoEquipo() {
		Equipo ret = null;
		Scanner teclado = new Scanner(System.in);
		long idEquipo = -1;
		int anioinscripcion = -1;
		Manager manager = null;
		String nombre = "";
		boolean correccion = true;

		do {
			boolean valido = false;
			do {
				System.out.println("Introduce el id del equipo (>0 y <1000");
				idEquipo = teclado.nextLong();
				if (idEquipo > 0 && idEquipo < 1000) {
					valido = true;
				} else {
					valido = false;
					System.out.println("Ha introducido un id inválido");
				}
			} while (!valido);
			valido = false;

			do {
				System.out.println("Introduce el año de inscripción del equipo (>1800 y <2023)");
				anioinscripcion = teclado.nextInt();
				if (anioinscripcion > 1800 && anioinscripcion < 2023) {
					valido = true;
				} else {
					valido = false;
					System.out.println("Ha introducido un año inválido");
				}
			} while (!valido);
			valido = false;

			int numeroatletas = -1;
			do {
				System.out.println("Introduce el numero de atletas");
				numeroatletas = teclado.nextInt();
				if (numeroatletas >= 3 && numeroatletas <= 5) {
					valido = true;
				} else {
					System.out.println("introduce un numero valido");
				}
			} while (!valido);
			Atleta[] atletas = new Atleta[numeroatletas];
			for (int i = 0; i < numeroatletas; i++) {
				atletas[i] = Atleta.nuevoAtleta();
			}
			manager = Manager.nuevoManager();

			do {
				System.out.println("Introduce el nombre del equipo (>2 y <100 caracteres)");
				nombre = teclado.next();
				if (nombre.length() > 2 && nombre.length() < 100) {
					valido = true;
				} else {
					valido = false;
					System.out.println("Ha introducido un nombre inválido");
				}
			} while (!valido);
			valido = false;

			ret = new Equipo(idEquipo, anioinscripcion, manager, atletas, nombre);
			System.out.println("¿Desea crear un equipo con los siguientes valores: (true para si, false para no) ?"
					+ ret.toString());
			correccion = Utilidades.leerBoolean();
			return ret;
		} while (!correccion);

	}

	public static void inscribirenPrueba() {
		Scanner teclado = new Scanner(System.in);
		boolean ret = false;
		File fichero = new File("pruebas.txt");
		FileReader lector = null;
		BufferedReader buffer = null;
		int subelecc = -1;
		boolean valido = false;
		Equipo nuevo = Equipo.nuevoEquipo();
		Prueba pruebaSelecc = null;
		try {
			Prueba[] colectivas = new Prueba[256];
			int i = 0;
			try {
				lector = new FileReader(fichero);
				buffer = new BufferedReader(lector);
				String linea;
				while ((linea = buffer.readLine()) != null) {
					String[] campos = linea.split("\\|");
					long idPrueba = Long.valueOf(campos[0]);
					String nombrePrueba = campos[1];
					LocalDate fecha = LocalDate.parse(campos[2], DateTimeFormatter.ofPattern("dd/MM/YYYY"));
					String lugarString = campos[3];

					Lugar lugar = null;
					for (Lugar l : Lugar.values()) {
						if (l.name().equalsIgnoreCase(lugarString)) {
							lugar = l;
						}
					}
					boolean individual = Boolean.valueOf(campos[4]);
					Prueba p = new Prueba(idPrueba, nombrePrueba, fecha, lugar, individual);
					if (p.isIndividual()) {
						System.out.println("" + p);
						colectivas[i] = p;
						i++;
					}
				}

				do {
					System.out.println("Introduzca el id de la prueba en que desea inscribirse:");
					subelecc = teclado.nextInt();
					for (int j = 0; j < i; j++) {
						if (((Prueba) colectivas[j]).getId() == subelecc) {

							pruebaSelecc = colectivas[j];
							valido = true;
							break;
						}
					}
					if (!valido) {
						System.out.println("El valor " + subelecc
								+ " no es válido. Se le mostrarán de nuevo las pruebas colectivas:");
						for (Prueba aux : colectivas) {
							if (aux != null) {
								System.out.println("" + aux);
							}
						}
					} else {
						System.out.println("Se ha elegido la prueba de id:" + subelecc + ". ¿Es correcto?");
						if (valido = Utilidades.leerBoolean()) {
							break;
						} else {
							System.out.println("Se le mostrarán de nuevo las pruebas colectivas:");
							for (Prueba aux : colectivas) {
								if (aux != null) {
									System.out.println("" + aux);
								}
							}
						}
					}
				} while (!valido);

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
		valido = false;
		String path = "inscrip_" + pruebaSelecc.getId() + "_" + nuevo.getManager().getPersona().getNifnie().mostrar()
				+ ".dat";
		try {
			FileOutputStream ficheroInscrip = new FileOutputStream(path, false);
			ObjectOutputStream escritor = new ObjectOutputStream(ficheroInscrip);
			escritor.writeObject((Equipo) nuevo);
			escritor.writeObject((Long) pruebaSelecc.getId());
			LocalDateTime ahora = LocalDateTime.now();
			escritor.writeObject((LocalDateTime) ahora);
			escritor.flush();
			valido = true;
			escritor.close();
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
		/// Si el fichero se creó exitosamente, se lee su contenido y se muestra el
		/// mensaje
		if (!valido) {
			System.out.println("ERROR: No se creó el fichero con la inscripcion.");
		} else {
			try {
				File ficheroLeido = new File(path);
				FileInputStream ficheroInscrip = new FileInputStream(ficheroLeido);
				ObjectInputStream lectorFichInsc = new ObjectInputStream(ficheroInscrip);
				Equipo equipoLeido = (Equipo) lectorFichInsc.readObject();
				Long idPruebaLeido = (Long) lectorFichInsc.readObject();
				LocalDateTime fechahoraLeida = (LocalDateTime) lectorFichInsc.readObject();
				System.out.println("Se ha creado el fichero " + path + " a "
						+ fechahoraLeida.format(DateTimeFormatter.ofPattern("dd/MM/YY hh:mm:ss"))
						+ " en el que el equipo " + equipoLeido.getId() + " de nombre " + equipoLeido.getNombre()
						+ "representado por " + equipoLeido.getManager().getPersona().getNombre() + "( "
						+ equipoLeido.getManager().getPersona().getNifnie().mostrar() + " )"
						+ " queda inscrito en la prueba " + idPruebaLeido + " de nombre " + pruebaSelecc.getNombre()
						+ " a celebrar en " + pruebaSelecc.getLugar().getNombre() + " el día "
						+ pruebaSelecc.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + ".");
				valido = true;
				lectorFichInsc.close();
			} catch (FileNotFoundException e) {
				System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
			} catch (IOException e) {
				System.out.println("Se ha producido una IOException" + e.getMessage());
			} catch (Exception e) {
				System.out.println("Se ha producido una Exception" + e.getMessage());
			}
		}

	}

	public Equipo(long id, int anioinscripcion, Manager manager, Atleta[] atletas) {
		super();
		this.idEquipo = id;
		this.anioinscripcion = anioinscripcion;
		this.manager = manager;
		this.atletas = atletas;
	}

	public Equipo(long idParticipante, Equipo e, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idEquipo = e.idEquipo;
		this.anioinscripcion = e.anioinscripcion;
		this.manager = e.manager;
		this.atletas = e.atletas;
	}

	public Equipo(long idEquipo2, int anioinscripcion2, Manager manager2, Atleta[] atletas2, String nombre2) {
		super();
		this.idEquipo = id;
		this.anioinscripcion = anioinscripcion;
		this.manager = manager;
		this.atletas = atletas;
		this.nombre = nombre;
	}

	@Override
	public long getId() {
		return idEquipo;
	}

	@Override
	public void setId(long id) {
		this.idEquipo = id;
	}

	public int getAnioinscripcion() {
		return anioinscripcion;
	}

	public void setAnioinscripcion(int anioinscripcion) {
		this.anioinscripcion = anioinscripcion;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Atleta[] getAtletas() {
		return atletas;
	}

	public void setAtletas(Atleta[] atletas) {
		this.atletas = atletas;
	}

	// Ejercicio 3
	@Override
	public String toString() {
		String ret = "";

		ret += "EQ" + idEquipo + ". de " + manager.getPersona().getNombre() + " (" + manager.getDireccion() + ") "
				+ atletas.length + " componentes en el equipo:\n";
		for (Atleta a : atletas) {
			ret += a.getId() + ". " + a.getPersona().getNombre() + "("
					+ a.getPersona().getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ") "
					+ " Datos físicos:\t" + a.getPeso() + "Kgs.\t" + a.getAltura() + "m.\n";
		}
		ret += "Valido durante el " + anioinscripcion;
		return ret;
	}

	public long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(long idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
