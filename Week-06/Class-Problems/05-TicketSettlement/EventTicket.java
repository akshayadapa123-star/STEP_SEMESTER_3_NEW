class EventTicket {
    private static int count = 1000;

    final String ticketId;
    protected double balance;

    EventTicket(double balance) {
        ticketId = "TCK-" + (++count);
        this.balance = balance;
    }

    void pay(double amount) {
        balance -= amount;
    }

    void pay(double amount, String mode) {
        pay(amount);
    }

    double getBalanceDue() {
        return balance;
    }

    static boolean validPromo(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'F'
            && Character.isDigit(code.charAt(1))
            && Character.isDigit(code.charAt(2))
            && Character.isDigit(code.charAt(3))
            && Character.isUpperCase(code.charAt(4));
    }
}

class GroupTicket extends EventTicket {

    GroupTicket(double balance) {
        super(balance);
    }
}

class Settlement {

    static double settle(EventTicket[] tickets) {
        double total = 0;

        if (tickets == null)
            return 0;

        for (EventTicket t : tickets) {
            if (t != null) {
                total += t.getBalanceDue();

                if (t instanceof GroupTicket)
                    total -= 10;
            }
        }

        return total;
    }
}