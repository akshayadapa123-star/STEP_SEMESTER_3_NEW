public class FleetReconciliationEngine {

    public void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account == null) {
            return;
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        double penalty = calculatePenalty(amount, minutesLate);

        if (account instanceof Sleeper) {

            // Implementation choice:
            // Sleeper accounts settle at 50% of the calculated penalty.
            penalty = penalty * 0.50;

            System.out.printf(
                    "Sleeper %s -> Penalty: Rs %.2f%n",
                    account.getBookingId(),
                    penalty
            );

        } else {

            System.out.printf(
                    "Regular %s -> Penalty: Rs %.2f%n",
                    account.getBookingId(),
                    penalty
            );
        }
    }

    private double calculatePenalty(
            double ticketFare,
            int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Minutes late cannot be negative"
            );
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penaltyPercent;

        if (minutesLate <= 5) {

            penaltyPercent = minutesLate * 0.5;

        } else if (minutesLate <= 15) {

            penaltyPercent = (5 * 0.5)
                    + ((minutesLate - 5) * 1.0);

        } else {

            penaltyPercent = (5 * 0.5)
                    + (10 * 1.0)
                    + ((minutesLate - 15) * 2.0);
        }

        return ticketFare * penaltyPercent / 100.0;
    }

    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        int limit = Math.min(
                accounts.length,
                Math.min(amounts.length, minutesLateArray.length)
        );

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;

        double grandTotal = 0.0;

        FleetReconciliationEngine engine =
                new FleetReconciliationEngine();

        for (int i = 0; i < limit; i++) {

            BusTicketAccount account = accounts[i];

            if (account == null) {

                nullSkipped++;
                continue;
            }

            double amount = amounts[i];
            int minutesLate = minutesLateArray[i];

            double penalty =
                    engine.calculatePenalty(amount, minutesLate);

            if (account instanceof Sleeper) {

                penalty = penalty * 0.50;
                sleeperCount++;

            } else {

                regularCount++;
            }

            engine.processAccount(account, amount, minutesLate);

            grandTotal += penalty;
            processed++;
        }

        System.out.println();
        System.out.println(
                processed + " processed | "
                + nullSkipped + " null skipped | "
                + sleeperCount + " sleeper | "
                + regularCount + " regular"
        );

        System.out.printf(
                "Grand total penalties = Rs %.2f%n",
                grandTotal
        );
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
                new Sleeper("BK001", 2000),
                null,
                new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
                1200,
                900,
                700
        };

        int[] minutesLateArray = {
                10,
                5,
                0
        };

        processBatch(
                accounts,
                amounts,
                minutesLateArray
        );
    }
}