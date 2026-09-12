public class FeeAccountDemo {

    public static void main(String[] args) {

        FeeAccount plain = new FeeAccount(
                "RA231100301011", 150000, 0);

        plain.pay(150000);

        HostelFeeAccount hostel = new HostelFeeAccount(
                "RA231100301012", 200000, 0);

        hostel.payInTwoInstallments(60000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount(
                        "RA231100301013", 180000, 0, 20);

        System.out.println("Plain total 150000 paid 150000 => due "
                + plain.getDue());

        System.out.println("Hostel total 200000 paid 60000 => due "
                + hostel.getDue());

        System.out.println("Scholarship total 180000 paid 0, scholarship 20% => effective due "
                + scholarship.effectiveDue());

        if (hostel instanceof HostelFeeAccount) {
            System.out.println("Hostel account supports two installments.");
        }

        if (scholarship instanceof ScholarshipFeeAccount) {
            System.out.println("Scholarship account supports scholarship.");
        }
    }
}