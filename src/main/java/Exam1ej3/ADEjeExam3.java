/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exam1ej3;

import java.io.*;

/**
 *
 * @author KirasW10
 */
public class ADEjeExam3 {
	static final String ARCHIVO = "Departamentos.dat";
	static final String TEMP = "DepartamentosTemp.dat";
	
	public static void main(String[] args) {
		if(args.length == 3){
			boolean modificado = false;
			File file = new File(ARCHIVO);
			File fileTmp = new File(TEMP);
			
			FileOutputStream fos = null;
			DataOutputStream dos = null;
			
			FileInputStream fis = null;
			DataInputStream dis = null;
			
			try{
				fos = new FileOutputStream(fileTmp);
				dos = new DataOutputStream(fos);
				
				fis = new FileInputStream(file);
				dis = new DataInputStream(fis);
				
				while(true){
					int numeroDepartamento = dis.readInt();
					String nombreDepartamento = dis.readUTF();
					String localidadDepartamento = dis.readUTF();
					
					if(numeroDepartamento == Integer.parseInt(args[0])){
						
						System.out.println("Numero de departamento: " + numeroDepartamento);
						
						System.out.println("Valores antiguos: " +
								"\n\tNombre: " + nombreDepartamento +
								"\n\tLocalidad: " + localidadDepartamento);
						
						System.out.println("Valores nuevos: " +
								"\n\tNombre: " + args[1] +
								"\n\tLocalidad: " + args[2]);
						
						dos.writeInt(numeroDepartamento);
						dos.writeUTF(args[1]);
						dos.writeUTF(args[2]);
						modificado = true;
					}else{
						dos.writeInt(numeroDepartamento);
						dos.writeUTF(nombreDepartamento);
						dos.writeUTF(localidadDepartamento);
					}
				}
				
			}catch(EOFException eoef){
				System.out.println("Fin de Archivo.");
			}catch(FileNotFoundException fnfe){
				fnfe.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}finally{
				if(dis!=null && dos!=null){
					try{
						dos.close();
						dis.close();
						fos.close();
						fis.close();
					}catch(IOException ioe){
						ioe.printStackTrace();
					}
				}
				if(modificado){
					file.delete();
					fileTmp.renameTo(file);
				}else{
					fileTmp.delete();
				}
			}
		}else{
			System.out.println("Debe de ejecutar el programa de la siguiente forma;");
			System.out.println("\tjava ADEjeExam3 numeroDepartamento nuevoNombre nuevaLocalizacion");
		}
	}
}
