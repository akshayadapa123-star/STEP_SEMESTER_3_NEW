class RaceEntry {
    protected String runnerId;
    protected double fee;

    RaceEntry(String runnerId, double fee) {
        if (runnerId == null || runnerId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid runner ID");

        this.runnerId = runnerId;
        this.fee = fee;
    }

    void pay(double amount) {
        fee -= amount;
    }

    double getBalanceDue() {
        return fee;
    }

    static int registerBatch(String[] ids) {
        int rejected = 0;

        for (String id : ids) {
            try {
                new RaceEntry(id, 100);
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return rejected;
    }
}