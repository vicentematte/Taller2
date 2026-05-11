import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static ArrayList<altoMando> listaAltosMandos = new ArrayList<>();
    public static ArrayList<Gimnasio> listaGimnasio = new ArrayList<>();
    public static ArrayList<Habitat> listaHabitat = new ArrayList<>();
    public static ArrayList<Pokedex> listaPokedex = new ArrayList<>();
    public static ArrayList<Pokedex> listaCapturados = new ArrayList<>();
    public static ArrayList<Pokedex> listaEquipo = new ArrayList<>();

    public static Scanner s;
    public static int maxEquipo = 6;

    public static void main(String[] args) throws FileNotFoundException {

        String apodo = "";
        int numero = 0;
        int opcion = 0;

        cargarAltoMando();
        cargarGimnasio();
        cargarHabitat();
        cargarPokedex();

        //menu inical
        do {
            s = new Scanner(System.in);
            System.out.println("1) Continuar.");
            System.out.println("2) Nueva Partida.");
            System.out.println("3) Salir.");
            System.out.print("Ingrese opcion: ");
            try {
                numero = s.nextInt();
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Opcion invalida.");
                s.nextLine();
                continue;
            }
            if (numero == 3) break;
        } while (numero != 1 && numero != 2);

        switch (numero) {
            case 1:
                try {
                    File archivoRegistro = new File("Registros.txt");
                    Scanner lectorRegistro = new Scanner(archivoRegistro);

                    if (lectorRegistro.hasNextLine()) {
                        String[] primeraLinea = lectorRegistro.nextLine().split(";");
                        apodo = primeraLinea[0];
                    }

                    while (lectorRegistro.hasNextLine()) {
                        String[] linea = lectorRegistro.nextLine().split(";");
                        String nombrePoke = linea[0];
                        String estadoPoke = linea[1];

                        for (Pokedex p : listaPokedex) {
                            if (p.getPokemon().equalsIgnoreCase(nombrePoke)) {
                                p.setEstado(estadoPoke);
                                listaCapturados.add(p);
                                if (listaEquipo.size() < maxEquipo) {
                                    listaEquipo.add(p);
                                }
                                break;
                            }
                        }
                    }
                    lectorRegistro.close();
                    System.out.println("Bienvenido de vuelta " + apodo + "!!");

                } catch (FileNotFoundException e) {
                    System.out.println("No se encontro partida guardada. Iniciando nueva partida...");
                    System.out.print("Ingrese Apodo: ");
                    apodo = s.nextLine();
                    System.out.println("Bienvenido " + apodo + "!!");
                }
                break;

            case 2:
                System.out.print("Ingrese Apodo: ");
                apodo = s.nextLine();
                System.out.println("Bienvenido " + apodo + "!!");
                break;

            case 3:
                System.out.println("Nos vemos entrenador...");
                return;
        }

        // segundo menu, el priuncial
        do {
            System.out.println("\n" + apodo + " , que deseas hacer?\n");
            System.out.println("1) Revisar equipo.");
            System.out.println("2) Salir a capturar.");
            System.out.println("3) Acceso al PC (cambiar Pokemon del equipo).");
            System.out.println("4) Retar un gimnasio.");
            System.out.println("5) Desafio al Alto Mando.");
            System.out.println("6) Curar Pokemon.");
            System.out.println("7) Guardar.");
            System.out.println("8) Guardar y Salir.\n");
            System.out.print("Ingrese Opcion: ");

            try {
                opcion = s.nextInt();
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Opcion invalida.");
                s.nextLine();
                continue;
            }

            switch (opcion) {

                case 1:
                    System.out.println("Equipo Actual:");
                    mostrarEquipo();
                    break;

                case 2:
                    System.out.println("Donde deseas ir a explorar?\n");
                    System.out.println("Zonas Disponibles:\n");
                    mostrarHabitat();
                    System.out.println("7) Volver al menu.");
                    System.out.println("");
                    System.out.print("Ingrese Zona: ");

                    int zona = 0;
                    try {
                        zona = s.nextInt();
                        s.nextLine();
                    } catch (Exception e) {
                        System.out.println("Opcion invalida.");
                        s.nextLine();
                        break;
                    }

                    if (zona == 7) break;
                    if (zona < 1 || zona > listaHabitat.size()) {
                        System.out.println("Zona invalida.");
                        break;
                    }

                    String habitatElegido = listaHabitat.get(zona - 1).getHabitat();

                    ArrayList<Pokedex> pokemonsZona = new ArrayList<>();
                    for (Pokedex p : listaPokedex) {
                        if (p.getHabitat().equalsIgnoreCase(habitatElegido)) {
                            pokemonsZona.add(p);
                        }
                    }

                    if (pokemonsZona.isEmpty()) {
                        System.out.println("No hay Pokemon en esta zona.");
                        break;
                    }

                    Pokedex pokemonSalvaje = elegirPokemonAleatorio(pokemonsZona);

                    System.out.println("Oh!! Ha aparecido un increible " + pokemonSalvaje.getPokemon() + "!!");
                    System.out.println("Que deseas hacer?");
                    System.out.println("1) Capturar");
                    System.out.println("2) Huir");
                    System.out.print("Ingrese opcion: ");

                    int opcionCaptura = 0;
                    try {
                        opcionCaptura = s.nextInt();
                        s.nextLine();
                    } catch (Exception e) {
                        System.out.println("Opcion invalida.");
                        s.nextLine();
                        break;
                    }

                    if (opcionCaptura == 1) {
                        boolean yaCapturado = false;
                        for (Pokedex p : listaCapturados) {
                            if (p.getPokemon().equalsIgnoreCase(pokemonSalvaje.getPokemon())) {
                                yaCapturado = true;
                                break;
                            }
                        }
                        if (yaCapturado) {
                            System.out.println("Ya tienes a " + pokemonSalvaje.getPokemon() + "! No puedes capturarlo de nuevo.");
                        } else {
                            pokemonSalvaje.setEstado("Vivo");
                            listaCapturados.add(pokemonSalvaje);
                            System.out.println(pokemonSalvaje.getPokemon() + " capturado con exito!!");
                            if (listaEquipo.size() < maxEquipo) {
                                listaEquipo.add(pokemonSalvaje);
                                System.out.println(pokemonSalvaje.getPokemon() + " ha sido agregado a tu equipo!");
                            } else {
                                System.out.println(pokemonSalvaje.getPokemon() + " ha sido guardado en el PC.");
                            }
                        }
                    } else {
                        System.out.println("Huiste del combate!");
                    }
                    break;

                case 3:
                    System.out.println("Que Pokemon deseas cambiar?");
                    mostrarpokemonsRegistro();
                    System.out.println("\n1) Cambiar Pokemon");
                    System.out.println("2) Salir.\n");
                    System.out.print("Ingrese opcion: ");

                    int opcionPc = 0;
                    try {
                        opcionPc = s.nextInt();
                        s.nextLine();
                    } catch (Exception e) {
                        System.out.println("Opcion invalida.");
                        s.nextLine();
                        break;
                    }

                    if (opcionPc == 1) {
                        System.out.print("Ingresa el indice del primer Pokemon a intercambiar: ");
                        int opcionPc1 = 0, opcionPc2 = 0;
                        try {
                            opcionPc1 = s.nextInt(); s.nextLine();
                            System.out.print("Ingresa el indice del segundo Pokemon a intercambiar: ");
                            opcionPc2 = s.nextInt(); s.nextLine();
                        } catch (Exception e) {
                            System.out.println("Opcion invalida.");
                            s.nextLine();
                            break;
                        }
                        if (opcionPc1 < 1 || opcionPc1 > listaCapturados.size() ||
                            opcionPc2 < 1 || opcionPc2 > listaCapturados.size()) {
                            System.out.println("Indices invalidos.");
                            break;
                        }
                        Collections.swap(listaCapturados, opcionPc1 - 1, opcionPc2 - 1);
                        listaEquipo.clear();
                        for (int i = 0; i < Math.min(maxEquipo, listaCapturados.size()); i++) {
                            listaEquipo.add(listaCapturados.get(i));
                        }
                        System.out.println("Intercambio realizado!");
                    }
                    break;

                    
                    
                    
                    
                    
                case 4:
                    System.out.println("A cual Lider deseas retar??\n");
                    mostrarGimnasio();
                    System.out.println("9) volver al menu.");
                    System.out.println("");
                    System.out.print("Ingrese una opcion: ");
                    int opcionLider = 0;
                    try {
                        opcionLider = s.nextInt(); s.nextLine();
                    } catch (Exception e) {
                        System.out.println("Opcion invalida.");
                        s.nextLine();
                        break;
                    }

                    if (opcionLider == 9) break;
                    if (opcionLider < 1 || opcionLider > listaGimnasio.size()) {
                        System.out.println("Opcion invalida.");
                        break;
                    }

                    boolean puedeRetar = true;
                    for (int i = 0; i < opcionLider - 1; i++) {
                        if (!listaGimnasio.get(i).getEstado().equalsIgnoreCase("Derrotado")) {
                            puedeRetar = false;
                            break;
                        }
                    }

                    if (!puedeRetar) {
                        System.out.println("Calmado Entrenador!!! No puedes retar a "
                            + listaGimnasio.get(opcionLider - 1).getLider()
                            + " sin haber derrotado a los lideres anteriores!!");
                        break;
                    }

                    Gimnasio gimElegido = listaGimnasio.get(opcionLider - 1);

                    if (gimElegido.getEstado().equalsIgnoreCase("Derrotado")) {
                        System.out.println("Ya derrotaste a " + gimElegido.getLider() + "!");
                        break;
                    }

                    System.out.println("Desafiando a " + gimElegido.getLider() + "!!");

                    // combate
                    ArrayList<String> pokemonsGim = gimElegido.getPokemons();
                    int indicePokemonGim = 0;

                    Pokedex pokemonJugador = null;
                    for (Pokedex p : listaEquipo) {
                        if (p.getEstado().equalsIgnoreCase("Vivo")) {
                            pokemonJugador = p;
                            break;
                        }
                    }

                    if (pokemonJugador == null) {
                        System.out.println("No tienes Pokemon vivos en tu equipo!");
                        break;
                    }

                    System.out.println(gimElegido.getLider() + " saca a " + pokemonsGim.get(indicePokemonGim) + "!");
                    System.out.println(apodo + " saca a " + pokemonJugador.getPokemon() + "!");

                    boolean combateActivo = true;
                    boolean victoriaGim = false;

                    while (combateActivo && indicePokemonGim < pokemonsGim.size()) {
                        String nombreRival = pokemonsGim.get(indicePokemonGim);
                        Pokedex pokemonRival = null;
                        for (Pokedex p : listaPokedex) {
                            if (p.getPokemon().equalsIgnoreCase(nombreRival)) {
                                pokemonRival = p;
                                break;
                            }
                        }

                        if (pokemonRival == null) {
                            indicePokemonGim++;
                            continue;
                        }

                        System.out.println("\nQue deseas hacer?");
                        System.out.println("1) Atacar");
                        System.out.println("2) Cambiar de pokemon");
                        System.out.println("3) Rendirse");
                        System.out.print("Ingrese Opcion: ");

                        int opcionBatalla = 0;
                        try {
                            opcionBatalla = s.nextInt(); s.nextLine();
                        } catch (Exception e) {
                            System.out.println("Opcion invalida.");
                            s.nextLine();
                            continue;
                        }

                        if (opcionBatalla == 3) {
                            System.out.println("Te has rendido. Volviendo al menu...");
                            combateActivo = false;
                            break;
                        }

                        if (opcionBatalla == 2) {
                            System.out.println("Elige un Pokemon de tu equipo:");
                            for (int i = 0; i < listaEquipo.size(); i++) {
                                System.out.println((i + 1) + ") " + listaEquipo.get(i).getPokemon() + " - " + listaEquipo.get(i).getEstado());
                            }
                            System.out.print("Ingrese opcion: ");
                            int eleccion = 0;
                            try {
                                eleccion = s.nextInt(); s.nextLine();
                            } catch (Exception e) {
                                System.out.println("Opcion invalida.");
                                s.nextLine();
                                continue;
                            }
                            if (eleccion < 1 || eleccion > listaEquipo.size()) {
                                System.out.println("Opcion invalida.");
                                continue;
                            }
                            Pokedex elegido = listaEquipo.get(eleccion - 1);
                            if (elegido.getEstado().equalsIgnoreCase("Debilitado")) {
                                System.out.println("Ese Pokemon esta debilitado!");
                                continue;
                            }
                            pokemonJugador = elegido;
                            System.out.println(apodo + " saca a " + pokemonJugador.getPokemon() + "!");
                            continue;
                        }

                        if (opcionBatalla == 1) {
                            int statsJugador = pokemonJugador.getStatsTotales();
                            int statsRival = pokemonRival.getStatsTotales();

                            System.out.println(pokemonJugador.getPokemon() + " -> " + statsJugador + " puntos");
                            System.out.println(nombreRival + " -> " + statsRival + " puntos");

                            double multiplicador = TablaTipos.getEfectividad(pokemonJugador.getTipo(), pokemonRival.getTipo());

                            if (multiplicador > 1.0) {
                                System.out.println(pokemonJugador.getPokemon() + " es super efectivo contra " + nombreRival + "!");
                                statsJugador = (int)(statsJugador * multiplicador);
                            } else if (multiplicador > 0 && multiplicador < 1.0) {
                                System.out.println(pokemonJugador.getPokemon() + " no es efectivo contra " + nombreRival + "!");
                                statsJugador = (int)(statsJugador * multiplicador);
                            } else if (multiplicador == 0) {
                                System.out.println(pokemonJugador.getPokemon() + " no afecta a " + nombreRival + "!");
                                statsJugador = 0;
                            }

                            System.out.println("Nuevo puntaje:");
                            System.out.println(pokemonJugador.getPokemon() + " -> " + statsJugador + " puntos");
                            System.out.println(nombreRival + " -> " + statsRival + " puntos");

                            if (statsJugador >= statsRival) {
                                System.out.println("Ha ganado " + pokemonJugador.getPokemon() + "! " + nombreRival + " ha sido derrotado!");
                                indicePokemonGim++;
                                if (indicePokemonGim < pokemonsGim.size()) {
                                    System.out.println(gimElegido.getLider() + " saca a " + pokemonsGim.get(indicePokemonGim) + "!");
                                }
                            } else {
                                System.out.println("Ha ganado " + nombreRival + "! " + pokemonJugador.getPokemon() + " ha sido derrotado...");
                                pokemonJugador.setEstado("Debilitado");
                                pokemonJugador = null;
                                for (Pokedex p : listaEquipo) {
                                    if (p.getEstado().equalsIgnoreCase("Vivo")) {
                                        pokemonJugador = p;
                                        break;
                                    }
                                }
                                if (pokemonJugador == null) {
                                    System.out.println("Te has quedado sin Pokemon en tu equipo! Volviendo al menu...");
                                    combateActivo = false;
                                } else {
                                    System.out.println(apodo + " saca a " + pokemonJugador.getPokemon() + "!");
                                }
                            }
                        }
                    }

                    if (combateActivo && indicePokemonGim >= pokemonsGim.size()) {
                        victoriaGim = true;
                    }

                    if (victoriaGim) {
                        gimElegido.setEstado("Derrotado");
                        System.out.println("Has derrotado a " + gimElegido.getLider() + "!! Obtuviste una medalla!");
                    }
                    break;
                    
                    
                    
                    
                case 5:
                	int gimnasiosDerrotados = 0;
                	for (Gimnasio g : listaGimnasio) {
                		if (g.getEstado().equalsIgnoreCase("Derrotado")) {
                			gimnasiosDerrotados++;
                		}
                	}
                	if (gimnasiosDerrotados < 8) {
                		System.out.println("Debes derrotar los 8 gimnasios antes de desafiar al Alto Mando!");
                		break;
                	}
                	System.out.println("Desafiando al Alto Mando!!");
                	boolean derrotadoAM = false;
                	for (altoMando miembro : listaAltosMandos) {
                		System.out.println("\nDesafiando a " + miembro.getNombre() + "!!");
                		ArrayList<String> pokemonsAM = new ArrayList<>();
                		pokemonsAM.add(miembro.getPokemon1());
                		pokemonsAM.add(miembro.getPokemon2());
                		pokemonsAM.add(miembro.getPokemon3());
                		pokemonsAM.add(miembro.getPokemon4());
                		pokemonsAM.add(miembro.getPokemon5());
                		pokemonsAM.add(miembro.getPokemon6());
                		
                		Pokedex pokemonJugadorAM = null;
                		for (Pokedex p : listaEquipo) {
                			if (p.getEstado().equalsIgnoreCase("Vivo")) {
                				pokemonJugadorAM = p;
                				break;
                			}
                		}
                		
                		if (pokemonJugadorAM == null) {
                			System.out.println("No tienes Pokemon vivos! Volviendo al menu...");
                			derrotadoAM = true;
                			break;
                		}
                		
                		System.out.println(miembro.getNombre() + " saca a " + pokemonsAM.get(0) + "!");
                		System.out.println(apodo + " saca a " + pokemonJugadorAM.getPokemon() + "!");
                		
                		int indiceAM = 0;
                		boolean combateActivoAM = true;
                		boolean victoriaAM = false;
                		
                		while (combateActivoAM && indiceAM < pokemonsAM.size()) {
                			String nombreRivalAM = pokemonsAM.get(indiceAM);
                			Pokedex pokemonRivalAM = null;
                			for (Pokedex p : listaPokedex) {
                				if (p.getPokemon().equalsIgnoreCase(nombreRivalAM)) {
                					pokemonRivalAM = p;
                					break;
                				}
                			}
                			
                			if (pokemonRivalAM == null) {
                				indiceAM++;
                				continue;
                			}
                			
                			System.out.println("\nQue deseas hacer?");
                			System.out.println("1) Atacar");
                			System.out.println("2) Cambiar de pokemon");
                			System.out.println("3) Rendirse");
                			System.out.print("Ingrese Opcion: ");
                			
                			int opcionAM = 0;
                			try {
                				opcionAM = s.nextInt(); s.nextLine();
                			} catch (Exception e) {
                				System.out.println("Opcion invalida.");
                				s.nextLine();
                				continue;
                			}
                			
                			if (opcionAM == 3) {
                				System.out.println("Te has rendido. Volviendo al menu...");
                				combateActivoAM = false;
                				derrotadoAM = true;
                				break;
                			}
                			
                			if (opcionAM == 2) {
                				System.out.println("Elige un Pokemon de tu equipo:");
                				for (int i = 0; i < listaEquipo.size(); i++) {
                					System.out.println((i + 1) + ") " + listaEquipo.get(i).getPokemon() + " - " + listaEquipo.get(i).getEstado());
                				}
                				System.out.print("Ingrese opcion: ");
                				int eleccionAM = 0;
                				try {
                					eleccionAM = s.nextInt(); s.nextLine();
                				} catch (Exception e) {
                					System.out.println("Opcion invalida.");
                					s.nextLine();
                					continue;
                				}
                				if (eleccionAM < 1 || eleccionAM > listaEquipo.size()) {
                					System.out.println("Opcion invalida.");
                					continue;
                				}
                				Pokedex elegidoAM = listaEquipo.get(eleccionAM - 1);
                				if (elegidoAM.getEstado().equalsIgnoreCase("Debilitado")) {
                					System.out.println("Ese Pokemon esta debilitado!");
                					continue;
                				}
                				pokemonJugadorAM = elegidoAM;
                				System.out.println(apodo + " saca a " + pokemonJugadorAM.getPokemon() + "!");
                				continue;
                			}
                			
                			if (opcionAM == 1) {
                				int statsJugadorAM = pokemonJugadorAM.getStatsTotales();
                				int statsRivalAM = pokemonRivalAM.getStatsTotales();
                				
                				System.out.println(pokemonJugadorAM.getPokemon() + " -> " + statsJugadorAM + " puntos");
                				System.out.println(nombreRivalAM + " -> " + statsRivalAM + " puntos");
                				
                				double multiplicadorAM = TablaTipos.getEfectividad(pokemonJugadorAM.getTipo(), pokemonRivalAM.getTipo());
                				
                				if (multiplicadorAM > 1.0) {
                					System.out.println(pokemonJugadorAM.getPokemon() + " es super efectivo contra " + nombreRivalAM + "!");
                					statsJugadorAM = (int)(statsJugadorAM * multiplicadorAM);
                				} else if (multiplicadorAM > 0 && multiplicadorAM < 1.0) {
                					System.out.println(pokemonJugadorAM.getPokemon() + " no es efectivo contra " + nombreRivalAM + "!");
                					statsJugadorAM = (int)(statsJugadorAM * multiplicadorAM);
                				} else if (multiplicadorAM == 0) {
                					System.out.println(pokemonJugadorAM.getPokemon() + " no afecta a " + nombreRivalAM + "!");
                					statsJugadorAM = 0;
                				}
                				
                				System.out.println("Nuevo puntaje:");
                				System.out.println(pokemonJugadorAM.getPokemon() + " -> " + statsJugadorAM + " puntos");
                				System.out.println(nombreRivalAM + " -> " + statsRivalAM + " puntos");
                				
                				if (statsJugadorAM >= statsRivalAM) {
                					System.out.println("Ha ganado " + pokemonJugadorAM.getPokemon() + "! " + nombreRivalAM + " ha sido derrotado!");
                					indiceAM++;
                					if (indiceAM < pokemonsAM.size()) {
                						System.out.println(miembro.getNombre() + " saca a " + pokemonsAM.get(indiceAM) + "!");
                					}
                				} else {
                					System.out.println("Ha ganado " + nombreRivalAM + "! " + pokemonJugadorAM.getPokemon() + " ha sido derrotado...");
                					pokemonJugadorAM.setEstado("Debilitado");
                					pokemonJugadorAM = null;
                					for (Pokedex p : listaEquipo) {
                						if (p.getEstado().equalsIgnoreCase("Vivo")) {
                							pokemonJugadorAM = p;
                							break;
                						}
                					}
                					if (pokemonJugadorAM == null) {
                						System.out.println("Te has quedado sin Pokemon! Volviendo al menu...");
                						combateActivoAM = false;
                						derrotadoAM = true;
                					} else {
                						System.out.println(apodo + " saca a " + pokemonJugadorAM.getPokemon() + "!");
                					}
                				}
                			}
                		}
                		
                		if (combateActivoAM && indiceAM >= pokemonsAM.size()) {
                			victoriaAM = true;
                		}
                		
                		if (!victoriaAM) {
                			derrotadoAM = true;
                			break;
                		}
                		
                		System.out.println("Has derrotado a " + miembro.getNombre() + "!!");
                	}
                	
                	if (!derrotadoAM) {
                		System.out.println("\nFelicidades!! Has derrotado al Alto Mando y eres el nuevo Campeon!!");
                	}
                	break;
                    
                    
       
                    
                case 6:
                	for (Pokedex p : listaCapturados) {
                		p.setEstado("Vivo");
                	}
                	System.out.println("Tu equipo se ha recuperado!");
                    break;  
                    
                    
                    
                    
                    
                    
                    
                case 7:
                    guardar(apodo);
                    System.out.println("Partida guardada!");
                    break;

                case 8:
                    guardar(apodo);
                    System.out.println("Nos vemos entrenador...");
                    break;
                    
                    

                default:
                    System.out.println("Opcion invalida.");
            } 
        } while (opcion != 8);
    }
    //funciones lol xd
    private static void mostrarEquipo() {
        if (listaEquipo.isEmpty()) {
            System.out.println("No tienes Pokemon en tu equipo.");
            return;
        }
        int indice = 1;
        for (Pokedex t : listaEquipo) {
            System.out.println(indice + ") " + t.getPokemon() + "|" + t.getTipo() + "|Stats totales: " + t.getStatsTotales());
            indice++;
        }
    }
    private static void mostrarpokemonsRegistro() {
        int indice = 1;
        for (Pokedex t : listaCapturados) {
            System.out.println(indice + ") " + t.getPokemon() + " - " + t.getEstado());
            indice++;
        }
    }
    private static Pokedex elegirPokemonAleatorio(ArrayList<Pokedex> pokemonsZona) {
        double total = 0;
        for (Pokedex p : pokemonsZona) total += p.getPorcentajeAparicion();
        Random random = new Random();
        double valor = random.nextDouble() * total;
        double acumulado = 0;
        for (Pokedex p : pokemonsZona) {
            acumulado += p.getPorcentajeAparicion();
            if (valor <= acumulado) return p;
        }
        return pokemonsZona.get(pokemonsZona.size() - 1);
    }
    private static void mostrarHabitat() {
        int indice = 1;
        for (Habitat t : listaHabitat) {
            System.out.println(indice + ") " + t);
            indice++;
        }
    }

    private static void mostrarGimnasio() {
        int indice = 1;
        for (Gimnasio t : listaGimnasio) {
            System.out.println(indice + ") " + t.getLider() + " - Estado: " + t.getEstado());
            indice++;
        }
    }

    private static void guardar(String apodo) {
        try {
            FileWriter escritorArchivo = new FileWriter("Registros.txt");
            BufferedWriter escritorRegistro = new BufferedWriter(escritorArchivo);

            escritorRegistro.write(apodo + ";none");
            escritorRegistro.newLine();

            for (Pokedex pokemon : listaCapturados) {
                escritorRegistro.write(pokemon.getPokemon() + ";" + pokemon.getEstado());
                escritorRegistro.newLine();
            }

            escritorRegistro.close();
        } catch (IOException e) {
            System.out.println("Error al guardar la partida.");
        }
    }

    private static void cargarPokedex() throws FileNotFoundException {
        File file = new File("Pokedex.txt");
        s = new Scanner(file);
        while (s.hasNextLine()) {
            String[] split = s.nextLine().split(";");
            Pokedex infoPokemons = new Pokedex(split[0], split[1], Double.parseDouble(split[2]),
                    Integer.valueOf(split[3]), Integer.valueOf(split[4]), Integer.valueOf(split[5]),
                    Integer.valueOf(split[6]), Integer.valueOf(split[7]), Integer.valueOf(split[8]), split[9]);
            listaPokedex.add(infoPokemons);
        }
        s = new Scanner(System.in);
    }

    private static void cargarHabitat() throws FileNotFoundException {
        File file = new File("Habitats.txt");
        s = new Scanner(file);
        while (s.hasNextLine()) {
            Habitat habitats = new Habitat(s.nextLine());
            listaHabitat.add(habitats);
        }
        s = new Scanner(System.in);
    }

    private static void cargarGimnasio() throws FileNotFoundException {
        File file = new File("Gimnasios.txt");
        s = new Scanner(file);
        while (s.hasNextLine()) {
            String[] split = s.nextLine().split(";");
            int numeroGimnasio = Integer.valueOf(split[0]);
            String lider = split[1];
            String estado = split[2];
            int cantPokemons = Integer.valueOf(split[3]);
            ArrayList<String> pokemons = new ArrayList<>();
            for (int i = 0; i < cantPokemons; i++) {
                pokemons.add(split[4 + i]);
            }
            listaGimnasio.add(new Gimnasio(numeroGimnasio, lider, estado, pokemons));
        }
        s = new Scanner(System.in);
    }

    private static void cargarAltoMando() throws FileNotFoundException {
        File file = new File("Alto Mando.txt");
        s = new Scanner(file);
        while (s.hasNextLine()) {
            String[] split = s.nextLine().split(";");
            listaAltosMandos.add(new altoMando(Integer.valueOf(split[0]), split[1],
                    split[2], split[3], split[4], split[5], split[6], split[7]));
        }
        s = new Scanner(System.in);
    }
}