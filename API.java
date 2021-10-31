
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface API extends Remote {
    // Lets us define API

    static final String SERVICE_NAME = "personneAPI";

    public int addPersonne(Personne p) throws RemoteException;
    public int getId(Personne p) throws RemoteException;
    public Personne getPersonne(int id) throws RemoteException;
}