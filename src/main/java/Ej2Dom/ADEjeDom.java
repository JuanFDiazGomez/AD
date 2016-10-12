/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej2Dom;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author KirasW10
 */

public class ADEjeDom {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
		// TODO code application logic here
		Scanner sc = new Scanner(System.in);
		int opcion;

		System.out.print("Introduce el nombre del fichero binario: ");
		String nombre = sc.nextLine();
		File fichero = new File(nombre);
		do {
			System.out.println("1. ¿Deseas escribir un fichero binario?");
			System.out.println("2. ¿Deseas leer un fichero binario?");
			System.out.println("3. ¿Deseas escribir en un fichero XML desde un binario?");
			System.out.println("4. ¿Deseas leer un fichero XML?");
			System.out.print("Seleccione una opción: ");
			opcion = sc.nextInt();
			sc.nextLine();
			switch (opcion) {
				case 1: {
					EscrituraObjetoFicheroBinario(fichero);
					break;
				}

				case 2: {
					LecturaObjetoFicheroBinario(fichero);
					break;
				}

				case 3: {
					EscrituraObjetoFicheroXML(fichero);
					break;
				}

				case 4: {
					LecturaObjetoFicheroXML();
					break;
				}

				case 0: {
					break;
				}
			}
		} while (opcion != 0);

	}

	private static void EscrituraObjetoFicheroBinario(File fichero) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(fichero);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		String nombres[] = {"Pepe", "Jose", "Juan", "Ramon", "Antonio"};
		int edades[] = {12, 23, 21, 34, 25};

		for (int i = 0; i < nombres.length; i++) {
			oos.writeObject(new persona(nombres[i], edades[i]));
		}
	}

	private static ArrayList<persona> LecturaObjetoFicheroBinario(File fichero) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(fichero);
		ObjectInputStream ois = new ObjectInputStream(fis);

		ArrayList<persona> ListPersona = new ArrayList();
		try {
			while (true) {
				ListPersona.add((persona) ois.readObject());
			}
		} catch (EOFException eoef) {
			for (persona p : ListPersona) {
				System.out.println(p);
			}
			System.out.println("Fin de archivo");
		} finally {
			ois.close();
			fis.close();
			return ListPersona;
		}

	}

	private static void EscrituraObjetoFicheroXML(File fichero) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		System.out.print("Introduce el nombre del fichero XML: ");
		Scanner entry = new Scanner(System.in);
		String ficheroXML = entry.nextLine();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, ficheroXML, null);
			document.setXmlVersion("1.0");

			ArrayList<persona> ListaPersonas = LecturaObjetoFicheroBinario(fichero);
			for (persona p : ListaPersonas) {
				Element raiz = document.createElement("persona");
				document.getDocumentElement().appendChild(raiz);
				CrearElemento("Nombre", p.getNombre(), raiz, document);
				CrearElemento("Edad", Integer.toString(p.getEdad()), raiz, document);
			}
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File(ficheroXML));
			Transformer transform = TransformerFactory.newInstance().newTransformer();
			transform.transform(source, result);
		} catch (Exception e) {
			System.err.println("Error: " + e);
		}
	}

	private static void CrearElemento(String datoPersona, String valor, Element raiz, Document document) {
		Element element = document.createElement(datoPersona);
		Text text = document.createTextNode(valor);
		raiz.appendChild(element);
		element.appendChild(text);

	}

	private static void LecturaObjetoFicheroXML() {
		System.out.print("Introduce el nombre del fichero XML: ");
		Scanner entry = new Scanner(System.in);
		String ficheroXML = entry.nextLine();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(ficheroXML);
			document.getDocumentElement().normalize();
			System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
			NodeList personas = document.getElementsByTagName("persona");
			for (int i = 0; i < personas.getLength(); i++) {
				Node persona = personas.item(i);
				if(persona.getNodeType()==Node.ELEMENT_NODE){
					Element elemento = (Element) persona;
					System.out.println("Nombre: "+elemento.getElementsByTagName("Nombre").item(0).getTextContent());
					System.out.println("Edad: "+elemento.getElementsByTagName("Edad").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
