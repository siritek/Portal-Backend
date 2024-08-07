package com.example.Portal.Service;

import com.example.Portal.Model.Employee;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface EmployeeService {
    public String saveEmployee(Employee employee) throws SQLException;

    public List<Employee> getAllemployee();

}
