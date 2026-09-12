public class FeeHostelManagement {

    static class FeeAccount {

        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        void pay(double amount) {

            if (amount <= 0) {
                System.out.println("Invalid payment");
                return;
            }

            amountPaid = amountPaid + amount;
        }

        double getDue() {
            return totalFee - amountPaid;
        }
    }

    static class HostelFeeAccount extends FeeAccount {

        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }

        void payInTwoInstallments(double amount) {

            if (amount <= 0) {
                System.out.println("Invalid payment");
                return;
            }

            pay(amount / 2);
            pay(amount / 2);
        }
    }

    static class HostelRoom {

        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {

            if (occupied < beds) {
                occupied++;
            }
        }

        static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

            for (int i = 0; i < rooms.length; i++) {

                if (rooms[i].occupied < rooms[i].beds) {
                    return rooms[i];
                }
            }

            return null;
        }

        static void safeAllot(HostelRoom[] rooms, SrmStudent student) {

            HostelRoom room = findAvailableRoom(rooms);

            if (room != null) {
                room.allot(student.name);
                student.room = room;
            }
        }
    }

    static class SrmStudent {

        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;

        static int totalStudents = 0;

        SrmStudent(String name, String regNo,
                   HostelFeeAccount feeAccount) {

            this.name = name;
            this.regNo = regNo;
            this.feeAccount = feeAccount;

            totalStudents++;
        }

        void fullStatus() {

            String roomNumber;

            if (room == null) {
                roomNumber = "unallotted";
            } else {
                roomNumber = room.roomNo;
            }

            System.out.println(name
                    + " Due " + feeAccount.getDue()
                    + " room " + roomNumber);
        }
    }

    public static void main(String[] args) {

        HostelFeeAccount raviFee =
                new HostelFeeAccount("RA231100301011", 200000, 0);

        HostelFeeAccount anithaFee =
                new HostelFeeAccount("RA231100301012", 200000, 0);

        HostelFeeAccount karthikFee =
                new HostelFeeAccount("RA231100301013", 200000, 0);

        SrmStudent ravi =
                new SrmStudent("Ravi", "RA231100301011", raviFee);

        SrmStudent anitha =
                new SrmStudent("Anitha", "RA231100301012", anithaFee);

        SrmStudent karthik =
                new SrmStudent("Karthik", "RA231100301013", karthikFee);

        raviFee.pay(60000);
        anithaFee.pay(20000);
        karthikFee.pay(-5000);

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        HostelRoom.safeAllot(rooms, ravi);
        HostelRoom.safeAllot(rooms, anitha);
        HostelRoom.safeAllot(rooms, karthik);

        ravi.fullStatus();
        anitha.fullStatus();
        karthik.fullStatus();

        System.out.println("Total students " + SrmStudent.totalStudents);
    }
}
