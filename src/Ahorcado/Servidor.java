package Ahorcado;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Servidor implements AhorcadoInterface {
	public static void main(String[] args) throws UnknownHostException, RemoteException {
		Registry registro = null;
		Servidor v = new Servidor();
		Random r = new Random();
		System.setProperty("java.rmi.server.hostname",InetAddress.getLocalHost().getHostAddress());
		String [] Palabras = {"Alberto","Anime","Manga"};
		try {
			registro = LocateRegistry.createRegistry(1234);
			registro.rebind("Ahorcado",
					(AhorcadoInterface) UnicastRemoteObject.exportObject(v, 0));
		} catch (Exception e) {
			System.out.println("Error : "+e);
			e.printStackTrace();
		}
		String pa = v.generarPalabra(r, Palabras);
		System.out.println(pa);
		v.letraCorrecta_Incorrecta("m", pa);
	}
	@Override
	public String generarPalabra(Random r , String [] palabras) throws RemoteException {
		int numero_aleatorio = r.nextInt(3);
		return palabras[numero_aleatorio];
	}
	@Override
	public boolean letraCorrecta_Incorrecta(String letra,String palabra_generada) throws RemoteException {
		int intentos = 10;
		String caracteres []= palabra_generada.split("");
		boolean resultado = false;
		for (int i = 0; i < caracteres.length; i++) {
			System.out.print(" _ ");
		}
		while(!resultado && intentos <=0) {
			for (int i = 0; i < caracteres.length; i++) {
				if(letra.equalsIgnoreCase(caracteres[i])) {
					caracteres[i] = letra;
					System.out.println(caracteres[i]);
					resultado=true;
				}else {
					System.out.println("Letra Incorrecta ");
					intentos = intentos-1;
					resultado=false;
				}
			}
		}
		return resultado;
	}
}
