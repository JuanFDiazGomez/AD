/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exam1ej1;

import java.io.File;

/**
 *
 * @author KirasW10
 */
public class ADEje1 {
	public static void main(String[] args) {
		if(args.length == 1){
			File file = new File(args[0]);
			file.deleteOnExit();
			if(file.isDirectory()){
				borradoRecursivo(file.listFiles());	
			}
			System.out.println("Borrando: "+file.getName());
		}else{
			System.out.println("Introduce el directorio a borrar de forma"+
					" recursiva al ejecutar el programa");
		}
	}
	
	public static void borradoRecursivo(File []fileList){
		for (File file : fileList) {
			file.deleteOnExit();
			if (file.isDirectory()) {
				borradoRecursivo(file.listFiles());
			}
			System.out.println("Borrando: "+file.getName());
		}
	}
			
			
			
			
			
			
			
			
			
}
