import java.util.ArrayList;

public class Gimnasio {
	private int numeroginmasio;
	private String lider;
	private String estado;
	private ArrayList<String> pokemons;
	public Gimnasio(int numeroginmasio, String lider, String estado, ArrayList<String> pokemons) {
		super();
		this.numeroginmasio = numeroginmasio;
		this.lider = lider;
		this.estado = estado;
		this.pokemons = pokemons;
	}
	public int getNumeroginmasio() {
		return numeroginmasio;
	}
	public void setNumeroginmasio(int numeroginmasio) {
		this.numeroginmasio = numeroginmasio;
	}
	public String getLider() {
		return lider;
	}
	public void setLider(String lider) {
		this.lider = lider;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ArrayList<String> getPokemons() {
		return pokemons;
	}
	public void setPokemons(ArrayList<String> pokemons) {
		this.pokemons = pokemons;
	}
	@Override
	public String toString() {
		return "Gimnasio "+ numeroginmasio + ", Lider: " + lider + ", Estado: " + estado + ", Pokemons: "
				+ pokemons;
	}	
	
	
}
