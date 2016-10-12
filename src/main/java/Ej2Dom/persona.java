/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej2Dom;

import java.io.Serializable;

/**
 *
 * @author KirasW10
 */
public class persona implements Serializable {
	
	private String nombre;
	private int edad;

	public persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public persona() {
		this.nombre = null;
		this.edad = 0;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "persona{" + "nombre=" + nombre + ", edad=" + edad + '}';
	}
}