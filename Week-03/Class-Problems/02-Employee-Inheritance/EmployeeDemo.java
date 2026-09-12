public class EmployeeDemo {

    public static void main(String[] args) {

        Employee plain =
                new Employee("E101", "Ravi", 40000);

        ManagerEmployee manager =
                new ManagerEmployee("E102", "Divya",
                        70000, 8000);

        InternEmployee intern =
                new InternEmployee("E103", "Meera",
                        12000, 10000);

        if (plain instanceof ManagerEmployee) {

            ManagerEmployee m = (ManagerEmployee) plain;
            System.out.println("Manager effective pay: Rs "
                    + m.effectiveSalary());

        } else if (plain instanceof InternEmployee) {

            InternEmployee i = (InternEmployee) plain;
            System.out.println("Intern effective pay: Rs "
                    + i.effectiveSalary());

        } else {

            System.out.println("Plain employee pay: Rs "
                    + plain.getSalary());
        }

        if (manager instanceof ManagerEmployee) {

            System.out.println("Manager effective pay: Rs "
                    + manager.effectiveSalary());
        }

        if (intern instanceof InternEmployee) {

            System.out.println("Intern effective pay: Rs "
                    + intern.effectiveSalary());
        }
    }
}