
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {
	public static void main(String [] args) {
        System.out.println("Server is booting....");
        System.setProperty("java.rmi.server.hostname","127.0.0.1"); 
		try {
            DataManager obj = new DataManager();
            API stub = (API) UnicastRemoteObject.exportObject(obj, 0);
            // Get the RMI registry.
            Registry registry = LocateRegistry.getRegistry();
            System.out.println("RMI registry is running");

            // create an instance of the service object
            System.out.println("Binding PersonneService...");

            // bind it in the RMI registry
            registry.bind(API.SERVICE_NAME, stub);

            System.out.println("PersonneService is ready.");

		} catch (Exception e) {
			System.out.println("Server error" + e);

		}
	}
}