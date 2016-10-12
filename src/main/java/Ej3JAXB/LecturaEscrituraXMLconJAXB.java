/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej3JAXB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author miguel
 */
public class LecturaEscrituraXMLconJAXB {

	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		Scanner sfichero = new Scanner(System.in);
		String nombreFichero = null;
		/*
		 * Declaramos una variable tipo File que instanciaremos más adelante cuando recivamos el el
		 * parametro bien como argumento de la aplicación, bien cuando se lo solicitamos al usuario.
		 */
		File fichero;

		//Comprobamos si nos pasan el nombre del fichero como un argumento de la lista de comandos
		if (args.length != 0) {//
			nombreFichero = args[0];
		}

		do {
			System.out.println("--------------------------------------");
			System.out.println("            Menú Principal");
			System.out.println("--------------------------------------");
			System.out.println("1. Deseas Escribir en xml con JAXB");
			System.out.println("2. Deseas leer de xml con JAXB");
			System.out.println("0. Salir");
			System.out.print("Introduce la opción deseada: ");
			opcion = sc.nextInt();
			switch (opcion) {
				case 1: {
					//Solicitamos el nombre del fichero si no lo hemos obtenido como parametro
					if (args.length == 0) {
						System.out.print("Introduce el nombre del fichero a escribir:");
						nombreFichero = sfichero.nextLine();
					}
					/*
					 * Creamos una instancia de la clase File con el nombre obtenido y se la
					 * asignamos a la variable fichero
					 */

					fichero = new File(nombreFichero);
					EscrituraFicheroXMLconJABX(fichero);
					break;
				}
				case 2: {
					//Solicitamos el nombre del fichero si no lo hemos obtenido como parametro
					if (args.length == 0) {
						System.out.print("Introduce el nombre del fichero a leer:");
						nombreFichero = sfichero.nextLine();

					}
					/*
					 * Creamos una instancia de la clase File con el nombre obtenido y se la
					 * asignamos a la variable fichero
					 */
					fichero = new File(nombreFichero);
					LecturaFicheroXMLconJABX(fichero);
					break;
				}
				default: {
					break;
				}

			}

		} while (opcion != 0);
	}

	private static void EscrituraFicheroXMLconJABX(File fichero) throws JAXBException {

		ArrayList<Libreria> listalibrerias = new ArrayList<>();
		//Creamos una lista de libros donde vamos a guardar los datos de varios libros de ejemplo
		ArrayList<Libro> librosejemplo = new ArrayList<>();
		ArrayList<Libro> librosejemplo2 = new ArrayList<>();
		ArrayList<Libro> librosejemplo3 = new ArrayList<>();

		//Creamos un par de libros de ejemplo que vamos a insertar en la lista
		Libro libro1 = new Libro("El quijote", "Miguel de Cervantes", "Planeta", "978-84-1545-297-3");
		Libro libro2 = new Libro("Harry Potter", "J.K. Rowling", "Oxford", "978-84-1545-297-4");
		Libro libro3 = new Libro("Perico El nene", "Antonio Perez", "Pikachu", "125-87-1245-297-3");
		Libro libro4 = new Libro("Poter Potter", "J.D. Cowling", "Oxfird", "9178-84-1545-297-4");
		Libro libro5 = new Libro("El pijote", "Ciguel de CeMvantes", "Plaeta", "978-84-1445-297-3");
		Libro libro6 = new Libro("Hamy Pomer", "J.K. Mowling", "Ozford", "878-84-1545-297-4");

		//Añadimos los dos libros creados a la lista
		librosejemplo.add(libro1);
		librosejemplo.add(libro2);
		librosejemplo2.add(libro3);
		librosejemplo2.add(libro4);
		librosejemplo3.add(libro5);
		librosejemplo3.add(libro6);

		/*
		 * Se crea un objeto de la clase libreria y le asignamos la lista de libros que hemos creado
		 * el nombre y el lugar donde se encuentra la librería
		 */
		Libreria milibreria = new Libreria(librosejemplo, "Libreria1", "Petrer");
		Libreria milibreria2 = new Libreria(librosejemplo2, "Libreria2", "Elda");
		Libreria milibreria3 = new Libreria(librosejemplo3, "Libreria3", "Sax");

		ArrayList<Libreria> listaLibreria = new ArrayList<>();

		listaLibreria.add(milibreria);
		listaLibreria.add(milibreria2);
		listaLibreria.add(milibreria3);

		Franquicia miFranquicia = new Franquicia(listaLibreria, "InteligenciaNula");
		/*
		 * Ahora vamos a realizar la escritura en el fichero XML utilizando las metodos JAXB
		 */

 /*
		 * Indicamos cual será la clase raíz del documento XML, para ello tenemos que utilizar un
		 * objeto JAXBConxtext que nos permitirá definir cual es la clase que se encuentra en la
		 * raiz
		 */
		JAXBContext context = JAXBContext.newInstance(Franquicia.class);
		//JAXBContext context = JAXBContext.newInstance(Libreria.class);

		//Creamos el marshaller que es la clase que nos permitirá convertir un objeto en una cadena XML
		Marshaller marshall = context.createMarshaller();

		//Al marshaller podemos indicarle que la cadena que convierta siga un patrón de formato para que sea legible
		//SetProperty recibe el nombre de la funcion y el valor que ha de tomar
		// vamos esto es pa que salgan tabuladores y legible.
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		//Escribimos el marshaller en el fichero XML que hemos solicitado en la clase main
		marshall.marshal(miFranquicia, fichero);

	}

	private static void LecturaFicheroXMLconJABX(File fichero) throws JAXBException, FileNotFoundException {
		/*
		 * Para leer tendreemos que realizar los pasos contrarios al proceso de escritura pero
		 * utilizaremos una clase tipo Unmarshaller que nos permitirá las clases del fichero XML
		 */
 /*
		 * Indicamos cual será la clase raíz del documento XML, para ello tenemos que utilizar un
		 * objeto JAXBConxtext que nos permitirá definir cual es la clase que se encuentra en la
		 * raiz
		 */
		JAXBContext context = JAXBContext.newInstance(Franquicia.class);

		//Creamos el elemento unmarshaller en el contexto de la clase librería
		Unmarshaller unmarshall = context.createUnmarshaller();

		/*
		 * Convertimos la cadena XML en objetos que podamos manejar en java necesitamos un objeto de
		 * la clase FileReader para poder leer del fichero.
		 */
		Franquicia franquicia = (Franquicia) unmarshall.unmarshal(new FileReader(fichero));

		ArrayList<Libreria> listaLibreria = franquicia.getListaLibreria();

		//Mostramos en pantalla el nombre de la franquicia
		System.out.println("Nombre de la franquicia:" + franquicia.getNombre());
		System.out.println("Librerias de la franquicia:");
		//Mostramos en pantalla el nombre de la libreria y el lugar
		for (Libreria libreria : franquicia.getListaLibreria()) {
			System.out.println("\tNombre de la libreria:" + libreria.getNombre());
			System.out.println("\tLugar de la libreria:" + libreria.getLugar());
			System.out.println("\tLibros de la libreria:");
			//Recorremos la lista de libros imprimiendo para cada unos sus atributos.
			for (Libro listaLibros : libreria.getListaLibro()) {
				System.out.println("\t\tNombre del libro: " + listaLibros.getNombre());
				System.out.println("\t\tAutor del libro: " + listaLibros.getAutor());
				System.out.println("\t\tEditorial del libro: " + listaLibros.getEditorial());
				System.out.println("\t\tIsbn del libro: " + listaLibros.getIsbn());
				System.out.println();
			}
		}
	}
}
