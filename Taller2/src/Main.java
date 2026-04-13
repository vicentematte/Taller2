//Vicente Augusto Matte Barraza
//21.753.089-K
//Ing. Tecnologias de Informacion
//
//
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<altoMando> listaAltosMandos = new ArrayList<>();
	public static ArrayList<Gimnasio> listaGimnasio = new ArrayList<>();
	public static ArrayList<Habitat> listaHabitat = new ArrayList<>();
	public static ArrayList<Pokedex> listaPokedex = new ArrayList<>();
	
	public static Scanner s;
	public static void main(String[] args) throws FileNotFoundException{
		
		String apodo = "";
		
		cargarAltoMando();
		//mostrarAltoMando();
		
		cargarGimnasio();
		//mostrarGimnasio();
		
		cargarHabitat();
		//mostrarHabitat();
		
		cargarPokedex();
		//mostrarPokedex();
		
/*1) Continuar.
2) Nueva Partida.
3) Salir.
switch case
*/		
		s = new Scanner(System.in);
		System.out.println("1) Continuar."); System.out.println("2) Nueva Partida."); System.out.println("3) Salir.");
		System.out.print("Ingrese opcion: "); int numero = s.nextInt();
		s.nextLine();
		
		switch (numero){
		case 2:
			System.out.print("Ingrese Apodo: "); apodo = s.nextLine();
			break;
		case 3:
			System.out.println("Nos vemos entrenador...");
			return;
		default:
			System.out.println("Opcion Invalida.");
			return;
		
		boolean jugando = true;
		while (jugando) {
			System.out.println(apodo + " , que deseas hacer?"); System.out.println(" ");
			System.out.println("1) Revisar equipo.");
			System.out.println("2) Salir a capturar.");
			System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
			System.out.println("4) Retar un gimnasio.");
			System.out.println("5) Desafío al Alto Mando.");
			System.out.println("6) Curar Pokémon.");
			System.out.println("7) Guardar.");
			System.out.println("8) Guardar y Salir."); System.out.println(" ");
			System.out.print("Ingrese Opcion: ");int opcion = s.nextInt();
			
		}
		
		
		}
		
		
		switch(opcion) {
		case(2):
			mostrarHabitat();
			System.out.println("7) Voler al menu.");
			
		
		case(8):
			System.out.println("Nos vemos entrenador...");
		return;
		default:
			System.out.println("Opcion Invalida.");
			return;
		}
			
	}

	private static void mostrarPokedex() {
		int indice = 1;
		for (Pokedex t: listaPokedex) {
			System.out.println(t);
			indice +=1;
		}
	}

	private static void cargarPokedex() throws FileNotFoundException{
		File file = new File("Pokedex.txt");
		s = new Scanner(file);
		
		while (s.hasNextLine()) {
			String[] split = s.nextLine().split(";");
			//pokemon;habitat;porcentajeAparicion;vida;ataque;defensa;ataqueEspecial;defensaEspecial;velocidad;Tipo
			//altoMando altosMandos = new altoMando(Integer.valueOf(split[0]), split[1], split[2],split[3],split[4],split[5],split[6],split[7]);
			Pokedex infoPokemons = new Pokedex(split[0],split[1],Double.parseDouble(split[2]),Integer.valueOf(split[3]),Integer.valueOf(split[4]),Integer.valueOf(split[5]),Integer.valueOf(split[6]),Integer.valueOf(split[7]),Integer.valueOf(split[8]),(split[9]));
			
	        listaPokedex.add(infoPokemons);

		}
	}

	private static void mostrarHabitat() {
		int indice = 1;
		for (Habitat t: listaHabitat) {
			System.out.println(indice+")"+" "+t);
			indice +=1;
		}
	}

	private static void cargarHabitat() throws FileNotFoundException{
		File file = new File("Habitats.txt");
		s = new Scanner(file);
		
		while (s.hasNextLine()) {
			String Habitat = s.nextLine();
			
			Habitat habitats = new Habitat(Habitat);
			listaHabitat.add(habitats);
		}
	}

	private static void mostrarGimnasio() {
		int indice = 1;
		for(Gimnasio t: listaGimnasio) {
			System.out.println(t);
			indice += 1;
		}
	}

	private static void cargarGimnasio() throws FileNotFoundException{
		File file = new File("Gimnasios.txt");
		s= new Scanner(file);
		
		while(s.hasNextLine()) {
			String[] split = s.nextLine().split(";");
			
			int numeroGimnasio = Integer.valueOf(split[0]);
			String Lider = split[1];
			String Estado = split[2];
			int cantPokemons = Integer.valueOf(split[3]);
			
			// hacemos una array temoral diria yo, para leugo agregarseal a la array main?
	        ArrayList<String> pokemons = new ArrayList<>();
	        	     
	        // llenamos esa nueva array
			for(int i =0; i < cantPokemons;i++) {
	            pokemons.add(split[4 + i]);
			}
			 Gimnasio gym = new Gimnasio(numeroGimnasio, Lider, Estado, pokemons);	        
		     listaGimnasio.add(gym);

		}
	}

	private static void mostrarAltoMando() {
		int indice = 1;
		for(altoMando t: listaAltosMandos) {
			System.out.println(t);
			indice += 1;
		}
	}

	private static void cargarAltoMando() throws FileNotFoundException{
		// Cargar los objetos (archivo de alto mando) y meterlos a una arraylist
		File file = new File("Alto Mando.txt");
		s = new Scanner(file);
		
		while(s.hasNextLine()) {
			String[] split = s.nextLine().split(";");
			
			altoMando altosMandos = new altoMando(Integer.valueOf(split[0]), split[1], split[2],split[3],split[4],split[5],split[6],split[7]);
			listaAltosMandos.add(altosMandos);

		}
	}
	
}
