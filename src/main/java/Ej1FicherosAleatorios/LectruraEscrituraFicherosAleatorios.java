/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ej1FicherosAleatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author KirasW10
 */
public class LectruraEscrituraFicherosAleatorios {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		String nombre;

		if (args.length != 0) {
			nombre = args[0];
		} else {
			System.out.print("Por favor, introduzca un nombre de fichero: ");
			nombre = sc.nextLine();

		}
		File fichero = new File(nombre);

		int opcion;
		do {
			System.out.println("1. ¿Deseas escribir un fichero binario?");
			System.out.println("2. ¿Deseas escribir un registro en un fichero binario?");
			System.out.println("3. ¿Deseas leer un registro de un fichero binario?");
			System.out.println("4. ¿Deseas leer de un fichero binario?");
			System.out.println("5. ¿Deseas modificar un registro de un fichero binario?");
			System.out.print("Seleccione una opción: ");
			opcion = sc.nextInt();
			switch (opcion) {
				case 1: {
					EscrituraFicheroAleatorio(fichero);
					break;
				}

				case 2: {
					EscrituraUnRegFicheroAleatorio(fichero);
					break;
				}

				case 3: {
					LeerUnRegistroFicheroAleatorio(fichero);
					break;
				}

				case 4: {
					LecturaFicheroAleatorio(fichero);
					break;
				}

				case 5: {
					ModificarUnRegistroFicheroAleatorio(fichero);
					break;
				}

				case 0: {
					break;
				}
			}
		} while (opcion != 0);
	}

	static void EscrituraFicheroAleatorio(File fichero) throws IOException, FileNotFoundException {
		RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

		String[] apellidos = {"FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA"};
		int[] departamentos = {10, 20, 10, 10, 30, 30, 20};
		Double[] salarios = {1000.45, 2400.60, 3000.0, 1500.56, 2200.0};

		StringBuffer buffer;

		for (int i = 0; i < apellidos.length; i++) {
			// Escribimos el ID
			raf.writeInt(i + 1);
			// Preparamos el tamaño del String
			buffer = new StringBuffer(apellidos[i]);
			buffer.setLength(10);
			// Escribimos los datos
			raf.writeChars(buffer.toString());
			raf.writeInt(departamentos[i]);
			raf.writeDouble(salarios[i]);
		}
		raf.close();
	}

	static void EscrituraUnRegFicheroAleatorio(File fichero) throws IOException, FileNotFoundException {
		RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

		System.out.print("Introduce el ID del empleado: ");
		int id = sc.nextInt();

		System.out.print("Introduce el apellido: ");
		StringBuffer buffer = new StringBuffer(sc.next());
		buffer.setLength(10);

		System.out.print("Introduce el departamento: ");
		int departamento = sc.nextInt();

		System.out.print("Introduce el salario: ");
		double salario = sc.nextDouble();

		// Nos colocamos en la posicion a escribir.
		raf.seek((id - 1) * 36);

		raf.writeInt(id);
		raf.writeChars(buffer.toString());
		raf.writeInt(departamento);
		raf.writeDouble(salario);

		raf.close();
	}

	static void LeerUnRegistroFicheroAleatorio(File fichero) throws IOException, FileNotFoundException {
		RandomAccessFile raf = new RandomAccessFile(fichero, "r");

		System.out.print("Introduce el ID del empleado a leer: ");
		raf.seek(36 * (sc.nextInt() - 1));
		char[] apellido = new char[10];

		int id = raf.readInt();

		for (int i = 0; i < apellido.length; i++) {
			apellido[i] = raf.readChar();
		}
		String apeEmpleados = new String(apellido);
		int departamento = raf.readInt();
		double salario = raf.readDouble();

		System.out.println("ID --> " + id);
		System.out.println("Apellido: " + apeEmpleados + " Dep: " + departamento + " Salario: " + salario);

		raf.close();
	}

	static void LecturaFicheroAleatorio(File fichero) throws IOException, FileNotFoundException {

		RandomAccessFile raf = new RandomAccessFile(fichero, "r");

		int posicion = 0;
		int departamento;
		double salario;
		char apellido[] = new char[10];

		while (posicion < raf.length()) {
			raf.seek(posicion);

			int id = raf.readInt();
			for (int i = 0; i < apellido.length; i++) {
				apellido[i] = raf.readChar();
			}
			String apeEmpleados = new String(apellido);

			departamento = raf.readInt();
			salario = raf.readDouble();

			System.out.println("ID --> " + id);
			System.out.println("Apellido: " + apeEmpleados + " Dep: " + departamento + " Salario: " + salario);
			posicion = posicion + 36;
		}
		raf.close();
	}

	static void ModificarUnRegistroFicheroAleatorio(File fichero) throws IOException, FileNotFoundException {
		RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

		System.out.print("Introduce el ID del empleado a modificar: ");

		int posicion = sc.nextInt();
		sc.nextLine();
		raf.seek(36 * (posicion - 1));

		System.out.println("Este es el empleado: ");
		char[] apellido = new char[10];

		int id = raf.readInt();
		for (int i = 0; i < apellido.length; i++) {
			apellido[i] = raf.readChar();
		}
		String apeEmpleados = new String(apellido);

		int departamento = raf.readInt();
		double salario = raf.readDouble();

		System.out.println("ID --> " + id);
		System.out.println("Apellido: " + apeEmpleados
				+ "\nDep: " + departamento
				+ "\nSalario: " + salario);

		raf.seek((36 * (posicion - 1)) + Integer.BYTES);

		System.out.println("Introduce ahora los nuevos valores.");
		System.out.println("Para no realizar cambios en alguno pulse intro sin escrbir nada.");

		System.out.print("Introduce el apellido: ");
		String nuevoApellido = sc.nextLine();
		if (nuevoApellido.isEmpty()) {
			for (int i = 0; i < apeEmpleados.length(); i++) {
				raf.readChar();
			}
		} else {
			StringBuffer buffer = new StringBuffer(nuevoApellido);
			buffer.setLength(10);
			raf.writeChars(buffer.toString());
		}

		System.out.print("Introduce el departamento: ");
		// Lo leeremos como un String y lo pasaremos luego con los wrappers para comprobar si es cadena vacia.
		String nuevoDepartamento = sc.nextLine();
		if (nuevoDepartamento.isEmpty()) {
			raf.readInt();
		} else {
			raf.writeInt(Integer.parseInt(nuevoDepartamento));
		}
		
		System.out.print("Introduce el salario: ");
		String nuevoSalario = sc.nextLine();
		if (nuevoSalario.isEmpty()) {
			raf.readDouble();
		} else {
			raf.writeDouble(Double.parseDouble(nuevoSalario));
		}

		raf.close();
	}
}
