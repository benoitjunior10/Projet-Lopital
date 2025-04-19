import java.io.Serializable;

public class Prescription implements Serializable{
    private String id;
    private String patientId;
    private String medecinId;
    private String medicament;
    private String dosage;

    public Prescription(String id, String patientId, String medecinId, String medicament, String dosage) {
        this.id = id;
        this.patientId = patientId;
        this.medecinId = medecinId;
        this.medicament = medicament;
        this.dosage = dosage;
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

    public String getMedicament() {
        return medicament;
    }

    public String getDosage() {
        return dosage;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Prescription : " + medicament + ", Dosage : " + dosage + ", Patient ID : " + patientId + ", Médecin ID : " + medecinId;
    }
}
