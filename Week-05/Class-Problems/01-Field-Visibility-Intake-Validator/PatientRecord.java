public class PatientRecord {

    private String patientId;
    String patientName;
    protected String diagnosis;
    public String hospitalName;

    public PatientRecord(String patientId, String patientName,
                         String diagnosis, String hospitalName) {

        if (patientId == null ||
            patientId.trim().isEmpty() ||
            patientId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid patient ID");
        }

        this.patientId = patientId.trim();
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.hospitalName = hospitalName;
    }

    public static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static void main(String[] args) {

        PatientRecord patient =
                new PatientRecord(
                        "PT01",
                        "Rahul",
                        "Fever",
                        "City Hospital");

        System.out.println(
                classifyAccess("private", "SAME_CLASS"));

        System.out.println(
                classifyAccess("private", "SAME_PACKAGE"));

        System.out.println(
                patient.hospitalName);
    }
}