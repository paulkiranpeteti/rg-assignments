package com.paulkiran;

import com.paulkiran.configuration.JdbcConfig;
import com.paulkiran.dao.EmployeeDao;
import com.paulkiran.model.Employee;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        EmployeeDao dao = context.getBean(EmployeeDao.class);

        dao.init();
        dao.create(new Employee(1, "Paul","CSE"));
        dao.create(new Employee(2, "Kiran","ECE"));
        dao.create(new Employee(3, "Mahesh", "AIML"));

        System.out.println();
        dao.update(new Employee(2, "CSIT", "Ganesh"));

        dao.delete(3);
        
        List<Employee> employees = dao.getAll();
        for(Employee e:employees){
            System.out.println(e);
        }

        context.close();
    }
}
