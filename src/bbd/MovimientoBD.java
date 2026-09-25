package bbd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Movimiento;

public class MovimientoBD {
	

	public ArrayList<Movimiento> getMovimientosNivel(int nivel, String tipo1, String tipo2) {
	    try {
	    	ArrayList<Movimiento>  lista = new ArrayList();
	    	String sql = "SELECT * FROM movimientos WHERE NIVEL_APRENDIZAJE<=? AND TIPO IN ('Normal',?,?) AND ESTADO IS NULL ORDER BY ID_MOVIMIENTO";
	    	PreparedStatement stmt = BD.getConnetion().prepareStatement(sql);
	    	stmt.setInt(1, nivel);
	    	stmt.setString(2, tipo1);
	    	stmt.setString(3, tipo2);
		    ResultSet rs = stmt.executeQuery();		    		    
		    while (rs.next()) {
		    	Movimiento movimiento = new Movimiento(
		    			rs.getInt("ID_MOVIMIENTO"),
		    			rs.getString("NOM_MOVIMIENTO"),
		    			rs.getInt("POTENCIA"),
		    			rs.getString("TIPO"),
		    			rs.getString("ESTADO"),
		    			rs.getInt("QUITA"),
		    			rs.getInt("TURNOS"),
		    			rs.getString("MEJORA"),
		    			rs.getInt("CANT_MEJORA"),
		    			rs.getInt("NIVEL_APRENDIZAJE")
     			);		    			
		    	lista.add(movimiento);
		    }		    
		    //Movimiento[] resultado = new Movimiento[lista.size()];
		    //return lista.toArray(resultado);
		    return lista;
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error al obtener los movimientos para los tipos (Normal, " + tipo1+ ", "+ tipo2 + " y nivel "+nivel);
           return new ArrayList<Movimiento>();
       }
	}

	public Movimiento getMovimiento(int id) {
	    try {
	    	String sql = "SELECT * FROM movimientos WHERE ID_MOVIMIENTO=?";
	    	PreparedStatement stmt = BD.getConnetion().prepareStatement(sql);
	    	stmt.setInt(1, id);
		    ResultSet rs = stmt.executeQuery(sql);		    		    
		    if (!rs.next()) return null;
	    	Movimiento movimiento = new Movimiento(
	    			rs.getInt("ID_MOVIMIENTO"),
	    			rs.getString("NOM_MOVIMIENTO"),
	    			rs.getInt("POTENCIA"),
	    			rs.getString("TIPO"),
	    			rs.getString("ESTADO"),
	    			rs.getInt("QUITA"),
	    			rs.getInt("TURNOS"),
	    			rs.getString("MEJORA"),
	    			rs.getInt("CANT_MEJORA"),
	    			rs.getInt("NIVEL_APRENDIZAJE")
 			);
	    	return movimiento;
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("Error al obtener el movimiento con id "+id);
           return null;
       }
	}

	
}
