package com.example.ToDoListBE.Services;

import com.example.ToDoListBE.Models.Task;
import com.example.ToDoListBE.Interfaces.TaskOperations;
import java.util.ArrayList;
import java.util.List;

// AbstractTaskService är en abstrakt klass som implementerar TaskOperations och tillhandahåller grundläggande logik
// för att hantera en lista av uppgifter (Task-objekt).
public abstract class AbstractTaskService implements TaskOperations {

    // Skyddad lista över uppgifter, som används av subklasser för att lagra uppgifter.
    protected List<Task> tasks = new ArrayList<>();

    // Implementerar metoden från TaskOperations för att hämta alla uppgifter.
    @Override
    public List<Task> getAllTasks() {
        // Returnerar hela listan av uppgifter.
        return tasks;
    }

    // Implementerar metoden från TaskOperations för att hämta en specifik uppgift baserat på dess ID.
    @Override
    public Task getTaskById(int id) {
        // Använder Java Streams för att hitta första uppgiften med ID.
        // Om ingen uppgift hittas returneras null.
        return tasks.stream()
                .filter(task -> task.getId() == id) // Filtrerar efter ID.
                .findFirst() // Hämtar första matchande objektet.
                .orElse(null); // Om inget matchar returneras null.
    }

    // Implementerar metoden för att lägga till en ny uppgift.
    // Subklasser kan använda denna implementering direkt.
    public Task addTask(Task task) {
        // Kontrollera om en uppgift med samma ID redan finns.
        if (tasks.stream().anyMatch(existingTask -> existingTask.getId() == task.getId())) {
            // Om ID:t redan finns, kasta ett undantag för att förhindra duplicering.
            throw new IllegalArgumentException("Task with ID " + task.getId() + " already exists.");
        }
        // Lägg till den nya uppgiften i listan.
        tasks.add(task);
        return task;
    }

    // Implementerar metoden för att ta bort en uppgift baserat på dess ID.
    @Override
    public boolean deleteTask(int id) {
        // Tar bort uppgiften från listan om ID:t matchar.
        // Returnerar true om en uppgift togs bort, annars false.
        return tasks.removeIf(task -> task.getId() == id);
    }
}
