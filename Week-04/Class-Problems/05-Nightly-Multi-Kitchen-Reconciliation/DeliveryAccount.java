public class DeliveryAccount {

    private String studentId;
    private double orderValue;

    static String accountSystem;

    static {
        accountSystem = "Campus Delivery";
    }

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    final double calculateSurgeFee(int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Order value and delay cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double tieredFee = 0.0;

        if (delayMinutes <= 5) {

            tieredFee =
                    orderValue * 0.005 * delayMinutes;

        } else {

            tieredFee =
                    orderValue * 0.005 * 5;

            if (delayMinutes <= 15) {

                tieredFee =
                        tieredFee
                        + orderValue * 0.01
                        * (delayMinutes - 5);

            } else {

                tieredFee =
                        tieredFee
                        + orderValue * 0.01 * 10;

                tieredFee =
                        tieredFee
                        + orderValue * 0.02
                        * (delayMinutes - 15);
            }
        }

        return tieredFee;
    }

    void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        double penalty = account.calculateSurgeFee(delayMinutes);

        System.out.println(account.studentId
                + " | Amount: Rs " + amount
                + " | Surge fee: Rs " + penalty);
    }
}