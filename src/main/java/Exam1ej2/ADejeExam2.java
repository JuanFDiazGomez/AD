/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exam1ej2;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author KirasW10
 */

public class ADejeExam2 {
	static final String ARCHIVO = "Departamentos.dat";
	
	public static void main(String[] args) {
		File file = new File(ARCHIVO);
		
		escribirDatos(file);
		leerDatos(file);
	}

	private static void escribirDatos(File file) {
		int numeroDepartamento[] = {1,2,3,4};
		String nombreDepartamento[] = {"Analisis", "Ventas", "Compras","Mantenimiento"};
		String localidadDepartamento[] = {"Madrid", "Barcelona", "Toledo", "Sevilla"};
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try{
			fos = new FileOutputStream(file);
			dos = new DataOutputStream(fos);
			for (int i = 0; i < numeroDepartamento.length; i++) {
				dos.writeInt(numeroDepartamento[i]);
				dos.writeUTF(nombreDepartamento[i]);
				dos.writeUTF(localidadDepartamento[i]);
			}
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(dos!=null){
				try{
					dos.close();
					fos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}

	private static void leerDatos(File file) {
		FileInputStream fis = null;
		DataInputStream dis = null;
		try{
			fis = new FileInputStream(file);
			dis = new DataInputStream(fis);
			
			while(true){
				System.out.println("Numero Departamento: "+ dis.readInt()
						+"\nNombre: "+dis.readUTF()
						+"\nLocalidad: "+dis.readUTF()
						+"\n---------------");
			}
		}catch(EOFException eofe){
			System.out.println("Lectura Finalizada");
		}catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(dis!=null){
				try{
					dis.close();
					fis.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
}
