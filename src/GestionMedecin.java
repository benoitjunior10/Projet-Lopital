import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;
public class GestionMedecin {
    private static final Scanner input = new Scanner(System.in);
    private static Vector<Medecin> medecins = new Vector<>();
    private static final String FILE_NAME = "medecins.txt";

    static {
        chargerMedecinsDepuisFichier();
    }

    public static boolean identifiantExistant(String id) {
        for (Medecin medecin : medecins) {
            if (medecin.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Medecin trouverMedecinParIdentifiant(String id) {
        for (Medecin medecin : medecins) {
            if (medecin.getId().equals(id)) {
                return medecin;
            }
        }
        return null;
    }

    public static void ajouterMedecin() {
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

        System.out.print("Nom du médecin : ");
        String nom = input.nextLine();

        System.out.print("Prenom du medecin : ");
        String prenom = input.nextLine();

        System.out.println("Sexe du médecin : ");
        System.out.println("1. Masculin");
        System.out.println("2. Féminin");
        String sexe;
        while (true) {
            int choixSexe = input.nextInt();
            input.nextLine(); // Consommer la nouvelle ligne
            if (choixSexe == 1) {
                sexe = "Masculin";
                break;
            } else if (choixSexe == 2) {
                sexe = "Féminin";
                break;
            } else {
                System.out.println("Choix invalide. Veuillez choisir 1 ou 2.");
            }
        }

        //specialisation

        System.out.println("Specialisation du medecin : ");
        System.out.println("1. Généraliste");
        System.out.println("2. Cardiologie");
        System.out.println("3. Dermatologie");
        System.out.println("4. Pédiatrie");
        System.out.println("5. Chirurgie");
        System.out.println("6. Ophtalmologie");
        System.out.println("7. Gynécologie");
        System.out.println("8. Urologie");
        System.out.println("9. Infectiologie");

        String specialisation;
        while (true) {
            int choixSpecialisation = input.nextInt();
            input.nextLine(); // Consommer la nouvelle ligne
            if (choixSpecialisation == 1) {
                specialisation = "Généraliste";
                break;
            } else if (choixSpecialisation == 2) {
                specialisation = "Cardiologie";
                break;
            } else if(choixSpecialisation == 3){
                specialisation = "Dermatologie";
                break;
            } else if(choixSpecialisation == 4){
                specialisation = "Pédiatrie";
                break;
            } else if(choixSpecialisation == 5){
                specialisation = "Chirurgie";
                break;
            } else if(choixSpecialisation == 6){
                specialisation = "Ophtalmologie";
                break;
            } else if(choixSpecialisation == 7){
                specialisation = "Gynécologie";
                break;
            } else if(choixSpecialisation == 8){
                specialisation = "Urologie";
                break;
            } else if(choixSpecialisation == 9){
                specialisation = "Infectiologie";
                break;
            } else {
                System.out.println("Choix invalide. Veuillez choisir 1 jusqu'a 9.");
            }
        }

        System.out.print("Mot de passe : ");
        String motDePasse = input.nextLine();
        System.out.println("");
        boolean actif = true;
        Medecin medecin = new Medecin(id, nom, prenom, sexe, specialisation, motDePasse, actif);

        medecins.add(medecin);
        sauvegarderMedecinsDansFichier();

        System.out.print("Médecin " + medecin.getNom() + " " + medecin.getPrenom() + " ajouté avec succes");
        System.out.println("");
    }

    public static void rechercherMedecin() {
        input.nextLine();
        System.out.print("Entrez l'identifiant du médecin à rechercher : ");
        String id = input.nextLine();

        Medecin medecin = trouverMedecinParIdentifiant(id);

        if (medecin != null) {
            System.out.println("Médecin trouvé : \n" + medecin);
        } else {
            System.out.println("Aucun médecin trouvé avec cet identifiant.");
        }
    }

    public static void listerMedecins() {
        if (medecins.isEmpty()) {
            System.out.println("Aucun médecin trouvé.");
        } else {
            for (Medecin medecin : medecins) {
                System.out.println(medecin);
            }
        }
    }

    //kantite medecin nan systeme nan

    public static int getNombreMedecins() {
        return medecins.size();
    }

    private static void activationOuRevocationDuMedecin()
    {
        input.nextLine();
        System.out.print("Identifiant du médecin : ");
        String id = input.nextLine();
        Medecin medecin = trouverMedecinParIdentifiant(id);

        if (medecin != null) {
            if (medecin.isActif()) {
                System.out.println("Le médecin est actif. Voulez-vous le révoquer ? (oui/non) : ");
                String choix = input.nextLine();
                if (choix.equalsIgnoreCase("oui")) {

                    medecin.revoquer();
                    sauvegarderMedecinsDansFichier();
                    System.out.println("Médecin révoqué.");
                } else {
                    System.out.println("Aucune action effectuée.");
                }
            } else {
                System.out.println("Le médecin est révoqué. Voulez-vous le rendre actif ? (oui/non) : ");
                String choix = input.nextLine();
                if (choix.equalsIgnoreCase("oui")) {
                    medecin.activer();
                    sauvegarderMedecinsDansFichier();
                    System.out.println("Médecin rendu actif.");
                } else {
                    System.out.println("Aucune action effectuée.");
                }
            }
        } else {
            System.out.println("Médecin non trouvé.");
        }

    }


    public static void modifierMedecin() {
        input.nextLine();
        System.out.print("Identifiant du médecin à modifier : ");
        String id = input.nextLine();

        Medecin medecin = trouverMedecinParIdentifiant(id);

        if (medecin == null) {
            System.out.println("Erreur : Médecin non trouvé.");
            return;
        }

        boolean execution = true;
        while (execution) {
            System.out.println("\nQue souhaitez-vous modifier pour le médecin " + medecin.getNom() + " " + medecin.getPrenom() + " ?");
            System.out.println("1. Nom");
            System.out.println("2. Prenom");
            System.out.println("3. Identifiant");
            System.out.println("4. Specialisation");
            System.out.println("5. Mot de passe");
            System.out.println("6. Quitter");
            System.out.print("Choix : ");
            int choix = input.nextInt();
            input.nextLine(); // Consommer la ligne restante

            switch (choix) {
                case 1:
                    System.out.print("Nouveau nom : ");
                    String nouveauNom = input.nextLine();
                    medecin.setNom(nouveauNom); // Mettre à jour le nom
                    sauvegarderMedecinsDansFichier();
                    System.out.println("Nom mis à jour avec succès.");
                    break;

                case 2:
                    System.out.print("Nouveau prenom : ");
                    String nouveauPrenom = input.nextLine();
                    medecin.setPrenom(nouveauPrenom);
                    sauvegarderMedecinsDansFichier();
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
                            medecin.setId(nouvelIdentifiant); // Mettre à jour l'identifiant
                            sauvegarderMedecinsDansFichier();
                            System.out.println("Identifiant mis à jour avec succès.");
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.print("Nouveau specialisation : ");
                    System.out.println("1. Généraliste");
                    System.out.println("2. Cardiologie");
                    System.out.println("3. Dermatologie");
                    System.out.println("4. Pédiatrie");
                    System.out.println("5. Chirurgie");
                    System.out.println("6. Ophtalmologie");
                    System.out.println("7. Gynécologie");
                    System.out.println("8. Urologie");
                    System.out.println("9. Infectiologie");
                    System.out.print("Veuillez choisir : ");

                    String specialisation;
                    while (true) {
                        int nouveauSpecialisation = input.nextInt();
                        input.nextLine(); // Consommer la nouvelle ligne
                        if (nouveauSpecialisation == 1) {
                            specialisation = "Généraliste";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            System.out.println("Specialisation mis à jour avec succès.");
                            break;
                        } else if (nouveauSpecialisation == 2) {
                            specialisation = "Cardiologie";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            System.out.println("Specialisation mis à jour avec succès.");
                            break;
                        } else if(nouveauSpecialisation == 3){
                            specialisation = "Dermatologie";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            break;
                        } else if(nouveauSpecialisation == 4){
                            specialisation = "Pédiatrie";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            break;
                        } else if(nouveauSpecialisation == 5){
                            specialisation = "Chirurgie";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            break;
                        } else if(nouveauSpecialisation == 6){
                            specialisation = "Ophtalmologie";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            break;
                        } else if(nouveauSpecialisation == 7){
                            specialisation = "Gynécologie";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            break;
                        } else if(nouveauSpecialisation == 8){
                            specialisation = "Urologie";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            break;
                        } else if(nouveauSpecialisation == 9){
                            specialisation = "Infectiologie";
                            medecin.setSpecialisation(specialisation);
                            sauvegarderMedecinsDansFichier();
                            break;
                        } else {
                            System.out.println("Choix invalide. Veuillez choisir 1 jusqu'a 9.");
                        }
                    }
                    break;
                case 5:
                    System.out.print("Nouveau mot de passe : ");
                    String nouveauMotDePasse = input.nextLine();
                    medecin.setMotDePasse(nouveauMotDePasse); // Mettre à jour le mot de passe
                    sauvegarderMedecinsDansFichier();
                    System.out.println("Mot de passe mis à jour avec succès.");
                    break;
                case 6:
                    execution = false;
                    System.out.println("Modification terminée.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }



    public static void supprimerMedecin(String id) {
        Medecin medecin = trouverMedecinParIdentifiant(id);
        if (medecin != null) {
            medecins.remove(medecin);
            sauvegarderMedecinsDansFichier();
            System.out.println("Médecin supprimé avec succès.");
        } else {
            System.out.println("Médecin non trouvé.");
        }
    }

    public static void gestionMed()
    {
        boolean execution = true;

        while (execution)
        {
            System.out.println("");
            System.out.println("Bienvenue a la section Gestion des medecins");
            System.out.println("1. Ajouter un médecin");
            System.out.println("2. Rechercher un medecin");
            System.out.println("3. Voir la liste des médecins");
            System.out.println("4. Modifier un médecin");
            System.out.println("5. Supprimer un médecin");
            System.out.println("6. Révoquer ou rendre actif un médecin");
            System.out.println("7. Quitter");
            System.out.print("Choix : ");
            int choix = input.nextInt();

            switch(choix)
            {
                case 1: ajouterMedecin();
                    break;

                case 2: rechercherMedecin();
                    break;

                case 3: listerMedecins();
                    break;

                case 4: modifierMedecin();
                    break;

                case 5: 
                    input.nextLine();
                    System.out.print("Entrez l'identifiant du médecin à supprimer : ");
                    String idMedecin = input.nextLine();
                    GestionMedecin.supprimerMedecin(idMedecin);
            
                break;

                case 6: activationOuRevocationDuMedecin();
                    break;

                case 7: execution = false;
                    break;
            }
        }

    }

    // Méthode pour sauvegarder les médecins dans un fichier avec sérialisation
    private static void sauvegarderMedecinsDansFichier() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(medecins);
            System.out.println("Les médecins ont été sauvegardés dans le fichier.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des médecins : " + e.getMessage());
        }
    }

    // Méthode pour charger les médecins depuis un fichier avec désérialisation
    @SuppressWarnings("unchecked")
    private static void chargerMedecinsDepuisFichier() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            medecins = (Vector<Medecin>) ois.readObject();
            System.out.println("Les médecins ont été chargés depuis le fichier.");
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier trouvé. Un nouveau fichier sera créé lors de la sauvegarde.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des médecins : " + e.getMessage());
        }
    }



}


