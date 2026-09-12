public final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue, int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Order value and delay minutes cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double tieredFee = 0.0;

        if (delayMinutes <= 5) {

            tieredFee = orderValue * 0.005 * delayMinutes;

        } else {

            tieredFee = orderValue * 0.005 * 5;

            if (delayMinutes <= 15) {

                tieredFee = tieredFee
                        + orderValue * 0.01
                        * (delayMinutes - 5);

            } else {

                tieredFee = tieredFee
                        + orderValue * 0.01 * 10;

                tieredFee = tieredFee
                        + orderValue * 0.02
                        * (delayMinutes - 15);
            }
        }

        double minimumFee =
                orderValue * minimumSurgePercent / 100;

        return Math.max(tieredFee, minimumFee);
    }

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println("Rs "
                + calculator.calculateSurgeFee(500, 0));

        System.out.println("Rs "
                + calculator.calculateSurgeFee(500, 1));

        System.out.println("Rs "
                + calculator.calculateSurgeFee(500, 16));
    }
}