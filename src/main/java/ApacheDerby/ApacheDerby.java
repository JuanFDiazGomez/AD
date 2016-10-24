/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApacheDerby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author alumno
 */
public class ApacheDerby {
    public static void main(String[] args) {
	try{
	    Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	    Connection conexion = DriverManager.getConnection("jdbc:derby:/home/alumno/Dropbox/AD/Apache_Derby/ejemploApacheDB.db");
	    Statement sentencia = conexion.createStatement();
	    String sql = "SELECT * FROM departamentos";
	    ResultSet result = sentencia.executeQuery(sql);
	    while(result.next()){
		System.out.println("ID departamento: "+result.getInt(1)+
			"\nNombre del departamento: "+result.getString(2)+
			"\nLocalidad del departamento: "+result.getString(3));
	    }
	    result.close();
	    sentencia.close();
	    
	    
	}catch(ClassNotFoundException cnfe){
	    
	}catch(SQLException sqle){
	}
	
    }
	    
    
}
