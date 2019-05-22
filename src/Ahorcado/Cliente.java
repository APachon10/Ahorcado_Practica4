package Ahorcado;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		try {
			Registry registro = LocateRegistry.getRegistry(5555);
			String palabra = "";
			Servidor v = new Servidor(palabra);
			System.out.println(v.intentos);
			Random r = new Random();
			int num_aleatorio = r.nextInt(3);
			boolean res = false;
			while(v.intentos >=0) {
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
