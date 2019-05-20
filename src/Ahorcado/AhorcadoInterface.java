package Ahorcado;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Random;

public interface AhorcadoInterface extends Remote {
	public String generarPalabra(Random r, String array []) throws RemoteException;
	public boolean letraCorrecta_Incorrecta(String letra) throws RemoteException;
	
}
