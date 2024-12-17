package com.example.ToDoListBE.Controllers;

import com.example.ToDoListBE.Models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// RestController hanterar alla API-anrop för uppgiftshantering
@RestController
@RequestMapping("/api/tasks") // Bas-URL för API:et
public class TaskController {
    private List<Task> tasks = new ArrayList<>(); // Lista för att lagra uppgifter i minnet

    // Konstruktor som lägger till några exempeluppgifter
    public TaskController() {
        tasks.add(new Task(1, "Gör läxan", "Matematikuppgifter", "2024-12-20"));
        tasks.add(new Task(2, "Städa rummet", "Plocka undan och dammsug", "2024-12-21"));
        tasks.add(new Task(3, "Handla mat", "Köp mjölk, bröd och ägg", "2024-12-22"));
    }

    // GET /api/tasks - Hämtar alla uppgifter
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks; // Returnerar hela listan av uppgifter
    }

    // GET /api/tasks/{id} - Hämtar en specifik uppgift med dess ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        // Söker efter uppgiften med det givna ID:et
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok) // Returnerar uppgiften om den hittas
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Om inte, returnera 404
    }

    // POST /api/tasks - Skapar en ny uppgift
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        if (task != null && task.getId() > 0) {
            tasks.add(task); // Lägger till den nya uppgiften i listan
            return ResponseEntity.status(HttpStatus.CREATED).body(task); // Returnerar 201 Created
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Returnerar 400 Bad Request om input är felaktig
        }
    }

    // PUT /api/tasks/{id} - Uppdaterar en befintlig uppgift
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                // Uppdaterar fält med nya värden
                task.setName(updatedTask.getName());
                task.setDescription(updatedTask.getDescription());
                task.setDate(updatedTask.getDate());
                return ResponseEntity.ok(task); // Returnerar den uppdaterade uppgiften
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Returnerar 404 om uppgiften inte hittades
    }

    // DELETE /api/tasks/{id} - Tar bort en specifik uppgift
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        // Försöker ta bort uppgiften med det givna ID:et
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) {
            return ResponseEntity.ok("Task with ID " + id + " has been removed."); // Returnerar 200 OK om uppgiften togs bort
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID " + id + " not found."); // Returnerar 404 om uppgiften inte hittades
        }
    }
}