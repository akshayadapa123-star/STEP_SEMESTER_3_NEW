public class FeeAccount {

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
            System.out.println("Payment rejected.");
            return;
        }

        amountPaid = amountPaid + amount;
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}