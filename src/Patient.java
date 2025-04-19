import java.io.Serializable;

public class Patient implements Serializable{
    private String id;
    private String nom;
    private String prenom;
    private int age;
    private String sexe;
    private String adresse;
    private String pathologie;
    private String motDePasse;

    public Patient(){}

    public Patient(String id, String nom, String prenom, int age, String sexe, String adresse, String pathologie, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.adresse = adresse;
        this.pathologie = pathologie;
        this.motDePasse = motDePasse;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public String getSexe() {
        return sexe;
    }

    public String adresse(){
        return adresse;
    }

    public String getPathologie() {
        return pathologie;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setPathologie(String pathologie) {
        this.pathologie = pathologie;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Patient: " + nom + " " + prenom +", Identifiant: " + id + ", Age : " + age + ", Sexe : " + sexe + ", Adresse : " + adresse + ", Pathologie : " + pathologie;
    }

}

