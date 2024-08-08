package com.example.Portal.Controller;

import com.example.Portal.Model.PreviousClients;
import com.example.Portal.Service.PreviousClientsService;
import com.example.Portal.Service.PreviousClientsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/previousClients")
@CrossOrigin("http://localhost:3000")
public class PreviousClientsController {

    private PreviousClientsService previousClientsService = new PreviousClientsServiceImpl();

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody PreviousClients previousClients) {
        try {
            String json = new ObjectMapper().writeValueAsString(previousClients);
            System.out.println("previousClients = " + json);

            previousClientsService.savePreviousClients(previousClients);
            System.out.println("Connection reached PreviousClientsController");
            return ResponseEntity.status(HttpStatus.CREATED).body(previousClients);

        } catch (Exception e) {
            System.out.println("Exception in add PreviousClient: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding previousClients");
        }
    }

    @GetMapping("/getAll")
    public List<PreviousClients> getAllPreviousClients() {
        return previousClientsService.getAllPreviousClients();
    }

    @GetMapping("/{id}")
    public PreviousClients getPreviousClientsById(@PathVariable Long id) {
        return previousClientsService.getPreviousClientsById(Math.toIntExact(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePreviousClients(@PathVariable String id) {
        try {
            previousClientsService.deletePreviousClients(id);
            return ResponseEntity.ok().body("PreviousClient is deleted");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("PreviousClient is not deleted");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePreviousClients(@RequestBody PreviousClients previousClients) {
        try {
            previousClientsService.updatePreviousClients(previousClients);
            return ResponseEntity.ok().body("PreviousClient is updated");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("PreviousClient is not updated");
        }
    }
}
