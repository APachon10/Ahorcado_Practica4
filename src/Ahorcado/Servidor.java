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
	static String palabra_generada = "";
	static int intentos = 10;
	public static void main(String[] args) throws UnknownHostException, RemoteException {
		Registry registro = null;
		Servidor v = new Servidor(palabra_generada);
		System.setProperty("java.rmi.server.hostname",InetAddress.getLocalHost().getHostAddress());

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
	public  Servidor(String palabra) throws RemoteException {
		palabra = generarPalabra(Palabras);
	}
	@Override
	public  String generarPalabra(String [] palabras) throws RemoteException {
		Random r = new Random();
		int numero_aleatorio = r.nextInt(3);
		return palabras[numero_aleatorio];
	}
	@Override
	public String letraCorrecta_Incorrecta(String letra,String palabra_generada) throws RemoteException {
		String caracteres []= palabra_generada.split("");
		
		String res = "_";
		
		for (int i = 0; i < caracteres.length; i++) {
			if(letra == caracteres[i]) {
				System.out.println(caracteres[i] +"");
			}else {
				System.out.println("_"+"");
			}
		}
		return res;
	}
}
