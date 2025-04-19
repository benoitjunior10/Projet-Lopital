import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

public class GestionPrescription {
    private static final Scanner input = new Scanner(System.in);
    private static Vector<Prescription> prescriptionL = new Vector<>();
    private static final String FILE_NAME = "prescription.txt";

    static {
        chargerPrescriptionDepuisFichier();
    }

    public static Prescription trouverPrescriptionParIdentifiant(String id) {
        for (Prescription prescription : prescriptionL) {
            if (prescription.getId().equals(id)) {
                return prescription;
            }
        }
        return null;
    }

    private static boolean PrescriptionExistant(String id) {
        for (Prescription prescription : prescriptionL) {
            if (prescription.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void enregistrerPrescription() {

        String medecinId;
        while (true) {
            System.out.print("ID du medecin : ");
            medecinId = input.nextLine();

            if (!GestionMedecin.identifiantExistant(medecinId)) {
                System.out.println("Erreur : Ce medecin n'existe pas. Veuillez entrer un autre identifiant.");
            } else {
                break;
            }
        }


        String id;

        while (true) {
            System.out.print("ID du prescription : ");
            id = input.nextLine();

            if (PrescriptionExistant(id)) {
                System.out.println("Erreur : Cet identifiant est déjà utilisé. Veuillez en choisir un autre.");
            } else {
                break;
            }
        }



        String patientId;

        while (true) {
            System.out.print("ID du patient : ");
            patientId = input.nextLine();

            if (!GestionPatient.identifiantExistant(patientId)) {
                System.out.println("Erreur : Ce patient n'existe pas. Veuillez entrer un autre identifiant.");
            } else {
                break;
            }
        }

        System.out.print("Medicament : ");
        String medicament = input.nextLine();
        System.out.print("Dosage : ");
        String dosage = input.nextLine();

        Prescription prescription = new Prescription(id, patientId, medecinId, medicament, dosage);
        prescriptionL.add(prescription);
        sauvegarderPrescriptionDansFichier();
        System.out.println("Rendez-vous enregistré : " + prescription);
    }

    public static void enregistrerPrescriptionPourMedecin(String medecinId) {


        String id;

        while (true) {
            System.out.print("ID du prescription : ");
            id = input.nextLine();

            if (PrescriptionExistant(id)) {
                System.out.println("Erreur : Cet identifiant est déjà utilisé. Veuillez en choisir un autre.");
            } else {
                break;
            }
        }

        String patientId;

        while (true) {
            System.out.print("ID du patient : ");
            patientId = input.nextLine();

            if (!GestionPatient.identifiantExistant(patientId)) {
                System.out.println("Erreur : Ce patient n'existe pas. Veuillez entrer un autre identifiant.");
            } else {
                break;
            }
        }

        System.out.print("Médicament : ");
        String medicament = input.nextLine();
        System.out.print("Dosage : ");
        String dosage = input.nextLine();

        // Création de l'objet Prescription
        Prescription prescription = new Prescription(id, patientId, medecinId, medicament, dosage);
        prescriptionL.add(prescription);

        sauvegarderPrescriptionDansFichier();

        System.out.println("Prescription enregistrée : " + prescription);
    }


    public static void listerPrescription() {
        if (prescriptionL.isEmpty()) {
            System.out.println("Aucun prescription enregistré.");
        } else {
            System.out.println("Liste des prescriptions :");
            for (Prescription pres : prescriptionL) {
                System.out.println(pres);
            }
        }
    }

    public static void listerPrescriptionsPourMedecin(Medecin medecin) {
        boolean prescriptionsTrouvees = false;
        System.out.println("Prescriptions pour Dr " + medecin.getNom() + " " + medecin.getPrenom() + " :");
        for (Prescription prescription : prescriptionL) {
            if (prescription.getMedecinId().equals(medecin.getId())) {
                System.out.println(prescription);
                prescriptionsTrouvees = true;
            }
        }
        if (!prescriptionsTrouvees) {
            System.out.println("Aucune prescription trouvée pour vous.");
        }
    }

    public static void listerPrescriptionsPourPatient(Patient patient) {
        boolean prescriptionsTrouvees = false;
        System.out.println("Prescriptions pour le patient " + patient.getNom() + " " + patient.getPrenom() + " :");
        for (Prescription prescription : prescriptionL) {
            if (prescription.getPatientId().equals(patient.getId())) {
                System.out.println(prescription);
                prescriptionsTrouvees = true;
            }
        }
        if (!prescriptionsTrouvees) {
            System.out.println("Aucune prescription trouvée pour ce patient.");
        }
    }




    public static void modifierPrescription() {
        System.out.print("ID de la prescription à modifier : ");
        String id = input.nextLine();

        Prescription prescription = trouverPrescriptionParIdentifiant(id);

        if (prescription == null) {
            System.out.println("Erreur : Prescription non trouvée.");
            return;
        }

        boolean execution = true;
        while (execution) {
            System.out.println("\nQue souhaitez-vous modifier pour la prescription ?");
            System.out.println("1. Médicament");
            System.out.println("2. Dosage");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            int choix = input.nextInt();
            input.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nouveau médicament : ");
                    String nouveauMedicament = input.nextLine();
                    prescription.setMedicament(nouveauMedicament);
                    System.out.println("Médicament mis à jour avec succès.");
                    break;

                case 2:
                    System.out.print("Nouveau dosage : ");
                    String nouveauDosage = input.nextLine();
                    prescription.setDosage(nouveauDosage);
                    System.out.println("Dosage mis à jour avec succès.");
                    break;

                case 3:
                    execution = false;
                    System.out.println("Modification terminée.");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }




    private static void sauvegarderPrescriptionDansFichier() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(prescriptionL);
            System.out.println("Les prescriptions ont été sauvegardés dans le fichier.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des rendez-vous : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void chargerPrescriptionDepuisFichier() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            prescriptionL = (Vector<Prescription>) ois.readObject();
            System.out.println("Les prescriptions ont été chargés depuis le fichier.");
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier trouvé. Un nouveau fichier sera créé lors de la sauvegarde.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des rendez-vous : " + e.getMessage());
        }
    }

}


