import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;


public class GestionRendezVous {
    private static final Scanner input = new Scanner(System.in);
    private static Vector<RendezVous> rendezVousL = new Vector<>();
    private static final String FILE_NAME = "rendezVous.xlsx";

    static{
        chargerRendezVousDepuisFichierExcel();
    }


    public static RendezVous trouverRendezVousParIdentifiant(String id) {
        for (RendezVous rendezVous : rendezVousL) {
            if (rendezVous.getId().equals(id)) {
                return rendezVous;
            }
        }
        return null;
    }

    private static boolean rendezVousExistant(String id) {
        for (RendezVous rendezVous : rendezVousL) {
            if (rendezVous.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void enregistrerRendezVous() {

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
            System.out.print("ID du rendez-vous : ");
            id = input.nextLine();

            if (rendezVousExistant(id)) {
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
        System.out.print("Date (JJ/MM/AAAA) : ");
        String date = input.nextLine();
        System.out.print("Heure : ");
        String heure = input.nextLine();

        RendezVous rendezVous = new RendezVous(id, patientId, medecinId, date, heure);
        rendezVousL.add(rendezVous);
        sauvegarderRendezVousDansFichierExcel();
        System.out.println("Rendez-vous enregistré : " + rendezVous);
    }

    public static void enregistrerRendezVousPourMedecin(String medecinId) {

        String id;

        while (true) {
            System.out.print("ID du rendez-vous : ");
            id = input.nextLine();

            if (rendezVousExistant(id)) {
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

        System.out.print("Date (JJ/MM/AAAA) : ");
        String date = input.nextLine();
        System.out.print("Heure : ");
        String heure = input.nextLine();

        RendezVous rendezVous = new RendezVous(id, patientId, medecinId, date, heure);
        rendezVousL.add(rendezVous);
        sauvegarderRendezVousDansFichierExcel();
        System.out.println("Rendez-vous enregistré : " + rendezVous);
    }

    public static void listerRendezVous() {
        if (rendezVousL.isEmpty()) {
            System.out.println("Aucun rendez-vous enregistré.");
        } else {
            System.out.println("Liste des rendez-vous :");
            for (RendezVous rdv : rendezVousL) {
                System.out.println(rdv);
            }
        }
    }

    public static void listerRendezVousPourMedecin(Medecin medecin) {
        boolean rendezVousTrouves = false;
        System.out.println("Rendez-vous pour le médecin avec ID : " + medecin.getNom() + " " + medecin.getPrenom() + " :");
        for (RendezVous rendezVous : rendezVousL) {
            if (rendezVous.getMedecinId().equals(medecin.getId())) {
                System.out.println(rendezVous);
                rendezVousTrouves = true;
            }
        }
        if (!rendezVousTrouves) {
            System.out.println("Aucun rendez-vous trouvé pour ce médecin.");
        }
    }

    public static void listerRendezVousPourPatient(Patient patient) {
        boolean rendezVousTrouves = false;
        System.out.println("Rendez-vous pour le patient " + patient.getNom() + " " + patient.getPrenom() + " :");
        for (RendezVous rendezVous : rendezVousL) {
            if (rendezVous.getPatientId().equals(patient.getId())) {
                System.out.println(rendezVous);
                rendezVousTrouves = true;
            }
        }
        if (!rendezVousTrouves) {
            System.out.println("Aucun rendez-vous trouvé pour ce patient.");
        }
    }

    public static void modifierRendezVous() {
        System.out.print("ID du rendez-vous à modifier : ");
        String id = input.nextLine();

        RendezVous rendezVous = trouverRendezVousParIdentifiant(id);

        if (rendezVous == null) {
            System.out.println("Erreur : Rendez-vous non trouvé.");
            return;
        }

        boolean execution = true;
        while (execution) {
            System.out.println("\nQue souhaitez-vous modifier pour le rendez-vous ?");
            System.out.println("1. Date");
            System.out.println("2. Heure");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            int choix = input.nextInt();
            input.nextLine();

            switch (choix) {
                case 1:
                    System.out.print("Nouvelle date (JJ/MM/AAAA) : ");
                    String nouvelleDate = input.nextLine();
                    rendezVous.setDate(nouvelleDate);
                    System.out.println("Date mise à jour avec succès.");
                    sauvegarderRendezVousDansFichierExcel();
                    break;

                case 2:
                    System.out.print("Nouvelle heure : ");
                    String nouvelleHeure = input.nextLine();
                    rendezVous.setHeure(nouvelleHeure);
                    System.out.println("Heure mise à jour avec succès.");
                    sauvegarderRendezVousDansFichierExcel();
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



    public static void sauvegarderRendezVousDansFichierExcel() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("RendezVous");

        // Créer une ligne d'en-tête
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID Rendez-vous");
        headerRow.createCell(1).setCellValue("ID Patient");
        headerRow.createCell(2).setCellValue("ID Médecin");
        headerRow.createCell(3).setCellValue("Date");
        headerRow.createCell(4).setCellValue("Heure");

        // Ajouter les données des rendez-vous
        int rowNum = 1;
        for (RendezVous rendezVous : rendezVousL) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rendezVous.getId());
            row.createCell(1).setCellValue(rendezVous.getPatientId());
            row.createCell(2).setCellValue(rendezVous.getMedecinId());
            row.createCell(3).setCellValue(rendezVous.getDate());
            row.createCell(4).setCellValue(rendezVous.getHeure());
        }

        // Ajuster la largeur des colonnes
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        // Écrire les données dans le fichier Excel
        try (FileOutputStream fileOut = new FileOutputStream(FILE_NAME)) {
            workbook.write(fileOut);
            System.out.println("Les rendez-vous ont été sauvegardés dans le fichier Excel : " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des rendez-vous dans le fichier Excel : " + e.getMessage());
        }
    }


    private static void chargerRendezVousDepuisFichierExcel() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Ignore la ligne d'en-tête
                String id = row.getCell(0).getStringCellValue();
                String patientId = row.getCell(1).getStringCellValue();
                String medecinId = row.getCell(2).getStringCellValue();
                String date = row.getCell(3).getStringCellValue();
                String heure = row.getCell(4).getStringCellValue();
                rendezVousL.add(new RendezVous(id, patientId, medecinId, date, heure));
            }
            System.out.println("Les rendez-vous ont été chargés depuis le fichier.");
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier trouvé. Un nouveau fichier sera créé lors de la sauvegarde.");
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des rendez-vous : " + e.getMessage());
        }
    }




}

