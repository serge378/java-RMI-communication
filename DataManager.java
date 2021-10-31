import java.util.Vector;
/**
 * Classe qui gère un ensemble de Personne via 3 opérations.
 */
public class DataManager implements API
{ 

   protected DataManager() {}

/**
    * Vecteur qui contient les personnes
    */
    protected Vector<Personne> personneVector = new Vector<Personne>();
    
    /**
     * Ajoute une personne dans la liste et retourne son
     * identificateur. Si la personne existait déjà, retourne
     * l'identificateur qu'elle avait dans la liste.
     * @param p la personne à ajouter dans la liste
     * @return l'identificateur de la personne 
     */
    public int addPersonne(Personne p){
        if (personneVector.indexOf(p) == -1) 
            personneVector.add(p);
            System.out.println("=============   Ajout de % "+p+" % avec identifiant = "+ personneVector.indexOf(p)+"   =============");
        return (personneVector.indexOf(p));
    }
    

    /**
     * Récupère une personne dans la liste à partir de son
     * identifiant.
     * @param id l'idenfiant de la personne à récupérer
     * @throws InvalidIdException dans le cas où l'identifiant
     * n'est attribué à aucune personne
     */
    public Personne getPersonne(int id){
        if (id >= personneVector.size()) {
            System.out.println("=============   Identifiant invalide : "+id+"   =============");
            return new Personne(-1, "null");
        }
        return (personneVector.elementAt(id));
    }

    /**
     * Récupére l'identifiant d'une personne. 
     * @param p la personne dont on veut récupérer l'identifiant
     * @return l'identificateur de la personne. Si la personne n'est
     * pas dans la liste, retourne -1.
     */ 
    public int getId(Personne p){
        if (personneVector.indexOf(p) == -1) System.out.println("=============   Personne non trouvée   =============");
	    return personneVector.indexOf(p);
    }

}

