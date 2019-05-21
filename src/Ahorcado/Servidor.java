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
		int i=0;
		while(intentos >=0 && !resultado) {
			for (int j = 0; j < caracteres.length; j++) {
				if (letra.equalsIgnoreCase(caracteres[j])) {
					System.out.print(letra);
					resultado=true;
				}else {
					System.out.print("_");
					resultado=false;
				}
			}
		}
		return resultado;
	}
}
