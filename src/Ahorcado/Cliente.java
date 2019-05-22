package Ahorcado;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		try {
			Registry registro = LocateRegistry.getRegistry(5555);
			
			Servidor v = new Servidor();
			String palabra = "";
			palabra = v.generarPalabra(v.Palabras);
			String cadena_final = "";
			
			System.out.println(v.intentos);
			Random r = new Random();
			int num_aleatorio = r.nextInt(3);
			
			for (int i = 0; i < palabra.length(); i++) {
				System.out.print(" _ ");
			}
			while(v.intentos >=0 || palabra.equals(cadena_final)) {
				System.out.println("Introduce una Letra: ");
				String letra = leerCadenas();
				v.compararLetra(letra, palabra);
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
