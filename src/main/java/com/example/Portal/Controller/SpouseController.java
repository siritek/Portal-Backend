package com.example.Portal.Controller;

import com.example.Portal.Model.Spouse;
import com.example.Portal.Service.SpouseServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/spouses")
@CrossOrigin("http://localhost:3000")
public class SpouseController {


    private SpouseServiceImpl spouseService = new SpouseServiceImpl();

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Spouse spouse) throws JsonProcessingException {
        try {
            // Log the received JSON for debugging
            String json = new ObjectMapper().writeValueAsString(spouse);
            System.out.println("Received this JSON from client for Spouse: " + json);

            // Save the spouse
            spouseService.saveSpouse(spouse);
            System.out.println("Connection reached SpouseController");

            // Return the spouse object and a success message
            return ResponseEntity.status(HttpStatus.CREATED).body(spouse);

        } catch (SQLException e) {
            System.out.println("Exception in SpouseController: " + e);
            // Return a JSON error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error adding spouse\"}");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Spouse>> getAllSpouses() {
        try {
            List<Spouse> spouses = spouseService.getAllSpouses();
            return ResponseEntity.ok(spouses);
        } catch (Exception e) {
            System.out.println("Exception in getAllSpouses: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{spouseId}")
    public ResponseEntity<?> getSpouseById(@PathVariable Long spouseId) {
        try {
            Spouse spouse = spouseService.getSpouseById(spouseId);
            if (spouse != null) {
                return ResponseEntity.ok(spouse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Spouse not found\"}");
            }
        } catch (Exception e) {
            System.out.println("Exception in getSpouseById: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error retrieving spouse\"}");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSpouse(@RequestBody Spouse spouse) {
        try {
            spouseService.updateSpouse(spouse);
            return ResponseEntity.ok("{\"message\": \"Spouse updated successfully\"}");
        } catch (SQLException e) {
            System.out.println("Exception in updateSpouse: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to update spouse\"}");
        }
    }

    @DeleteMapping("/delete/{spouseId}")
    public ResponseEntity<?> deleteSpouse(@PathVariable Long spouseId) {
        try {
            spouseService.deleteSpouse(spouseId);
            return ResponseEntity.ok("{\"message\": \"Spouse deleted successfully\"}");
        } catch (SQLException e) {
            System.out.println("Exception in deleteSpouse: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to delete spouse\"}");
        }
    }
}
