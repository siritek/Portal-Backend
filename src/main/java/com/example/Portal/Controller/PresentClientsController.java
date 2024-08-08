package com.example.Portal.Controller;

import com.example.Portal.Model.PresentClients;
import com.example.Portal.Service.PresentClientsService;
import com.example.Portal.Service.PresentClientsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/presentClients")
@CrossOrigin("http://localhost:3000")
public class PresentClientsController {

    private PresentClientsService presentClientsService = new PresentClientsServiceImpl();

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PresentClients presentClients) {
        try {
            String json = new ObjectMapper().writeValueAsString(presentClients);
            System.out.println("presentClients = " + json);

            presentClientsService.savePresentClients(presentClients);
            System.out.println("Connection reached PresentClientsController");
            return ResponseEntity.status(HttpStatus.CREATED).body(presentClients);


        } catch (Exception e) {
            System.out.println("Exception in add PresentClient: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding presentClients");
        }
    }

    @GetMapping("/getAll")
    public List<PresentClients> getAllPresentClients() {
        return presentClientsService.getAllPresentClients();
    }

    @GetMapping("/{id}")
    public PresentClients getPresentClientsById(@PathVariable Long id) {
        return presentClientsService.getPresentClientsById(Math.toIntExact(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePresentClients(@PathVariable int id) {
        try {
            presentClientsService.deletePresentClients(id);
            return ResponseEntity.ok().body("PresentClient is deleted");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("PresentClient is not deleted");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePresentClients(@RequestBody PresentClients presentClients) {
        try {
            presentClientsService.updatePresentClients(presentClients);
            return ResponseEntity.ok().body("PresentClient is updated");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("PresentClient is not updated");
        }
    }
}
