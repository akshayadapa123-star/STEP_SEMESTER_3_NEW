class RaceEntry {
    private static int count = 1000;

    final String entryCode;
    protected double balance;

    RaceEntry(double balance) {
        entryCode = "BIB-" + (++count);
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

    static boolean validDiscount(String code) {
        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'M'
            && Character.isDigit(code.charAt(1))
            && Character.isDigit(code.charAt(2))
            && Character.isDigit(code.charAt(3))
            && Character.isUpperCase(code.charAt(4));
    }
}

class RelayTeamEntry extends RaceEntry {

    RelayTeamEntry(double balance) {
        super(balance);
    }
}

class Settlement {

    static double settle(RaceEntry[] entries) {
        double total = 0;

        if (entries == null)
            return 0;

        for (RaceEntry e : entries) {
            if (e != null) {
                total += e.getBalanceDue();

                if (e instanceof RelayTeamEntry)
                    total -= 10;
            }
        }

        return total;
    }
}