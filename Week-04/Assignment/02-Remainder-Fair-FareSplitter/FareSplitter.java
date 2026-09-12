public class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {

        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {

        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {

        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {

        double[] shares = new double[passengerCount];

        long totalPaisa = Math.round(totalFare * 100);
        long basePaisa = totalPaisa / passengerCount;
        long remainder = totalPaisa % passengerCount;

        for (int i = 0; i < passengerCount; i++) {

            long sharePaisa = basePaisa;

            if (i == passengerCount - 1) {
                sharePaisa += remainder;
            }

            shares[i] = sharePaisa / 100.0;
        }

        return shares;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {

        return confirmed < expected;
    }

    public static void main(String[] args) {

        FareSplitter fareSplitter =
                new FareSplitter("TRIP001", 100000, 3);

        double[] shares = fareSplitter.fareBreakdown();

        for (double share : shares) {
            System.out.printf("%.2f ", share);
        }

        System.out.println();

        FareSplitter provisional =
                new FareSplitter("TRIP003");

        double[] provisionalShares = provisional.fareBreakdown();

        for (double share : provisionalShares) {
            System.out.printf("%.1f ", share);
        }
    }
}