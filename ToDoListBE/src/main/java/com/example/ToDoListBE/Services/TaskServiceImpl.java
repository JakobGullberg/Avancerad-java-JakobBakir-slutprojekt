package com.example.ToDoListBE.Services;

import com.example.ToDoListBE.Models.Task;

// TaskServiceImpl är en konkret implementation av AbstractTaskService.
// Den utökar AbstractTaskService och implementerar de återstående metoderna för att lägga till och uppdatera uppgifter.
public class TaskServiceImpl extends AbstractTaskService {

    @Override
    public Task addTask(Task task) {
        // Genererar ett nytt ID automatiskt om ID saknas eller är ogiltigt.
        int newId = tasks.keySet().stream()
                .mapToInt(Integer::intValue) // Extraherar alla ID från nycklarna i HashMapen.
                .max()                      // Hittar det högsta ID:t i HashMapen.
                .orElse(0) + 1;             // Om HashMapen är tom, starta från 1.

        // Sätter nytt ID på uppgiften.
        task.setId(newId);
        // Lägg till den nya uppgiften i HashMapen.
        tasks.put(task.getId(), task);
        return task; // Returnerar den nya uppgiften.
    }

    @Override
    public Task updateTask(int id, Task updatedTask) {
        // Kontrollera om uppgiften med det givna ID:t finns i HashMapen.
        if (tasks.containsKey(id)) {
            // Uppdaterar uppgiftens fält med värden från den uppdaterade uppgiften.
            Task existingTask = tasks.get(id);
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDate(updatedTask.getDate());
            return existingTask; // Returnerar den uppdaterade uppgiften.
        }
        // Om ingen uppgift med det givna ID:t hittas, returnera null.
        return null;
    }
}
