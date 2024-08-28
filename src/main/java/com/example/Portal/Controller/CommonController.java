package com.example.Portal.Controller;

import com.example.Portal.Model.common;
import com.example.Portal.Service.EmployeeServiceImpl;
import com.example.Portal.Service.KidsServiceImpl;
import com.example.Portal.Service.SpouseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/common")
@CrossOrigin("http://localhost:3001")
public class CommonController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private KidsServiceImpl kidsService;

    @Autowired
    private SpouseServiceImpl spouseService;

    @PostMapping("/add")
    public ResponseEntity<?> addFamily(@RequestBody common Common) {
        try {
            // Log the received JSON for debugging
            String json = new ObjectMapper().writeValueAsString(Common);
            System.out.println("Received this JSON from client: " + json);

            // Save the employee, kids, and spouse
            employeeService.saveEmployee(Common.getEmployee());
            kidsService.saveKids(Common.getKids());
            spouseService.saveSpouse(Common.getSpouse());

            System.out.println("Data successfully saved for Employee, Kids, and Spouse");

            // Return a success message
            return ResponseEntity.status(HttpStatus.CREATED).body(Common);

        } catch (Exception e) {
            System.out.println("Exception in FamilyController: " + e);
            // Return a JSON error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error adding family data\"}");
        }
    }
}
