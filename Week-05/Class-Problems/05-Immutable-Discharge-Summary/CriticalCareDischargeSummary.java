public class CriticalCareDischargeSummary
        extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medications,
            int icuDays) {

        super(patientId, medications);

        this.icuDays = icuDays;
    }

    public int getIcuDays() {

        return icuDays;
    }
}