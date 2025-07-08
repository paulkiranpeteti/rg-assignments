import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {

    private List<Employee> list;
    public static void main(String[] args) {
        EmployeeCRUD empcrud = new EmployeeCRUD();
        empcrud.list = new ArrayList<>();

        empcrud.create();
        empcrud.retrive();

        empcrud.delete();
        empcrud.retrive();

        empcrud.update();
        empcrud.retrive();
    }

    public void create(){
        list.add(new Employee(1, "Ramesh", "CSE"));
        list.add(new Employee(2, "Suresh", "ECE"));
        list.add(new Employee(3, "Naresh", "CSIT"));
    }

    public void retrive(){
        for(Employee e:list){
            System.out.println(e.getId()+" "+e.getName()+" "+e.getDepartment());
        }
        System.out.println();
    }

    public void delete(){
        list.remove(2); // Removing the Record with index as 2
    }

    public void update(){
        list.set(0, new Employee(3 , "Prasad", "CSIT")); /* Updated the 0th indexed record */
    }
}
