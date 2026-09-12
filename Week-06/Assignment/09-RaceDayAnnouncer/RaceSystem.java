class RaceEntry {
    void printEntry() {
        System.out.println("Race Entry");
    }
}

class RunnerEntry extends RaceEntry {
    String category = "10K";

    void printEntry() {
        System.out.println("Runner Entry");
    }
}

public class RaceSystem {

    static String batchPrint(RaceEntry[] entries) {
        StringBuilder sb = new StringBuilder();

        for (RaceEntry e : entries) {
            e.printEntry();

            if (e instanceof RunnerEntry) {
                RunnerEntry r = (RunnerEntry) e;
                sb.append(r.category).append("\n");
            }
        }

        return sb.toString();
    }
}