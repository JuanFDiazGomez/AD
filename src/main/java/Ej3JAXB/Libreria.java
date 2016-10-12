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
 * @author miguel
 */
@XmlType(propOrder = {"nombre","lugar", "listaLibro"})
public class Libreria {
    //Atributos de la clase Libreria
    
    private ArrayList<Libro> listaLibro;
    private String nombre;
    private String lugar;
    
    //Constructores de la clase Libreria
    
    public Libreria (ArrayList<Libro> listaLibro,String nombre,String lugar){
        super();
        this.listaLibro=listaLibro;
        this.nombre=nombre;
        this.lugar=lugar;
    }
    public Libreria(){
        //Constructor por defecto
    }
    
    //Setters y Getters de la clase Libreria
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setLugar(String lugar){
        this.lugar=lugar;
    }
    
   public String getNombre(){
       return nombre;
   }
   public String getLugar(){
       return lugar;
   }
   
   //Wrapper, envoltura alrededor la representaci�n XML
   
   @XmlElementWrapper(name="listaLibro")
   @XmlElement(name="Libro")
   public ArrayList<Libro> getListaLibro(){
       return listaLibro;
   }
   
   public void setListaLibro(ArrayList<Libro> listaLibro){
       this.listaLibro=listaLibro;
   }
    
}