import java.sql.*;

public class EmployeeJDBC {

    private final String url = "jdbc:mysql://localhost:3306/paypal"; /* Docker Image */
    private final String username = "root";
    private final String password = "2118847@Paul";

    public static void main(String args[]) {
        EmployeeJDBC jdbc = new EmployeeJDBC();
        
        jdbc.insert(new Employee(1, "Ramesh", "STS"));
        jdbc.insert(new Employee(2, "Suresh", "SWE"));
        jdbc.insert(new Employee(3,"Naresh","Analyst"));

        jdbc.retrive();

        jdbc.delete(3);
        jdbc.retrive();

        jdbc.update(1, "Kamlesh", "SRE");
        jdbc.retrive();

    }

    private Connection connect(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

        }catch(Exception e){
           e.printStackTrace();
        }
        return con;
    }

    public void insert(Employee e) {
        try 
        {
            Connection connection = connect();
            String query = "INSERT INTO Employees VALUES (?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, e.getId());
            pstmt.setString(2,e.getName());
            pstmt.setString(3, e.getDepartment());

            pstmt.executeUpdate();

        } catch (Exception exp) {
            System.out.println(exp.getLocalizedMessage());
        }
    }

    public void retrive(){
        try{
            Connection connection = connect();
            String query = "Select id,name,department from Employees";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getString("department"));
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, String name, String department) {
    
        try{
            Connection connection = connect();
            String query = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id){
        try{
            Connection connection = connect();
            String query = "delete from employees where id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
