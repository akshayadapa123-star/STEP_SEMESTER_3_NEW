public class HostelFeeAccount extends FeeAccount {

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