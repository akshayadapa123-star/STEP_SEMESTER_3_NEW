public class SrmStudentStaticDemo {

    static class BrokenSrmStudent {

        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }

        void printDetails() {
            System.out.println(name + " " + regNo + " " + attendance);
        }
    }

    static class SrmStudent {

        String name;
        String regNo;
        int attendance;

        static String university = "SRMIST";
        static int admissionCount = 0;

        SrmStudent(String name, int attendance) {

            this.name = name;
            this.attendance = attendance;

            admissionCount++;

            this.regNo = "RA2311003010" + (10 + admissionCount);
        }

        void printIdCard() {
            System.out.println(name + " " + regNo + " " + attendance);
        }

        static void printTotalAdmissions() {
            System.out.println("Total admissions: " + admissionCount);
        }
    }

    public static void main(String[] args) {

        System.out.println("Broken version:");

        /*
         * These fields are static, so both objects share the same data.
         * Creating the second student overwrites the first student's data.
         */
        BrokenSrmStudent student1 =
                new BrokenSrmStudent("Ravi", "RA231100301011", 82);

        BrokenSrmStudent student2 =
                new BrokenSrmStudent("Anitha", "RA231100301012", 68);

        student1.printDetails();
        student2.printDetails();

        System.out.println();
        System.out.println("Corrected version:");

        SrmStudent s1 = new SrmStudent("Ravi", 82);
        SrmStudent s2 = new SrmStudent("Anitha", 68);

        s1.printIdCard();
        s2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}