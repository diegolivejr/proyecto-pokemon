package bbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Pokemon;

public class PokemonBD {

	
	MovimientosPokemonBD mpBD;
	
	public PokemonBD() {
		this.mpBD = new MovimientosPokemonBD();
	}
	
	public String[] getByEntrenador(int idEntrenador, int cuantos) {
	    String[] resultado = new String[cuantos];
	    try {
	    	Statement stmt = BD.getConnetion().createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT NOMBRE FROM pokemon WHERE ID_ENTRENADOR = "+idEntrenador+" ORDER BY ID_POKEMON LIMIT "+cuantos);
		    int index = 0; 
		    while (rs.next()) {
		    	if (index>=cuantos) continue;
		    	resultado[index++]=rs.getString(1);		    
		    }		    
		    return resultado;
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error al obtener los pokemons del entrenador " + e.getMessage());
           resultado=null;
       }
	   return resultado;		
	}
	

	public Pokemon[] getListaPokemonEntrenador(int idEntrenador) {
	    try {
	    	ArrayList<Pokemon>  lista = new ArrayList();
	    	Statement stmt = BD.getConnetion().createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT pokemon.*, pokedex.TIPO1, pokedex.TIPO2 "
								    		+" FROM pokemon "
								    		+" INNER JOIN pokedex ON pokedex.NUM_POKEDEX=pokemon.NUM_POKEDEX "
								    		+" WHERE pokemon.ID_ENTRENADOR = " + idEntrenador
								    		+" ORDER BY pokemon.ID_POKEMON"
								    		+"");		    		    
		    while (rs.next()) {
		    	Pokemon pokemon = creaPokemonRS(rs);
		    	cargaMovimientos(pokemon);
		    	lista.add(pokemon);
		    }		    
		    return lista.toArray(new Pokemon[lista.size()]);        		    
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error al obtener los pokemons del entrenador " + e.getMessage());
           return null;
       }
	}
	
	public Pokemon[] getListaPokemonSexo(int idEntrenador, String sexo) {
	    try {
	    	ArrayList<Pokemon>  lista = new ArrayList();
	    	String sql = "SELECT pokemon.*, pokedex.TIPO1, pokedex.TIPO2 "
		    			+" FROM pokemon "
		    			+" INNER JOIN pokedex ON pokedex.NUM_POKEDEX=pokemon.NUM_POKEDEX "
		    			+" WHERE pokemon.ID_ENTRENADOR = ? " 
		    			+"   AND pokemon.SEXO=? "
		    			+" ORDER BY pokemon.ID_POKEMON "
		    			+"";
	    	PreparedStatement stmt = BD.getConnetion().prepareStatement(sql);
		    stmt.setInt(1, idEntrenador);
		    stmt.setString(2, sexo);
	    	ResultSet rs = stmt.executeQuery();		    		    
		    while (rs.next()) {
		    	Pokemon pokemon = creaPokemonRS(rs);
		    	cargaMovimientos(pokemon);
		    	lista.add(pokemon);
		    }		    
		    return lista.toArray(new Pokemon[lista.size()]);        		    
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error al obtener los pokemons por sexo " + e.getMessage());
           return null;
       }
	}
	
	private Pokemon creaPokemonRS(ResultSet rs) throws SQLException  {
    	Pokemon pokemon = new Pokemon(
    			rs.getInt("ID_POKEMON"),
    			rs.getInt("NUM_POKEDEX"),
    			rs.getInt("ID_ENTRENADOR"),
    			rs.getInt("CAJA"),
    			rs.getString("NOMBRE"),
    			rs.getString("MOTE"),
    			rs.getInt("SALUD"),
    			rs.getInt("SALUD_MAXIMA"),
    			rs.getInt("ATAQUE"),
    			rs.getInt("DEFENSA"),
    			rs.getInt("VELOCIDAD"),
    			rs.getInt("AT_ESPECIAL"),
    			rs.getInt("DEF_ESPECIAL"),
    			rs.getInt("NIVEL"),
    			rs.getInt("FERTILIDAD"),
    			rs.getString("SEXO"),
    			rs.getInt("ESTADO"),		    			
    			rs.getString("TIPO1"),
    			rs.getString("TIPO2"),
    			rs.getInt("EXPERIENCIA")
			);
    	return pokemon;
	}
	
	

	public Pokemon getPokemon(int idPokemon) {
	    try {
	    	Statement stmt = BD.getConnetion().createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT pokemon.*, pokedex.TIPO1, pokedex.TIPO2 "
								    		+" FROM pokemon "
								    		+" INNER JOIN pokedex ON pokedex.NUM_POKEDEX=pokemon.NUM_POKEDEX "
								    		+" WHERE pokemon.ID_POKEMON = " + idPokemon
								    		+ "");		    		    
		    if (!rs.next()) return null;
	    	Pokemon pokemon = creaPokemonRS(rs);
	    	cargaMovimientos(pokemon);
		    return pokemon;
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error al obtener el pokemon por id " + e.getMessage());
           return null;
       }
	}
	
	public void guarda(Pokemon pokemon) {
        String sql = "UPDATE POKEMON SET NUM_POKEDEX=?, ID_ENTRENADOR=?, CAJA=?, NOMBRE=?, MOTE=?, SALUD=?, SALUD_MAXIMA=?, ATAQUE=?, DEFENSA=?, VELOCIDAD=?, AT_ESPECIAL=?, DEF_ESPECIAL=?, NIVEL=?, FERTILIDAD=?, SEXO=?, EXPERIENCIA=? WHERE ID_POKEMON=?";
        try (PreparedStatement statement = BD.getConnetion().prepareStatement(sql)) {
            statement.setInt(1, pokemon.getNumPokedex() ); 			// NUM_POKEDEX 
            statement.setInt(2, pokemon.getIdEntrenador());						// ID_ENTRENADOR
            statement.setInt(3, pokemon.getCaja());							// CAJA
            statement.setString(4, pokemon.getNombre());			// NOMBRE
            statement.setString(5, pokemon.getMote());							// MOTE
            statement.setInt(6, pokemon.getSalud()); 			// SALUD (entre 25 y 50) 
            statement.setInt(7, pokemon.getSaludMaxima()); 			// SALUD (entre 25 y 50) 
            statement.setInt(8, pokemon.getAtaque()); 			// ATAQUE (entre 10 y 20) 
            statement.setInt(9, pokemon.getDefensa()); 			// DEFENSA (entre 20 y 30) 
            statement.setInt(10, pokemon.getVelocidad()); 				// VELOCIDAD (entre 15 y 20) 
            statement.setInt(11, pokemon.getAtEspecial()); 			// AT_ESPECIAL (entre 15 y 20) 
            statement.setInt(12, pokemon.getDefEspecial()); 			// DEF_ESPECIAL (entre 20 y 30) 
            statement.setInt(13, pokemon.getNivel()); 								// NIVEL (1) 
            statement.setInt(14, pokemon.getFertilidad()); 								// FERTILIDAD (0)
            statement.setString (15, pokemon.getSexo()); 				// SEXO (entre 'M' y 'H') 
            statement.setInt(16, pokemon.getExperiencia());					// EXPERIENCIA            
            statement.setInt(17, pokemon.getIdPokemon() );					// POKEMON_ID            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}

	// guarda en la base de datos el nuevo pokémon y devuelve el ID generado
	public int crea(Pokemon pokemon) {
        String sql = "INSERT INTO POKEMON(NUM_POKEDEX,ID_ENTRENADOR, CAJA, NOMBRE, MOTE, SALUD, SALUD_MAXIMA, ATAQUE, DEFENSA, VELOCIDAD, AT_ESPECIAL, DEF_ESPECIAL, NIVEL, FERTILIDAD, SEXO, EXPERIENCIA ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = BD.getConnetion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, pokemon.getNumPokedex() ); 		 
            statement.setInt(2, pokemon.getIdEntrenador());
            statement.setInt(3, pokemon.getCaja());			
            statement.setString(4, pokemon.getNombre());	
            statement.setString(5, pokemon.getMote());		
            statement.setInt(6, pokemon.getSalud()); 		 
            statement.setInt(7, pokemon.getSaludMaxima()); 		 
            statement.setInt(8, pokemon.getAtaque()); 		 
            statement.setInt(9, pokemon.getDefensa()); 		 
            statement.setInt(10, pokemon.getVelocidad()); 	 
            statement.setInt(11, pokemon.getAtEspecial()); 	 
            statement.setInt(12, pokemon.getDefEspecial());  
            statement.setInt(13, pokemon.getNivel()); 		 
            statement.setInt(14, pokemon.getFertilidad()); 	
            statement.setString (15, pokemon.getSexo()); 	 
            statement.setInt(16, pokemon.getExperiencia());		            
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            int pokemonId=keys.getInt(1);            
            return pokemonId;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }		
	}
	
	public void borra(Pokemon pokemon) {
		if (pokemon==null) return;
        String sql;
        sql = "DELETE FROM MOVIMIENTO_POKEMON WHERE ID_POKEMON=?";
        try (PreparedStatement statement = BD.getConnetion().prepareStatement(sql)) {
            statement.setInt(1, pokemon.getIdPokemon() );	            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
        sql = "DELETE FROM POKEMON WHERE ID_POKEMON=?";
        try (PreparedStatement statement = BD.getConnetion().prepareStatement(sql)) {
            statement.setInt(1, pokemon.getIdPokemon() );	            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}
	
	
	public void cargaMovimientos(Pokemon pokemon) {
		pokemon.setMovimientosActivos(mpBD.getMovimientosActivosPokemon(pokemon.getIdPokemon()));
		pokemon.setMovimientosAprendidos(mpBD.getMovimientosAprendidosPokemon(pokemon.getIdPokemon()));
	}
	
	public void actualizaDineroEntrenador(int idEntrenador, int nuevoSaldo) {
	    try (Connection conn = BD.getConnetion();
	         PreparedStatement pstmt = conn.prepareStatement("UPDATE ENTRENADOR SET POKEDOLLARS=? WHERE ID_ENTRENADOR=?")) {
	        pstmt.setInt(1, nuevoSaldo);
	        pstmt.setInt(2, idEntrenador);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void guardaMovimientoPokemon(int idMovimiento, int idPokemon, String activo) {
        String sql = "INSERT INTO movimiento_pokemon (ID_MOVIMIENTO,ID_POKEMON, ACTIVO) VALUES (?,?,?)";
        try (PreparedStatement statement = BD.getConnetion().prepareStatement(sql)) {
            statement.setInt(1, idMovimiento); 
            statement.setInt(2, idPokemon);
            statement.setString(3, activo);
            statement.executeUpdate();                        
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}
	
	public void actualizaMovimientoPokemon(int idMovimiento, int idPokemon, String activo) {
        String sql = "UPDATE movimiento_pokemon SET ACTIVO=? WHERE ID_MOVIMIENTO=? AND ID_POKEMON=?";
        try (PreparedStatement statement = BD.getConnetion().prepareStatement(sql)) {
            statement.setString(1, activo);
            statement.setInt(2, idMovimiento); 
            statement.setInt(3, idPokemon);
            statement.executeUpdate();                        
        } catch (SQLException e) {
            e.printStackTrace();
        }		
		
	}
	
}
