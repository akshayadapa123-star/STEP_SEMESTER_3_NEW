public class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {

        if (!isValidName(passengerName)) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (!isValidText(destination)) {
            throw new IllegalArgumentException("Invalid destination");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    private static boolean isValidText(String value) {

        return value != null && !value.trim().isEmpty();
    }

    private static boolean isValidName(String value) {

        if (!isValidText(value)) {
            return false;
        }

        value = value.trim();

        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);

            if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
                return false;
            }
        }

        return true;
    }

    public void markCheckedIn() {

        if (checkedIn) {
            System.out.println("Already checked in: " + passengerName);
        } else {
            checkedIn = true;
            System.out.println("Checked in: " + passengerName);
        }
    }

    public static void processBatch(String[][] rawBookings) {

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        String[][] acceptedBookings = new String[rawBookings.length][2];

        for (int i = 0; i < rawBookings.length; i++) {

            if (rawBookings[i] == null || rawBookings[i].length < 2) {
                rejected++;
                continue;
            }

            String name = rawBookings[i][0];
            String destination = rawBookings[i][1];

            try {

                BusTicket ticket = new BusTicket(name, destination);

                boolean duplicate = false;

                for (int j = 0; j < valid; j++) {

                    if (acceptedBookings[j][0].equalsIgnoreCase(ticket.passengerName)
                            && acceptedBookings[j][1].equalsIgnoreCase(ticket.destination)) {

                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    duplicates++;
                } else {

                    acceptedBookings[valid][0] = ticket.passengerName;
                    acceptedBookings[valid][1] = ticket.destination;
                    valid++;
                }

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid
                + " | Rejected: " + rejected
                + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {" ", " "}
        };

        processBatch(bookings);
    }
}