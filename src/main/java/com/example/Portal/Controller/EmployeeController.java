package com.example.Portal.Controller;


import com.example.Portal.Model.Employee;
import com.example.Portal.Service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {


    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @PostMapping("/add")
    public String add(@RequestBody Employee employee) {
        try {
            String json = new ObjectMapper().writeValueAsString(employee);
            System.out.println("received this json from react for Employee :- " + json);
            employeeService.saveEmployee(employee);
            System.out.println("Connection reached Controller");
        } catch (Exception e) {
            System.out.println(e);
        }            return "New Employee info is added";

    }

    @GetMapping("/getAll")
    public List<Employee> getAllempolyee() {
        return employeeService.getAllemployee();
    }
}


