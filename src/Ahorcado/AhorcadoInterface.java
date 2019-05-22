package Ahorcado;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Random;

public interface AhorcadoInterface extends Remote {
	public String generarPalabra(String array []) throws RemoteException;
	public String  letraCorrecta_Incorrecta(String letra,String palabra_generada) throws RemoteException;
	
}
