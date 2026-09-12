public final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {

        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException("Ticket fare cannot be negative");
        }

        if (minutesLate < 0) {
            throw new IllegalArgumentException("Minutes late cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penaltyPercent = 0.0;

        if (minutesLate <= 5) {

            penaltyPercent = minutesLate * 0.5;

        } else {

            penaltyPercent = 5 * 0.5;

            if (minutesLate <= 15) {

                penaltyPercent += (minutesLate - 5) * 1.0;

            } else {

                penaltyPercent += 10 * 1.0;
                penaltyPercent += (minutesLate - 15) * 2.0;
            }
        }

        double tieredPenalty =
                ticketFare * penaltyPercent / 100.0;

        double minimumPenalty =
                ticketFare * minimumPenaltyPercent / 100.0;

        return Math.max(tieredPenalty, minimumPenalty);
    }

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println(calculator.calculatePenalty(1000, 0));
        System.out.println(calculator.calculatePenalty(1000, 1));
        System.out.println(calculator.calculatePenalty(1000, 16));
    }
}