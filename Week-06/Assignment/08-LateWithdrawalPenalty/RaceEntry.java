import java.util.Arrays;

class RaceEntry {
    private double[] fees = new double[10];
    private int count = 0;

    protected void applyLateFee(double amount) {
        fees[count++] = amount;
    }

    double[] getFees() {
        return Arrays.copyOf(fees, count);
    }
}

class RunnerEntry extends RaceEntry {

    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}