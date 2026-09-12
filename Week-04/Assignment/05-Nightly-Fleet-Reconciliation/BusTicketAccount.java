public class BusTicketAccount {

    private static int totalAccounts;

    private String bookingId;
    private double ticketFare;

    static {
        totalAccounts = 0;
        System.out.println("BusTicketAccount system initialized.");
    }

    public BusTicketAccount(String bookingId, double ticketFare) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException("Ticket fare cannot be negative");
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;

        totalAccounts++;
    }

    public BusTicketAccount(String bookingId) {

        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException("Minutes late cannot be negative");
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

    public String getBookingId() {

        return bookingId;
    }

    public double getTicketFare() {

        return ticketFare;
    }
}