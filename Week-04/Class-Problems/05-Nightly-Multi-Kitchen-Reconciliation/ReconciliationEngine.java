public class ReconciliationEngine {

    static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        int limit = accounts.length;

        if (amounts.length < limit) {
            limit = amounts.length;
        }

        if (delayMinutesArray.length < limit) {
            limit = delayMinutesArray.length;
        }

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotal = 0.0;

        for (int i = 0; i < limit; i++) {

            DeliveryAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            if (account instanceof PremiumDeliveryAccount) {

                PremiumDeliveryAccount premium =
                        (PremiumDeliveryAccount) account;

                double penalty =
                        premium.calculateSurgeFee(
                                delayMinutesArray[i]);

                double premiumPenalty =
                        penalty * 0.50;

                premium.processPremiumAccount(
                        amounts[i],
                        delayMinutesArray[i]);

                grandTotal =
                        grandTotal + premiumPenalty;

                premiumCount++;

            } else {

                double penalty =
                        account.calculateSurgeFee(
                                delayMinutesArray[i]);

                account.processAccount(
                        account,
                        amounts[i],
                        delayMinutesArray[i]);

                grandTotal =
                        grandTotal + penalty;

                regularCount++;
            }

            processed++;
        }

        System.out.println(
                processed + " processed | "
                + nullSkipped + " null skipped | "
                + premiumCount + " premium | "
                + regularCount + " regular | "
                + "grand total surge fees = Rs "
                + grandTotal);
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500, 400, 300
        };

        int[] delayMinutesArray = {
            10, 5, 0
        };

        processBatch(
                accounts,
                amounts,
                delayMinutesArray);
    }
}