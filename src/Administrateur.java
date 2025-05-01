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

    public static void afficherStatistiques() {
        System.out.println("");
        System.out.println("Nombre de médecins : " + GestionMedecin.getNombreMedecins());
        System.out.println("Nombre de patients : " + GestionPatient.getNombrePatients());
        System.out.println("Nombre de rendez-vous : " + GestionRendezVous.getNombreRendezVous());
        System.out.println("Nombre de prescription : " + GestionPrescription.getNombrePrescription());
        System.out.println("");
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
