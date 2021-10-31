import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class App {

    private App() {}

    public static void getInfoById(API personneService) throws RemoteException{
        Scanner idObj = new Scanner(System.in);
        try {
            System.out.println("Entrer l'id de la personne : ");
            int id = idObj.nextInt();
            Personne personne = personneService.getPersonne(id);
            System.out.println("Reponse: =============   " + "Personne d'identificateur " + id + " = " + personne+ "   =============");
            menuOrExit(personneService);
        } finally {
            idObj.close();
        }
    }

    public static void addPersonne_or_getId(API personneService, int choice) {
        Scanner nomObj = new Scanner(System.in);
        Scanner ageObj = new Scanner(System.in);
        try {
            System.out.println("Entrer le nom : ");
            String nom = nomObj.nextLine();

            System.out.println("Entrer l'age : ");
            int age = ageObj.nextInt();
            Personne personne = new Personne(age, nom);

            switch (choice) {
                case 1:
                    int personneId1;
                    try {
                        personneId1 = personneService.addPersonne(personne);
                        System.out.println("Reponse: =============   " + personneId1+ "   =============");
                        menuOrExit(personneService);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    int personneId2;
                    try {
                        personneId2 = personneService.getId(personne);
                        System.out.println("Reponse: =============   " + "Identificateur de " + personne.nom +", " + personne.age + " ans = " + personneId2+"   =============");
                        menuOrExit(personneService);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        } finally {
            nomObj.close();
            ageObj.close();
        }        
    }

    public static void getPersonne(API personneService) {
        Scanner idObj = new Scanner(System.in);
        try {
            System.out.println("Entrer l'id de la peronne' : ");
            int id = idObj.nextInt();
    
            Personne personne;
            personne = personneService.getPersonne(id);
            System.out.println("Reponse : =============   Personne d'identificateur " + id + " = " + personne + "   =============");
            menuOrExit(personneService);
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally{
            idObj.close();
        }

    }

    public static void menuOrExit(API personneService) throws RemoteException {
        Scanner choiceObj = new Scanner(System.in);
        System.out.println("Continuer ? Si oui appuyer 1, sinon appuyez 0");
        try {
            int choice = choiceObj.nextInt();
            switch (choice) {
                case 1:
                    start(personneService);
                    break;
                default:
                    break;
            }
        }finally {
            choiceObj.close();
        }
        
    }

    public static void start(API personneService) throws RemoteException {
        System.out.println("Que voulez-vous faire ?");
        Scanner choiceObj = new Scanner(System.in);
        try {
            printMenuQuestions();
            int choice = choiceObj.nextInt();

            switch (choice) {
                case 1:
                    addPersonne_or_getId(personneService, choice);
                    break;
                case 2:
                    addPersonne_or_getId(personneService, choice);
                    break;
                case 3:
                    getInfoById(personneService);
                    break;
                default:
                    break;
            }
        } finally {
            choiceObj.close();
        }
    }

    public static void printMenuQuestions() {
        //test code
        MenuQuestions st = new MenuQuestions();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("1", "2", "3");//optional - if not used then there will be no header and horizontal lines
        st.addRow("Ajouter une personne", "Demander l'identificateur d'une personne", "Recuperer les informations concernant une personne");
        st.print();
    }

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            API personneService = (API) registry.lookup(API.SERVICE_NAME);
            start(personneService);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}