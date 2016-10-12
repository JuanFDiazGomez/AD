/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej3JAXB;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

/**
 *
 * @author KirasW10
 */
@XmlRootElement()
@XmlType(propOrder = {"nombre","listaLibreria"})
public class Franquicia {

	private String nombre;
	private ArrayList<Libreria> listaLibreria;
	
	// Constructores de la clase Franquicia
	public Franquicia(ArrayList<Libreria> listaLibreria, String nombre) {
		this.nombre = nombre;
		this.listaLibreria = listaLibreria;
	}

	public Franquicia(){
		// Constructor por defecto
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElementWrapper(name="listaLibreria")
	@XmlElement(name="Libreria")
	public ArrayList<Libreria> getListaLibreria() {
		return listaLibreria;
	}

	public void setListaLibreria(ArrayList<Libreria> listaLibreria) {
		this.listaLibreria = listaLibreria;
	}
	
	
	
}
