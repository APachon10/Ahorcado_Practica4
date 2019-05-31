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
	static int intentos = 5;
	public static void main(String[] args) throws UnknownHostException, RemoteException {
		Registry registro = null;
		Servidor v = new Servidor();

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
	public  Servidor() throws RemoteException {
		palabra_generada = generarPalabra(Palabras);
	}
	@Override
	public String generarPalabra(String [] palabras) throws RemoteException {
		Random r = new Random();
		int numero_aleatorio = r.nextInt(3);
		return palabras[numero_aleatorio];
	}
	@Override
	public String compararLetra(String letra) throws RemoteException {
		String caracteres []= palabra_generada.split("");
		String res = "";
		if(letraAcertada(letra)==true) {
			for (int j = 0; j < caracteres.length; j++) {
				if(letra.equalsIgnoreCase(caracteres[j])) {
					res=caracteres[j];
					System.out.print(res+"");
				}else {
					res="_";
					System.out.print(res);
				}
			}
		}else {
			intentos = intentos -1;
		}
		System.out.println("\n========================");
		return res;
	}
	@Override
	public boolean letraAcertada(String letra) {
		String caracteres []= palabra_generada.split("");
		boolean salir=false;
		int i=0;
		while(!salir) {
			if(intentos <=0) {
				if(i<caracteres.length) {
					if(letra.equalsIgnoreCase(caracteres[i])) {
						salir=true;
					}else {
						salir=false;
						i=i+1;
					}
				}else {
					salir=true;
				}
			}else {
				salir=true;
			}
		}
		return salir;
	}
}
