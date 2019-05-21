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
	static int intentos = 10;
	public static void main(String[] args) throws UnknownHostException, RemoteException {
		Registry registro = null;
		Servidor v = new Servidor();
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
	@Override
	public String generarPalabra(Random r , String [] palabras) throws RemoteException {
		int numero_aleatorio = r.nextInt(3);
		return palabras[numero_aleatorio];
	}
	@Override
	public boolean letraCorrecta_Incorrecta(String letra,String palabra_generada) throws RemoteException {
		String caracteres []= palabra_generada.split("");
		boolean resultado = false;
		while(intentos >=0 && !resultado) {
			for (int j = 0; j < caracteres.length; j++) {
				if (letra.equalsIgnoreCase(caracteres[j])) {
					System.out.print(letra+"");
					resultado=true; 
				}else {
					System.out.print("_"+"");
					resultado=false;
				}
			}
		}
		return resultado;
	}
}
