import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

public class GestionPatient {
    private static final Scanner input = new Scanner(System.in);
    private static Vector<Patient> patients = new Vector<>();
    private static final String FILE_NAME = "patients.txt";

    static {
        chargerPatientsDepuisFichier();
    }


    public static boolean identifiantExistant(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Patient trouverPatientParIdentifiant(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public static void enregistrerPatient() {

        String id;
        input.nextLine();
        while (true) {
            System.out.print("Identifiant : ");
            id = input.nextLine();

            // Vérifier si l'identifiant existe déjà
            if (identifiantExistant(id)) {
                System.out.println("Erreur : Cet identifiant est déjà utilisé. Veuillez en choisir un autre.");
            } else {
                break; // Identifiant valide, sortir de la boucle
            }
        }

        System.out.print("Nom du patient : ");
        String nom = input.nextLine();

        System.out.print("Prenom du patient : ");
        String prenom = input.nextLine();

        System.out.print("Âge : ");
        int age = input.nextInt();
        input.nextLine();

        System.out.print("Sexe : ");
        String sexe = input.nextLine();

        System.out.print("Adresse : ");
        String adresse = input.nextLine();

        System.out.print("Pathologie du patient");
        String pathologie = input.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = input.nextLine();

        Patient patient = new Patient(id, nom, prenom, age, sexe, adresse, pathologie, motDePasse);
        patients.add(patient);
        sauvegarderPatientsDansFichier();
        System.out.println("Patient ajouté : " + patient);
    }

    public static void rechercherPatient() {
        input.nextLine();
        System.out.print("Entrez l'identifiant du patient à rechercher : ");
        String id = input.nextLine();

        Patient patient = trouverPatientParIdentifiant(id);

        if (patient != null) {
            System.out.println("Patient trouvé : " + patient);
        } else {
            System.out.println("Aucun patient trouvé avec cet identifiant.");
        }
    }


    public static void listerPatients() {
        if (patients.isEmpty()) {
            System.out.println("Aucun patient enregistré.");
        } else {
            System.out.println("Liste des patients :");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    //kantite patient nan systeme nan 

    public static int getNombrePatients() {
        return patients.size();
    }


    public static void modifierPatient() {
        System.out.print("Identifiant du médecin à modifier : ");
        String id = input.nextLine();

        Patient patient = trouverPatientParIdentifiant(id);

        if (patient == null) {
            System.out.println("Erreur : Patient non trouvé.");
            return;
        }

        boolean execution = true;
        while (execution) {
            System.out.println("\nQue souhaitez-vous modifier pour le patient " + patient.getNom() + " ?");
            System.out.println("1. Nom");
            System.out.println("2. Prenom");
            System.out.println("3. Identifiant");
            System.out.println("4. Age");
            System.out.println("5. Adresse");
            System.out.println("6. Pathologie");
            System.out.println("7. Mot de passe");
            System.out.println("8. Quitter");
            System.out.print("Choix : ");
            int choix = input.nextInt();
            input.nextLine(); // Consommer la ligne restante

            switch (choix) {
                case 1:
                    System.out.print("Nouveau nom : ");
                    String nouveauNom = input.nextLine();
                    patient.setNom(nouveauNom);
                    System.out.println("Nom mis à jour avec succès.");
                    break;

                case 2:
                    System.out.print("Nouveau prenom : ");
                    String nouveauPrenom = input.nextLine();
                    patient.setPrenom(nouveauPrenom);
                    System.out.println("Nom mis à jour avec succès.");
                    break;
                case 3:
                    String nouvelIdentifiant;
                    while (true) {
                        System.out.print("Nouvel identifiant : ");
                        nouvelIdentifiant = input.nextLine();
                        if (identifiantExistant(nouvelIdentifiant)) {
                            System.out.println("Erreur : Cet identifiant est déjà utilisé. Veuillez en choisir un autre.");
                        } else {
                            patient.setId(nouvelIdentifiant);
                            System.out.println("Identifiant mis à jour avec succès.");
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.print("Nouvel age : ");
                    int nouvelAge = input.nextInt();
                    patient.setAge(nouvelAge);
                    System.out.println("Age mis à jour avec succès.");
                    break;

                case 5:
                    System.out.print("Nouvel adresse : ");
                    String nouvelAdresse = input.nextLine();
                    patient.setAdresse(nouvelAdresse);
                    System.out.println("Adresse mis à jour avec succès.");
                    break;

                case 6:
                    System.out.print("Nouveau pathologie : ");
                    String nouveauPathologie = input.nextLine();
                    patient.setPathologie(nouveauPathologie);
                    System.out.println("Pathologie mis à jour avec succès.");
                    break;
                case 7:
                    System.out.print("Nouveau mot de passe : ");
                    String nouveauMotDePasse = input.nextLine();
                    patient.setMotDePasse(nouveauMotDePasse);
                    System.out.println("Mot de passe mis à jour avec succès.");
                    break;
                case 8:
                    execution = false;
                    System.out.println("Modification terminée.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }


//    public static void gestionPat()
//    {
//        boolean execution = true;
//
//        while (execution)
//        {
//            System.out.println("\nBienvenue a la section Gestion des patients");
//            System.out.println("1. Ajouter un patient");
//            System.out.println("2. Modifier un patient");
//            System.out.println("3. Lister les patients");
//            System.out.println("4. Rechercher un patient");
//            System.out.println("5. Donner un rendez-vous");
//            System.out.println("6. Donner un prescription");
//            System.out.println("7. Lister les rendez-vous");
//            System.out.println("8. Lister les rendez-vous");
//            System.out.println("9. Modifier une prescription");
//            System.out.println("10. Modifier un rendez-vous");
//            System.out.println("11. Quitter");
//            System.out.print("Faire un choix : ");
//            int choix = input.nextInt();
//            switch(choix)
//            {
//                case 1: enregistrerPatient();
//                    break;
//
//                case 2: modifierPatient();
//                    break;
//
//                case 3: listerPatients();
//                    break;
//
//                case 4: rechercherPatient();
//                    break;
//
//                case 5: GestionRendezVous.enregistrerRendezVous();
//                    break;
//
//                case 6: GestionPrescription.enregistrerPrescription();
//                    break;
//
//                case 7: GestionRendezVous.listerRendezVous();
//                    break;
//
//                case 8: GestionPrescription.listerPrescription();
//                    break;
//
//                case 9: GestionPrescription.modifierPrescription();
//                    break;
//
//                case 10: GestionRendezVous.modifierRendezVous();
//                    break;
//
//                case 11: execution = false;
//                    break;
//            }
//        }
//
//    }



    public static void gestionPatAdm()
    {
        boolean execution = true;

        while (execution)
        {
            System.out.println("\nBienvenue a la section Gestion des patients");
            System.out.println("1. Modifier un patient");
            System.out.println("2. Lister les patients");
            System.out.println("3. Rechercher un patient");
            System.out.println("4. Lister les rendez-vous");
            System.out.println("5. Lister les prescriptions");
            System.out.println("6. Modifier une prescription");
            System.out.println("7. Modifier un rendez-vous");
            System.out.println("8. Quitter");
            System.out.print("Faire un choix : ");
            int choix = input.nextInt();
            switch(choix)
            {

                case 1: modifierPatient();
                    break;

                case 2: listerPatients();
                    break;

                case 3: rechercherPatient();
                    break;

                case 4: GestionRendezVous.listerRendezVous();
                    break;

                case 5: GestionPrescription.listerPrescription();
                    break;

                case 6: GestionPrescription.modifierPrescription();
                    break;

                case 7: GestionRendezVous.modifierRendezVous();
                    break;

                case 8: execution = false;
                    break;
            }
        }

    }




    private static void sauvegarderPatientsDansFichier() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(patients);
            System.out.println("Les patients ont été sauvegardés dans le fichier.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des patients : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void chargerPatientsDepuisFichier() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            patients = (Vector<Patient>) ois.readObject();
            System.out.println("Les patients ont été chargés depuis le fichier.");
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier trouvé. Un nouveau fichier sera créé lors de la sauvegarde.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des patients : " + e.getMessage());
        }
    }
}

