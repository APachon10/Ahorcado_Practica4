package Ahorcado;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Scanner;

public class Cliente {
	static String palabra = "";
	public static void main(String[] args) {
		
		try {
			Registry registro = LocateRegistry.getRegistry(5555);
			
			Servidor v = new Servidor();
			palabra = v.palabra_generada;
			String cadena_final = "";
			
			Random r = new Random();
			int num_aleatorio = r.nextInt(3);
			System.out.println("La palabra tiene un total de : "+ palabra.length() +" letras");
			for (int i = 0; i < palabra.length(); i++) {
				System.out.print(" _ ");
			}
			System.out.println("\n=====================");
			int j=0;
			while(v.intentos >=0) {
				System.out.print("Introduce una Letra: ");
				String letra = leerCadenas();
				if(v.letraAcertada(letra) == true ) {
					v.compararLetra(letra);	
				}
			}
		} catch (Exception e) {
			System.out.println("Error: "+e);
			e.printStackTrace();
		}
	}
	public static String leerCadenas() {
		Scanner scan  = new Scanner(System.in);
		String cadena = scan.nextLine();
		return cadena;
	}
}
