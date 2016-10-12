/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej3JAXB;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author miguel
 */
 @XmlType(propOrder={"autor","nombre","editorial","isbn"})
                
public class Libro {
    //Declaración de las propiedades de la clase Libro
    private String nombre;
    private String autor;
    private String editorial;
    private String isbn;
    
    //Constructores de la clase
    public Libro(String nombre, String autor, String editorial,String isbn){
        super();
        this.nombre=nombre;
        this.autor=autor;
        this.editorial=editorial;
        this.isbn=isbn;
    }
    public Libro(){
        //Constructor por defecto
    }
    
    //Setters y Getters de la clase Libro
    public String getNombre(){
        return nombre;
    }
    public String getAutor(){
        return autor;
    }
    public String getEditorial(){
        return editorial;
    }
    public String getIsbn(){
        return isbn;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setAutor(String autor){
        this.autor=autor;
    }
    public void setEditorial(String editorial){
        this.editorial=editorial;
    }
    public void setIsbn (String isbn){
        this.isbn=isbn;
    }
}
