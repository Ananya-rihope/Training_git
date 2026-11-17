import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeManagement {
    private static final Map<Integer, Employee> employees = new LinkedHashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Employee Management ===");
            System.out.println("1. Add employee");
            System.out.println("2. Display all employees");
            System.out.println("3. Search employee by ID");
            System.out.println("4. Update employee details");
            System.out.println("5. Delete employee");
            System.out.println("6. Exit");
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1–6.");
            }
        }

        scanner.close();
    }

    private static void addEmployee() {
        int id = readInt("Employee ID: ");
        if (employees.containsKey(id)) {
            System.out.println("An employee with that ID already exists.");
            return;
        }

        String name = readNonEmptyLine("Name: ");
        String department = readNonEmptyLine("Department: ");
        double salary = readNonNegativeDouble("Salary: ");

        employees.put(id, new Employee(id, name, department, salary));
        System.out.println("Employee added successfully.");
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        System.out.println("\nEmployee ID | Name | Department | Salary");
        System.out.println("------------------------------------------");
        for (Employee employee : employees.values()) {
            System.out.printf("%11d | %s | %s | %.2f%n",
                    employee.getId(), employee.getName(),
                    employee.getDepartment(), employee.getSalary());
        }
    }

    private static void searchEmployee() {
        int id = readInt("Enter employee ID to search: ");
        Employee employee = employees.get(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            System.out.println("Employee found:");
            System.out.println(employee);
        }
    }

    private static void updateEmployee() {
        int id = readInt("Enter employee ID to update: ");
        Employee employee = employees.get(id);
        if (employee == null) {
            System.out.println("No employee found with ID " + id + ".");
            return;
        }

        System.out.println("Enter the updated employee details:");
        employee.setName(readNonEmptyLine("Name: "));
        employee.setDepartment(readNonEmptyLine("Department: "));
        employee.setSalary(readNonNegativeDouble("Salary: "));
        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee() {
        int id = readInt("Enter employee ID to delete: ");
        Employee removed = employees.remove(id);
        if (removed == null) {
            System.out.println("No employee found with ID " + id + ".");
        } else {
            System.out.println("Employee deleted successfully.");
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // continue
            }
            System.out.println("Please enter a valid non-negative salary.");
        }
    }

    private static String readNonEmptyLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static class Employee {
        private final int id;
        private String name;
        private String department;
        private double salary;

        private Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        private int getId() {
            return id;
        }

        private String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        private String getDepartment() {
            return department;
        }

        private void setDepartment(String department) {
            this.department = department;
        }

        private double getSalary() {
            return salary;
        }

        private void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("ID: %d, Name: %s, Department: %s, Salary: %.2f",
                    id, name, department, salary);
        }
    }
}
