public class Administrateur {
    private String nom;
    private String identifiant;
    private String motDePasse;
    private String role; // "admin", "medecin", "patient"

    public Administrateur(String nom, String identifiant, String motDePasse, String role) {
        this.nom = nom;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getRole() {
        return role;
    }

    public boolean verifierMotDePasse(String motDePasse) {
        return this.motDePasse.equals(motDePasse);
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Identifiant: " + identifiant + ", Rôle: " + role;
    }
}
