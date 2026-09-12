public class InternEmployee extends Employee {

    private double stipendCap;

    InternEmployee(String empId, String empName,
                   double salary, double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {

        if (getSalary() < stipendCap) {
            return getSalary();
        }

        return stipendCap;
    }
}