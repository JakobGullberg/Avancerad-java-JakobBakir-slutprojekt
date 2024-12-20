package com.example.ToDoListBE.Services;

import com.example.ToDoListBE.Models.Task;
import com.example.ToDoListBE.Interfaces.TaskOperations;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// AbstractTaskService är en abstrakt klass som implementerar TaskOperations och tillhandahåller grundläggande logik
// för att hantera en HashMap av uppgifter (Task-objekt).
public abstract class AbstractTaskService implements TaskOperations {

    // Skyddad HashMap över uppgifter, där varje nyckel representerar ett unikt ID och värdet är Task-objektet.
    protected Map<Integer, Task> tasks = new HashMap<>();

    // Implementerar metoden från TaskOperations för att hämta alla uppgifter.
    @Override
    public List<Task> getAllTasks() {
        // Returnerar hela HashMapen som en lista av uppgifter.
        return tasks.values().stream().collect(Collectors.toList());
    }

    // Implementerar metoden från TaskOperations för att hämta en specifik uppgift baserat på dess ID.
    @Override
    public Task getTaskById(int id) {
        // Söker direkt i HashMapen efter uppgiften med det givna ID:t.
        // Om ingen uppgift hittas returneras null.
        return tasks.get(id);
    }

    // Implementerar metoden för att lägga till en ny uppgift.
    // Subklasser kan använda denna implementering direkt.
    public Task addTask(Task task) {
        // Kontrollera om en uppgift med samma ID redan finns i HashMapen.
        if (tasks.containsKey(task.getId())) {
            // Om ID:t redan finns, kasta ett undantag för att förhindra duplicering.
            throw new IllegalArgumentException("Task with ID " + task.getId() + " already exists.");
        }
        // Lägg till den nya uppgiften i HashMapen.
        tasks.put(task.getId(), task);
        return task;
    }

    // Implementerar metoden för att ta bort en uppgift baserat på dess ID.
    @Override
    public boolean deleteTask(int id) {
        // Tar bort uppgiften från HashMapen om ID:t matchar.
        // Returnerar true om en uppgift togs bort, annars false.
        return tasks.remove(id) != null;
    }
}
