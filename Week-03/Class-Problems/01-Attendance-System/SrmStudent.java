public class SrmStudent {

    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    /*
     * classAverage is static because it calculates the average
     * of multiple students and does not belong to one student.
     *
     * isEligible is not static because it checks the attendance
     * of one particular student object.
     */
    static double classAverage(SrmStudent[] students) {

        int total = 0;

        for (int i = 0; i < students.length; i++) {
            total = total + students[i].attendance;
        }

        return (double) total / students.length;
    }

    public static void main(String[] args) {

        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA231100301011", 82),
            new SrmStudent("Anitha", "RA231100301012", 68),
            new SrmStudent("Karthik", "RA231100301013", 91),
            new SrmStudent("Meera", "RA231100301014", 74),
            new SrmStudent("Suresh", "RA231100301015", 60)
        };

        for (int i = 0; i < students.length; i++) {

            String status;

            if (students[i].isEligible()) {
                status = "Eligible";
            } else {
                status = "Detained";
            }

            System.out.println(
                students[i].name + " - "
                + students[i].attendance + "% - "
                + status
            );
        }

        System.out.println(
            "Class average: "
            + classAverage(students) + "%"
        );
    }
}