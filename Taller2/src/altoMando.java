
public class altoMando {
	private int numeroaltomando;
	private String nombre;
	private String pokemon1;
	private String pokemon2;
	private String pokemon3;
	private String pokemon4;
	private String pokemon5;
	private String pokemon6;
	
	public altoMando(int numeroaltomando, String nombre, String pokemon1, String pokemon2, String pokemon3,
			String pokemon4, String pokemon5, String pokemon6) {
		super();
		this.numeroaltomando = numeroaltomando;
		this.nombre = nombre;
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
		this.pokemon3 = pokemon3;
		this.pokemon4 = pokemon4;
		this.pokemon5 = pokemon5;
		this.pokemon6 = pokemon6;
	}

	public int getNumeroaltomando() {
		return numeroaltomando;
	}

	public void setNumeroaltomando(int numeroaltomando) {
		this.numeroaltomando = numeroaltomando;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPokemon1() {
		return pokemon1;
	}

	public void setPokemon1(String pokemon1) {
		this.pokemon1 = pokemon1;
	}

	public String getPokemon2() {
		return pokemon2;
	}

	public void setPokemon2(String pokemon2) {
		this.pokemon2 = pokemon2;
	}

	public String getPokemon3() {
		return pokemon3;
	}

	public void setPokemon3(String pokemon3) {
		this.pokemon3 = pokemon3;
	}

	public String getPokemon4() {
		return pokemon4;
	}

	public void setPokemon4(String pokemon4) {
		this.pokemon4 = pokemon4;
	}

	public String getPokemon5() {
		return pokemon5;
	}

	public void setPokemon5(String pokemon5) {
		this.pokemon5 = pokemon5;
	}

	public String getPokemon6() {
		return pokemon6;
	}

	public void setPokemon6(String pokemon6) {
		this.pokemon6 = pokemon6;
	}

	@Override
	public String toString() {
		return  numeroaltomando + " " + nombre + ": [pokemon1=" + pokemon1
				+ ", pokemon2:" + pokemon2 + ", pokemon3:" + pokemon3 + ", pokemon4:" + pokemon4 + ", pokemon5:"
				+ pokemon5 + ", pokemon6:" + pokemon6 + "]";
	}
	
	
}
