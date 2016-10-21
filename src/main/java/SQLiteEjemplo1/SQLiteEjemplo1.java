/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLiteEjemplo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class SQLiteEjemplo1 {

    public static void main(String[] args) {
	try {
	    Class.forName("org.sqlite.JDBC");
	    String url = "jdbc:sqlite:/home/alumno/Dropbox/AD/Tema2BD/BasesDeDatos/ejemplo.db";
	    Connection conexion = null;
	    conexion = DriverManager.getConnection(url);

	    Statement sentencia = conexion.createStatement();

	    String sql = "SELECT * FROM empleado";

	    ResultSet result = sentencia.executeQuery(sql);
	    while (result.next()) {
		for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
		    System.out.println("Columna " + i + " ->" + result.getMetaData().getColumnLabel(i));
		}
	    }

	}catch (SQLException sqle) {
	    sqle.printStackTrace();
	}catch (ClassNotFoundException cnfe){
	    cnfe.printStackTrace();
	}
    }

}
