public class Personne implements java.io.Serializable{
    protected int age;
    protected String nom;

    public Personne(int a, String n){
        age = a;
        nom = n;
    }
    public String toString(){
	    return ("nom : "+nom+", age : "+age);
    }
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (!(obj instanceof Personne)) return false;
        
        Personne p = (Personne)obj;
        return ((age == p.age) && (nom.equals(p.nom)));
    }
}