class RaceEntry {
    protected double fee;

    RaceEntry(double fee) {
        this.fee = fee;
    }

    double getBalanceDue() {
        return fee;
    }

    void printEntry() {
        System.out.println("Race Entry");
    }
}

class RunnerEntry extends RaceEntry {

    RunnerEntry(double fee) {
        super(fee);
    }

    void printEntry() {
        System.out.println("Runner Entry");
    }
}

class EliteRunnerEntry extends RunnerEntry {

    EliteRunnerEntry(double fee) {
        super(fee);
    }

    void printEntry() {
        System.out.println("Elite Runner Entry");
    }
}

class RelayTeamEntry extends RaceEntry {

    RelayTeamEntry(double fee) {
        super(fee);
    }

    void printEntry() {
        System.out.println("Relay Team Entry");
    }
}

public class RaceSystem {

    static String classifyGeneration(RaceEntry e) {
        if (e instanceof EliteRunnerEntry)
            return "Elite";

        if (e instanceof RunnerEntry)
            return "Runner";

        return "Base";
    }

    public static void main(String[] args) {
        RaceEntry[] entries = {
            new RaceEntry(100),
            new RunnerEntry(200),
            new EliteRunnerEntry(300),
            new RelayTeamEntry(400)
        };

        for (RaceEntry e : entries) {
            e.printEntry();
            System.out.println(e.getBalanceDue());
        }
    }
}