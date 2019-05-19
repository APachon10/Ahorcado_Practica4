package Ahorcado;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements AhorcadoInterface {
	static String [] Palabras = {"Alberto","Anime","Manga"};
	public static void main(String[] args) {
		Registry registro = null;
		Servidor v = new Servidor();
		System.setProperty("java.rmi.server.hostname","192.168.0.12");
		
		try {
			registro = LocateRegistry.createRegistry(1234);
			registro.rebind("Ahorcado",
			(AhorcadoInterface) UnicastRemoteObject.exportObject(v, 0));
		} catch (Exception e) {
			System.out.println("Error : "+e);
			e.printStackTrace();
		}
		
	}
	@Override
	public String letra() throws RemoteException {
		String letra = "";
		
		return letra;
	}
}
