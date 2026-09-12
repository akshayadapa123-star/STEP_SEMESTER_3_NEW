public class DischargeSummary {

    private final String patientId;
    private final String[] medications;

    public DischargeSummary(
            String patientId,
            String[] medications) {

        if (medications == null) {
            throw new IllegalArgumentException(
                    "Medication list cannot be null");
        }

        for (String medication : medications) {

            if (!isValidMedication(medication)) {
                throw new IllegalArgumentException(
                        "Invalid medication code");
            }
        }

        this.patientId = patientId;
        this.medications = medications.clone();
    }

    private static boolean isValidMedication(
            String medication) {

        return medication != null &&
               medication.matches("MED-[A-Z]");
    }

    public String getPatientId() {

        return patientId;
    }

    public String[] getMedications() {

        return medications.clone();
    }

    public DischargeSummary withCorrectedMedication(
            int index,
            String newMedication) {

        if (index < 0 ||
            index >= medications.length) {

            throw new IndexOutOfBoundsException(
                    "Invalid medication index");
        }

        if (!isValidMedication(newMedication)) {

            throw new IllegalArgumentException(
                    "Invalid medication code");
        }

        String[] corrected =
                medications.clone();

        corrected[index] = newMedication;

        return new DischargeSummary(
                patientId,
                corrected);
    }
}