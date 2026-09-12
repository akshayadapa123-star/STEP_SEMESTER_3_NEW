public class CirculationLedger {

    static {
        System.out.println(
                "Nightly circulation ledger initialized.");
    }

    public static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int routine = 0;

        for (int i = 0; i < receipts.length; i++) {

            LoanReceipt receipt = receipts[i];

            if (receipt == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {

                referenceOnly++;

            } else {

                routine++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + routine + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt receipt =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{
                                "BK-100",
                                "BK-101"
                        });

        String[] ids = receipt.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
                receipt.getBookIds()[0]);

        LoanReceipt corrected =
                receipt.withCorrectedBookId(
                        0,
                        "BK-999");

        System.out.println(
                corrected.getBookIds()[0]);

        LoanReceipt[] receipts = {

                new ReferenceOnlyLoanReceipt(
                        "LIB-001",
                        new String[]{"BK-200"},
                        "Reading Room 3"),

                null,

                new LoanReceipt(
                        "LIB-002",
                        new String[]{"BK-201"})
        };

        System.out.println(
                processNightlyCirculation(receipts));
    }
}