import java.util.List;
import java.util.Scanner;

public class CompanyManagment {
    public static void main(String[] args) {
        //creat instance
        EmployeeDAOImpl dao=EmployeeDAOImpl.getInstance();
        //display all employee
       // displayAllEmployee(dao);
        //addNewEmployee
        //addNewEmployee(dao);
        //find employee by ID
        //findEmployeeID(dao);
        //update employee by ID
       // updateEmployeeByID(dao);
        //dlete employee by ID
        deletEmployeeByID(dao);
    }

    private static void deletEmployeeByID(EmployeeDAOImpl dao) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter an employee ID that you want to delete:");
        int id =Integer.parseInt(sc.nextLine().trim());
        dao.deletEmp(id);
    }

    private static void updateEmployeeByID(EmployeeDAOImpl dao) {
        //Search employee ID
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter and employee ID:");
        int id = Integer.parseInt(sc.nextLine());
        Employee emp = dao.findEmp(id);
        if (emp != null) {
            System.out.println(emp.toString());
            //get new data
            System.out.print("Enter new salary for employee ID"
                    + emp.getEmpID() + ":");
            double ns = Double.parseDouble(sc.nextLine().trim());
            //edit data
            emp.setSalary(ns);
            //Update data into database
            dao.updateEmp(emp);
        }
    }
    private static void findEmployeeID(EmployeeDAOImpl dao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter and employee ID:");
        int id = Integer.parseInt(sc.nextLine());
        Employee emp = dao.findEmp(id);
        if (emp != null) {
            System.out.println(emp.toString());
        }
    }

    private static void addNewEmployee(EmployeeDAOImpl dao) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee id:");
        int id =Integer.parseInt(sc.nextLine().trim());
        System.out.println("Enter Employee name");
        String name = sc.nextLine();
        System.out.println("Enter Employee position");
        String position=sc.nextLine();
        System.out.println("Enter Employee salaty");
        double saraly = Double.parseDouble(sc.nextLine().trim());

        dao.addEmp(new  Employee(id,name,position,saraly));
    }

    private static void displayAllEmployee(EmployeeDAOImpl dao) {
        List<Employee> emp = dao.getAllEmp();
        for (Employee e:emp){
            System.out.println(e.toString());
        }
    }
}
