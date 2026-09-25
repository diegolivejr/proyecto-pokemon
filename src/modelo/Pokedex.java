package modelo;

public class Pokedex {	
	
	int		id;
	String 	nombre;
	String	tipo1;
	String 	tipo2;
	
	public Pokedex(
		int		id,
		String 	nombre,
		String	tipo1,
		String 	tipo2) {
		this.id = id;
		this.nombre = nombre;
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getTipo1() {
		return tipo1;
	}

	public String getTipo2() {
		return tipo2;
	}

	public void setId(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setNombre(String string) {
		// TODO Auto-generated method stub
		
	}
}
