package bbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Pokemon;
import modelo.Pokedex;

public class PokedexBD {
	

	public int getCuantosPokemonsHay() {
		int cuantos = 0;
        try {
            String query = "SELECT COUNT(*) FROM POKEDEX";
            try (PreparedStatement statement = BD.getConnetion().prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                	resultSet.next();
                    cuantos = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; 
        }		
        return cuantos;
	}
	
	public Pokedex getPokedex(int id) {
        try {
            String query = "SELECT NUM_POKEDEX, NOM_POKEMON, TIPO1, TIPO2 FROM POKEDEX WHERE NUM_POKEDEX=?";
            try (PreparedStatement statement = BD.getConnetion().prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                	if (!resultSet.next()) {
                		System.out.println("Pokedex "+id+" no encontrado.");
                		return null;
                	};
                	Pokedex pokedex = new Pokedex(
                			resultSet.getInt(1),
                			resultSet.getString(2),
                			resultSet.getString(3),
                			resultSet.getString(4)
                		);                		
                	return pokedex;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; 
        }		
	}
	
	public Pokedex[] getAllPokedex() {
        try {
	    	ArrayList<Pokedex>  lista = new ArrayList();        	
            String query = "SELECT * FROM POKEDEX ORDER BY NUM_POKEDEX";
            try (PreparedStatement statement = BD.getConnetion().prepareStatement(query)) {
                try (ResultSet rs = statement.executeQuery()) {
        		    while (rs.next()) {
        		    	Pokedex pokedex = new Pokedex(
       		    			rs.getInt("NUM_POKEDEX"),
    		    			rs.getString("NOM_POKEMON"),
    		    			rs.getString("TIPO1"),
    		    			rs.getString("TIPO2")
        		    	);
        		    	lista.add(pokedex);
        		    }
        		    return lista.toArray(new Pokedex[lista.size()]);        		    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; 
        }		
	}

	public Pokedex getPokedexPorNombre(String nombre) {
        try {
            String query = "SELECT NUM_POKEDEX, NOM_POKEMON, TIPO1, TIPO2 FROM POKEDEX WHERE NOM_POKEMON=?";
            try (PreparedStatement statement = BD.getConnetion().prepareStatement(query)) {
                statement.setString(1, nombre);
                try (ResultSet resultSet = statement.executeQuery()) {
                	if (!resultSet.next()) {
                		System.out.println("Pokedex con nombre "+nombre+" no encontrado.");
                		return null;
                	};
                	Pokedex pokedex = new Pokedex(
                			resultSet.getInt(1),
                			resultSet.getString(2),
                			resultSet.getString(3),
                			resultSet.getString(4)
                		);                		
                	return pokedex;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; 
        }		
	}
	
	
}
