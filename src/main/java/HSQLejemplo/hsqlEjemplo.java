/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSQLejemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumno
 */
public class hsqlEjemplo {

    public static void main(String[] args) {

	try {
	    String url = "jdbc:hsqldb:/home/alumno/Dropbox/AD/HSQLDB/hsqld_db.db";
	    Class.forName("org.hsqldb.jdbc.JDBCDriver");
	    Connection conexion = DriverManager.getConnection(url, "", "");
	    Statement sentencia = conexion.createStatement();
	    String sql = "SELECT * FROM departamentos";
	    ResultSet result = sentencia.executeQuery(sql);
	    while (result.next()) {
		System.out.println("ID departamento: " + result.getInt(1)
			+ "\nNombre del departamento: " + result.getString(2)
			+ "\nLocalidad del departamento: " + result.getString(3));
	    }
	    
	    result.close();
	    sentencia.close();
	} catch (ClassNotFoundException clnfe) {
	    clnfe.printStackTrace();
	} catch (SQLException sqle) {
	    sqle.printStackTrace();
	}
    }
}
