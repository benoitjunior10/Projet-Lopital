import java.io.Serializable;
public class Medecin implements Serializable{
    private static final long serialVersionUID = 1L;
    private String id;
    private String nom;
    private String prenom;
    private String sexe;
    private String specialisation;
    private String motDePasse;
    private boolean actif;

    public Medecin(){}

    public Medecin(String id, String nom, String prenom, String sexe,String specialisation, String motDePasse, boolean actif) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.specialisation = specialisation;
        this.motDePasse = motDePasse;
        this.actif = true;
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

    public String getSexe() {
        return sexe;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public boolean isActif() {
        return actif;
    }

    public void activer() {
        actif = true;
    }

    public void revoquer() {
        actif = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    @Override
    public String toString() {
        return "Médecin: " + nom + " " + prenom +", Identifiant: " + id + ", Sexe : " + sexe + ", Specialisation : " + specialisation + ", Statut: " + (actif ? "Actif" : "Révoqué");
    }
}
