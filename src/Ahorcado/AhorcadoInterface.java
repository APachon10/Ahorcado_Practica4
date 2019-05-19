package Ahorcado;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AhorcadoInterface extends Remote {
	public String letra () throws RemoteException;
}
