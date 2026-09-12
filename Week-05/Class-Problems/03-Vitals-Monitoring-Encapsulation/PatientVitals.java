public class PatientVitals {

    private double[] readings;
    private int count;

    public PatientVitals(double[] initialReadings) {

        readings = new double[500];
        count = 0;

        if (initialReadings != null) {

            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    public void recordReading(double reading) {

        if (reading > 0 && count < readings.length) {
            readings[count] = reading;
            count++;
        }
    }

    public double getAverage() {

        if (count == 0) {
            return 0;
        }

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }

        return sum / count;
    }

    public double[] getReadings() {

        double[] result = new double[count];

        for (int i = 0; i < count; i++) {
            result[i] = readings[i];
        }

        return result;
    }

    public static void main(String[] args) {

        double[] initial = {
                98.6, 99.1, 98.8
        };

        PatientVitals vitals =
                new PatientVitals(initial);

        vitals.recordReading(99.2);
        vitals.recordReading(-10);

        System.out.println(
                "Average: " + vitals.getAverage());

        double[] readings =
                vitals.getReadings();

        readings[0] = 500;

        System.out.println(
                "First reading: "
                + vitals.getReadings()[0]);
    }
}