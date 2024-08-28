//package com.example.Portal.Controller;
//
//
//import com.example.Portal.Model.Employee;
//import com.example.Portal.Service.EmployeeServiceImpl;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/employee")
//@CrossOrigin("http://localhost:3000")
//public class EmployeeController {
//
//
//    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
//
//    @PostMapping("/add")
//    public String add(@RequestBody Employee employee) {
//        try {
//            String json = new ObjectMapper().writeValueAsString(employee);
//            System.out.println("received this json from react for Employee :- " + json);
//            employeeService.saveEmployee(employee);
//            System.out.println("Connection reached Controller");
//        } catch (Exception e) {
//            System.out.println(e);
//        }            return "New Employee info is added";
//
//    }
//
//    @GetMapping("/getAll")
//    public List<Employee> getAllempolyee() {
//        return employeeService.getAllemployee();
//    }
//}



package com.example.Portal.Controller;
import com.example.Portal.Model.Employee;
import com.example.Portal.Service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("http://localhost:3001")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService; // Inject EmployeeServiceImpl

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Employee employee) {
        try {
            // Log the received JSON for debugging
            String json = new ObjectMapper().writeValueAsString(employee);
            System.out.println("Received this JSON from client for Employee: " + json);

            // Save the employee
            employeeService.saveEmployee(employee);
            System.out.println("Connection reached EmployeeController");

            // Return the employee object and a success message
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);

        } catch (Exception e) {
            System.out.println("Exception in EmployeeController: " + e);
            // Return a JSON error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error adding employee\"}");
        }
    }

    @GetMapping("/getAll")
    public List<Employee> getAllemployee() {
        return employeeService.getAllemployee();
    }
}

