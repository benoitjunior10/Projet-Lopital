import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        Administrateur admin = new Administrateur("Admin", "admin", "admin123", "admin");


        boolean execution = true;

        while (execution) {
            System.out.println("\nBienvenue dans l'application de gestion hospitalière.");
            System.out.println("Veuillez choisir votre rôle :");
            System.out.println("1. Administrateur");
            System.out.println("2. Médecin");
            System.out.println("3. Patient");
            System.out.println("4. Quitter");
            System.out.print("Choix : ");
            int choix = input.nextInt();
            input.nextLine();

            switch (choix) {
                case 1:
                    connexionAdministrateur(admin);
                    break;
                case 2:
                    connexionMedecin();
                    break;
                case 3:
                    connexionPatient();
                    break;
                case 4:
                    execution = false;
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }

        input.close();
    }

    private static void connexionAdministrateur(Administrateur admin) {
        System.out.println("\nConnexion Administrateur :");
        String identifiant;
        while (true) {
            System.out.print("Identifiant : ");
            identifiant = input.nextLine();

            if (!admin.getIdentifiant().equalsIgnoreCase(identifiant)) {
                System.out.println("Erreur : identifiant incorrect. Veuillez rentrer a nouveau.");
            } else {
                break;
            }
        }

        String motDePasse;
        while (true) {
            System.out.print("Mot de passe : ");
            motDePasse = input.nextLine();

            if (!admin.verifierMotDePasse(motDePasse)) {
                System.out.println("Erreur : mot de passe incorrect. Veuillez rentrer le mot de passe a nouveau.");
            } else {
                break;
            }
        }

        System.out.println("");

        boolean execution = true;

        while (execution)
        {
            System.out.println("Bienvenue Admin");
            System.out.println("1. Gestion des medecins");
            System.out.println("2. Gestion des patients");
            System.out.println("3. Afficher les statistiques");
            System.out.println("4. Deconnexion");
            System.out.print("Faire un choix : ");
            int choix = input.nextInt();
            switch(choix)
            {
                case 1: GestionMedecin.gestionMed();
                    break;

                case 2: GestionPatient.gestionPatAdm();
                    break;

                case 3: Administrateur.afficherStatistiques();
                break;

                case 4: execution = false;
                    System.out.println("Deconnecter avec succes. A bientot cher admin");
                    break;

                default: System.out.println("Choix Invalide");
            }
        }

    }


    private static void connexionMedecin() {
        System.out.println("\nConnexion Médecin :");
        System.out.print("Identifiant : ");
        String id = input.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = input.nextLine();

        Medecin medecin = GestionMedecin.trouverMedecinParIdentifiant(id);

        if (medecin != null && medecin.getMotDePasse().equals(motDePasse)) {
            if (medecin.isActif()) {
                System.out.println("Connexion réussie. Bienvenue, Dr "+ medecin.getNom() + " " + medecin.getPrenom());

                boolean execution = true;
                while (execution) {
                    System.out.println("\nQue souhaitez-vous faire ?");
                    System.out.println("1. Enregistrer un patient");
                    System.out.println("2. Rechercher un patient");
                    System.out.println("3. Afficher la liste des patients");
                    System.out.println("4. Enregistrer une prescription");
                    System.out.println("5. Enregistrer un rendez-vous");
                    System.out.println("6. Voir les rendez-vous que j'ai donner");
                    System.out.println("7. Voir les prescriptions que j'ai donner");
                    System.out.println("8. Deconnexion");
                    System.out.print("Choix : ");
                    int choix = input.nextInt();
                    input.nextLine();

                    switch (choix) {
                        case 1:
                            GestionPatient.enregistrerPatient();
                            break;
                        case 2:
                            GestionPatient.rechercherPatient();
                            break;
                        case 3:
                            GestionPatient.listerPatients();
                            break;
                        case 4:
                            GestionPrescription.enregistrerPrescriptionPourMedecin(medecin.getId());
                            break;
                        case 5:
                            GestionRendezVous.enregistrerRendezVousPourMedecin(medecin.getId());
                            break;
                        case 6:
                            GestionRendezVous.listerRendezVousPourMedecin(medecin);
                            break;
                        case 7:
                            GestionPrescription.listerPrescriptionsPourMedecin(medecin);
                            break;
                        case 8:
                            execution = false;
                            System.out.println("Deconnecter avec succes. A bientot Dr " + medecin.getNom() + " " + medecin.getPrenom());
                            break;
                        default:
                            System.out.println("Choix invalide.");
                    }
                }
            } else {
                System.out.println("Votre compte est révoqué. Veuillez contacter l'administrateur.");
            }
        } else {
            System.out.println("Identifiant ou mot de passe incorrect.");
        }
    }

    private static void connexionPatient()
    {
        System.out.println("\nConnexion Patient :");
        System.out.print("Identifiant : ");
        String id = input.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = input.nextLine();

        Patient patient = GestionPatient.trouverPatientParIdentifiant(id);

        if (patient != null && patient.getMotDePasse().equals(motDePasse))
        {
            System.out.println("Connexion réussie. Bienvenue, Patient " + patient.getNom() + " " + patient.getPrenom());
            boolean execution = true;
            while (execution) {
                System.out.println("\nQue souhaitez-vous faire ?");
                System.out.println("1. Voir mes prescriptions");
                System.out.println("2. Voir mes rendez-vous");
                System.out.println("3. Deconnexion");
                System.out.print("Choix : ");
                int choix = input.nextInt();
                input.nextLine();
                switch (choix) {
                    case 1: GestionPrescription.listerPrescriptionsPourPatient(patient);
                        break;

                    case 2: GestionRendezVous.listerRendezVousPourPatient(patient);
                        break;

                    case 3: execution = false;
                        System.out.println("A bientot patient " + patient.getNom() + " " + patient.getPrenom());
                        break;

                    default: System.out.println("Choix invalide");
                }
            }
        } else {
            System.out.println("Identifiant ou mot de passe incorrect.");
        }

    }








}

