import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
    //set connection
    public static String driverName="org.sqlite.JDBC";
    public static String url="jdbc:sqlite:"+"D:/OOPPLAB10_DATABASE_IS/IS_Company.sqlite";
    public static Connection conn=null;
    //Constant Operators
    //SQL CRUD
    public static final String GET_ALL_EMP="select * from Employee";
    public static final String ADD_NEW_EMP="insert into Employee(empID,name,position,salary)values(?,?,?,?)";
    public static final String FIND_EMP_BY_ID="select*from Employee where empID=?";
    public static final String UPDATE_EMP ="update Employee set name=?,position=?,salary=?where empID=?";
    public static final String DELETE_EMP ="delete from Employee where empID=?";
    //Create instant
    private static  EmployeeDAOImpl instance=new EmployeeDAOImpl();
    public static EmployeeDAOImpl getInstance(){
        return instance;
    }
    //Constructor
    private EmployeeDAOImpl(){
        //load class JDBC
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAllEmp() {
        List<Employee>emp=new ArrayList<Employee>();
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(GET_ALL_EMP);

            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String position = rs.getString(3);
                double salary = rs.getDouble(4);

                emp.add(new Employee(id, name, position, salary));
            }
            //close connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;



    }//getAllEmp

    @Override
    public void addEmp(Employee emp) {
        try {
            conn=DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(ADD_NEW_EMP);
            //set parameter
            ps.setInt(1,emp.getEmpID());
            ps.setString(2,emp.getName());
            ps.setString(3,emp.getPosition());
            ps.setDouble(4,emp.getSalary());

            if (ps.execute()==false) {
                System.out.println("Already add new employee.");
            }else{
                System.out.println("Could not add new employee");
            }
            //close connection
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee findEmp(int id) {
        Employee emp = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(FIND_EMP_BY_ID);
            //set parameter
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int empid = rs.getInt(1);
                String name = rs.getString(2);
                String position = rs.getString(3);
                double salary = rs.getDouble(4);
                emp = new Employee(empid, name, position, salary);
            }else {
                System.out.println("Could not found employee with id:"+id);
            }
            //close connection
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return emp;
    }

    @Override
    public void updateEmp(Employee emp) {
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(UPDATE_EMP);
            //set parameter
            ps.setString(1,emp.getName());
            ps.setString(2,emp.getPosition());
            ps.setDouble(3,emp.getSalary());
            ps.setInt(4,emp.getEmpID());

            int rs = ps.executeUpdate();
            if (rs !=0) {
                System.out.println("Employee with id"
                        + emp.getEmpID());
            }else {
                System.out.println("Could not update employee wiht id"
                +emp.getEmpID());
            }
            //close connection
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletEmp(int id) {
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(DELETE_EMP);
            //set parameter
            ps.setInt(1,id);
            int rs = ps.executeUpdate();
            if (rs !=0) {
                System.out.println("Employee with id"
                        + id + "was deleted");
            }else {
                System.out.println("Could not delete employee"+
                        "with id"+id);

            }
            //close connection
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
