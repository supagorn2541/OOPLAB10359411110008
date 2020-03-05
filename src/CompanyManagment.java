import java.util.List;
import java.util.Scanner;

public class CompanyManagment {
    public static void main(String[] args) {
        //creat instance
        EmployeeDAOImpl dao=EmployeeDAOImpl.getInstance();
        //display all employee
        displayAllEmployee(dao);
        //addNewEmployee
        addNewEmployee(dao);
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
