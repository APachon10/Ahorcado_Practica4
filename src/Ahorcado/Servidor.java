package Ahorcado;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Servidor implements AhorcadoInterface {
	static String [] Palabras = {"Alberto","Anime","Manga"};
	static String palabra_generada="";
	static int intentos = 2;
	public static void main(String[] args) throws UnknownHostException, RemoteException {
		Registry registro = null;
		Servidor v = new Servidor();
		System.out.println(palabra_generada);
		System.setProperty("java.rmi.server.hostname",InetAddress.getLocalHost().getHostAddress());
		System.out.println("Servidor Listo");
		try {
			registro = LocateRegistry.createRegistry(5555);
			registro.rebind("Ahorcado",
					(AhorcadoInterface) UnicastRemoteObject.exportObject(v, 0));
		} catch (Exception e) {
			System.out.println("Error : "+e);
			e.printStackTrace();
		}
	}
	//Constructor
	public  Servidor() throws RemoteException {
		if(this.palabra_generada.equals("")) {
			this.palabra_generada = generarPalabra(Palabras);
		}
	}
	@Override
	public  String generarPalabra(String [] palabras) throws RemoteException {
		Random r = new Random();
		int numero_aleatorio = r.nextInt(3);
		return palabras[numero_aleatorio];
	}
	@Override
	public String compararLetra(String letra) throws RemoteException {
		String caracteres []= palabra_generada.split("");
		String res = "";
		System.out.println("Hola: " +palabra_generada);
		for (int i = 0; i < caracteres.length; i++) {
			if(letra == caracteres[i]) {
				res = res+ caracteres[i];
				System.out.print(caracteres[i] +"");
			}else {
				res=res+"_";
				System.out.print("_"+"");
				intentos = intentos -1;
			}
		}
		return res;
	}
}
