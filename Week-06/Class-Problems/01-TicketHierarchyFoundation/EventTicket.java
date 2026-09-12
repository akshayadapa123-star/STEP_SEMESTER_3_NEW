class EventTicket {
    protected String attendeeId;
    protected double price;

    EventTicket(String attendeeId, double price) {
        if (attendeeId == null || attendeeId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid attendee ID");

        this.attendeeId = attendeeId;
        this.price = price;
    }

    void pay(double amount) {
        price -= amount;
    }

    double getBalanceDue() {
        return price;
    }

    static int registerBatch(String[] ids) {
        int rejected = 0;

        for (String id : ids) {
            try {
                new EventTicket(id, 100);
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return rejected;
    }
}