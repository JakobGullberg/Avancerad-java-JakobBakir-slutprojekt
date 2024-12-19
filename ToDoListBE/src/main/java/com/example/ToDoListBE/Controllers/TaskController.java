package com.example.ToDoListBE.Controllers;

import com.example.ToDoListBE.Models.Task;
import com.example.ToDoListBE.Services.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Denna klass är en REST-kontroller för att hantera API, hämtas från Spring.
@RestController
@RequestMapping("/api/tasks") // Bas-URL för alla endpoints.
public class TaskController {
    // Skapar en objekt av TaskServiceImpl.
    private final TaskServiceImpl taskService = new TaskServiceImpl();

    // Konstruktor för några exempel uppgifter.
    public TaskController() {
        taskService.addTask(new Task(1, "Gör läxan", "Matematikuppgifter", "2024-12-20"));
        taskService.addTask(new Task(2, "Städa rummet", "Plocka undan och dammsug", "2024-12-21"));
        taskService.addTask(new Task(3, "Handla mat", "Köp mjölk, bröd och ägg", "2024-12-22"));
    }

    // Endpoint för att hämta alla uppgifter.
    // GET /api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        // Returnerar en lista med alla uppgifter.
        return taskService.getAllTasks();
    }

    // Endpoint för att hämta en specifik uppgift med dess ID.
    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        // Hämtar uppgiften med det givna ID:t.
        Task task = taskService.getTaskById(id);
        // Returnerar uppgiften om den finns, annars 404 Not Found.
        return task != null ? ResponseEntity.ok(task) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint för att lägga till en ny uppgift.
    // POST /api/tasks
    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        try {
            // Lägger till den nya uppgiften och returnerar den med status 201 Created.
            Task createdTask = taskService.addTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (Exception e) {
            // Om något går fel, returneras status 400 Bad Request.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint för att uppdatera en befintlig uppgift med dess ID.
    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
        // Uppdaterar uppgiften med det givna ID.
        Task task = taskService.updateTask(id, updatedTask);
        if (task != null) {
            // Returnerar den uppdaterade uppgiften om den hittas.
            return ResponseEntity.ok(task);
        } else {
            // Returnerar 404 Not Found om uppgiften inte hittas.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint för att ta bort en uppgift med dess ID.
    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        // Försöker ta bort uppgiften med det givna ID:t.
        boolean removed = taskService.deleteTask(id);
        if (removed) {
            // Om allt fungerar returneras ok.
            return ResponseEntity.ok("Task with ID " + id + " has been removed.");
        } else {
            // Om uppgiften inte hittas, returneras 404 Not Found.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID " + id + " not found.");
        }
    }
}
