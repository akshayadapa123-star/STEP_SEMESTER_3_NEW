public class PremiumDeliveryAccount
        extends DeliveryAccount {

    PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }

    void processPremiumAccount(
            double amount, int delayMinutes) {

        double penalty = calculateSurgeFee(delayMinutes);

        // Premium members pay 50% of the calculated surge fee.
        double premiumPenalty = penalty * 0.50;

        System.out.println("Premium account"
                + " | Amount: Rs " + amount
                + " | Surge fee: Rs " + premiumPenalty);
    }
}