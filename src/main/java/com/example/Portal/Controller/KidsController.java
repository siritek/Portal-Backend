package com.example.Portal.Controller;

import com.example.Portal.Model.Kids;
import com.example.Portal.Service.KidsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kids")
@CrossOrigin("http://localhost:3000")
public class KidsController {


    private KidsServiceImpl kidsService = new KidsServiceImpl();

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Kids kid) {
        try {
            // Log the received JSON for debugging
            String json = new ObjectMapper().writeValueAsString(kid);
            System.out.println("Received this JSON from client for Kid: " + json);

            // Save the kid
            kidsService.saveKid(kid);
            System.out.println("Connection reached KidsController");

            // Return the kid object and a success message
            return ResponseEntity.status(HttpStatus.CREATED).body(kid);

        } catch (Exception e) {
            System.out.println("Exception in KidsController: " + e);
            // Return a JSON error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error adding kid\"}");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Kids>> getAllKids() {
        try {
            List<Kids> kids = kidsService.getAllKids();
            return ResponseEntity.ok(kids);
        } catch (Exception e) {
            System.out.println("Exception in getAllKids: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{kidId}")
    public ResponseEntity<?> getKidById(@PathVariable Long kidId) {
        try {
            Kids kid = kidsService.getKidById(kidId);
            if (kid != null) {
                return ResponseEntity.ok(kid);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Kid not found\"}");
            }
        } catch (Exception e) {
            System.out.println("Exception in getKidById: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error retrieving kid\"}");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateKid(@RequestBody Kids kid) {
        try {
            kidsService.updateKid(kid);
            return ResponseEntity.ok("{\"message\": \"Kid updated successfully\"}");
        } catch (Exception e) {
            System.out.println("Exception in updateKid: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to update kid\"}");
        }
    }

    @DeleteMapping("/delete/{kidId}")
    public ResponseEntity<?> deleteKid(@PathVariable Long kidId) {
        try {
            kidsService.deleteKid(kidId);
            return ResponseEntity.ok("{\"message\": \"Kid deleted successfully\"}");
        } catch (Exception e) {
            System.out.println("Exception in deleteKid: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to delete kid\"}");
        }
    }
}
