public class NightlyLedger {

    static {
        System.out.println(
                "Nightly discharge ledger initialized.");
    }

    public static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {

            if (summary == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof
                    CriticalCareDischargeSummary) {

                criticalCare++;

            } else {

                routine++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + criticalCare + " critical-care | "
                + routine + " routine";
    }

    public static void main(String[] args) {

        DischargeSummary summary =
                new DischargeSummary(
                        "PT1001",
                        new String[]{
                                "MED-A",
                                "MED-B"
                        });

        String[] medications =
                summary.getMedications();

        medications[0] = "HACKED";

        System.out.println(
                summary.getMedications()[0]);

        DischargeSummary corrected =
                summary.withCorrectedMedication(
                        0,
                        "MED-C");

        System.out.println(
                corrected.getMedications()[0]);

        DischargeSummary[] summaries = {

                new CriticalCareDischargeSummary(
                        "PT2001",
                        new String[]{"MED-A"},
                        4),

                null,

                new DischargeSummary(
                        "PT3001",
                        new String[]{"MED-B"})
        };

        System.out.println(
                processNightlyBatch(summaries));
    }
}