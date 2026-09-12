public class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;

    public PatientProfile() {

        this(null, null);
    }

    public PatientProfile(String name) {

        this(null, name);
    }

    public PatientProfile(
            String patientId,
            String name) {

        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
        this.lockerPinHash = null;
    }

    public String getPatientId() {

        return patientId;
    }

    public void setPatientId(String patientId) {

        if (this.patientId == null) {
            this.patientId = patientId;
        }
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public boolean isDischarged() {

        return discharged;
    }

    public void setDischarged(boolean discharged) {

        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {

        if (pin == null ||
            !pin.matches("[0-9]{4,6}")) {

            throw new IllegalArgumentException(
                    "PIN must contain 4 to 6 digits");
        }

        lockerPinHash =
                Integer.toHexString(pin.hashCode());
    }

    public static void main(String[] args) {

        PatientProfile patient =
                new PatientProfile("Ananya");

        patient.setPatientId("PT1001");

        // Second assignment is ignored.
        patient.setPatientId("PT9999");

        patient.setLockerPin("1234");

        patient.setDischarged(true);

        System.out.println(
                patient.getPatientId());

        System.out.println(
                patient.getName());

        System.out.println(
                patient.isDischarged());
    }
}