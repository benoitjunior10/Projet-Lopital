import java.io.Serializable;

public class RendezVous implements Serializable{
    private String id;
    private String patientId;
    private String medecinId;
    private String date;
    private String heure;

    public RendezVous(String id, String patientId, String medecinId, String date, String heure) {
        this.id = id;
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.date = date;
        this.heure = heure;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getMedecinId() {
        return medecinId;
    }

    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Rendez-vous : " + date + " à " + heure + ", Patient ID : " + patientId + ", ID RendezVous: " + id + ", Médecin ID : " + medecinId;
    }
}
