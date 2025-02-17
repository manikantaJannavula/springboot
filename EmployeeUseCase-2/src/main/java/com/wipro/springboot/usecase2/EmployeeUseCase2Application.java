package com.wipro.springboot.usecase2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.List;

public class EmployeeUseCase2Application {

    public static void main(String[] args) {
        // Set up the session factory and session
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        Session session = factory.openSession();

        // HashMap to assign designations based on role
        HashMap<String, String> roleToDesignation = new HashMap<>();
        roleToDesignation.put("Developer", "Software Engineer");
        roleToDesignation.put("Tester", "QA Engineer");
        roleToDesignation.put("Architect", "Solution Architect");

        // CREATE operation - Add multiple employees
        addMultipleEmployees(session, roleToDesignation);

        // READ operation - Fetch all employees
        List<Employee> employees = getAllEmployees(session);
        for (Employee e : employees) {
            System.out.println("Employee: " + e);
        }

        // UPDATE operation - Update an employee's salary
        int employeeIdToUpdate = 1; // Assuming you want to update employee with ID 1
        updateEmployeeSalary(session, employeeIdToUpdate, 95000);

        // DELETE operation - Delete an employee by ID
        int employeeIdToDelete = 2; // Assuming you want to delete employee with ID 2
        deleteEmployee(session, employeeIdToDelete);

        // Closing session and factory
        session.close();
        factory.close();
    }

    // CREATE - Method to add multiple employees
    public static void addMultipleEmployees(Session session, HashMap<String, String> roleToDesignation) {
        Transaction tx = session.beginTransaction();

        Employee emp1 = new Employee();
        emp1.setName("Manikanta");
        emp1.setRole("Tester");
        emp1.setDesignation(roleToDesignation.get(emp1.getRole()));
        emp1.setSalary(75000);

        Employee emp2 = new Employee();
        emp2.setName("Anjali");
        emp2.setRole("Developer");
        emp2.setDesignation(roleToDesignation.get(emp2.getRole()));
        emp2.setSalary(90000);

        Employee emp3 = new Employee();
        emp3.setName("Raj");
        emp3.setRole("Architect");
        emp3.setDesignation(roleToDesignation.get(emp3.getRole()));
        emp3.setSalary(120000);

        session.save(emp1);
        session.save(emp2);
        session.save(emp3);

        tx.commit();
        System.out.println("Employees added: " + emp1 + ", " + emp2 + ", " + emp3);
    }

    // READ - Method to get all employees
    public static List<Employee> getAllEmployees(Session session) {
        return session.createQuery("FROM Employee", Employee.class).list();
    }

    // UPDATE - Method to update an employee's salary
    public static void updateEmployeeSalary(Session session, int employeeId, double newSalary) {
        Transaction tx = session.beginTransaction();

        Employee empToUpdate = session.get(Employee.class, employeeId);
        if (empToUpdate != null) {
            empToUpdate.setSalary(newSalary);
            session.update(empToUpdate);
            tx.commit();
            System.out.println("Updated Employee: " + empToUpdate);
        } else {
            System.out.println("Employee with ID " + employeeId + " not found!");
        }
    }

    // DELETE - Method to delete an employee by ID
    public static void deleteEmployee(Session session, int employeeId) {
        Transaction tx = session.beginTransaction();

        Employee empToDelete = session.get(Employee.class, employeeId);
        if (empToDelete != null) {
            session.delete(empToDelete);
            tx.commit();
            System.out.println("Deleted Employee with ID: " + employeeId);
        } else {
            System.out.println("Employee with ID " + employeeId + " not found!");
        }
    }
}
