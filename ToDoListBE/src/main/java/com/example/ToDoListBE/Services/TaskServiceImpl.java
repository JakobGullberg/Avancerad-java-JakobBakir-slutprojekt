package com.example.ToDoListBE.Services;

import com.example.ToDoListBE.Models.Task;

// TaskServiceImpl är en konkret implementation av AbstractTaskService.
// Den utökar AbstractTaskService och implementerar de återstående metoderna för att lägga till och uppdatera uppgifter.
public class TaskServiceImpl extends AbstractTaskService {

    @Override
    public Task addTask(Task task) {
        // Genererar ett nytt ID per auto om ID fattas eller ogiltigt.
        int newId = tasks.stream()
                .mapToInt(Task::getId) // Extraherar ID från varje uppgift.
                .max()                 // Hittar det högsta ID:t i listan.
                .orElse(0) + 1;        // Om listan är tom, starta från 1.

        // Sätter ny ID på uppgiften.
        task.setId(newId);
        // Lägger till uppgiften i listan.
        tasks.add(task);
        return task; // Returnerar nya uppgiften.
    }

    @Override
    public Task updateTask(int id, Task updatedTask) {
        // Listar alla uppgifter i listan.
        for (Task task : tasks) {
            // Om en uppgift med ID hittas.
            if (task.getId() == id) {
                // Uppdaterar uppgiftens fält med värden från den uppdaterade uppgiften.
                task.setName(updatedTask.getName());
                task.setDescription(updatedTask.getDescription());
                task.setDate(updatedTask.getDate());
                return task; // Returnerar den uppdaterade uppgiften.
            }
        }
        // Om ingen uppgift med ID hittas, returnera null.
        return null;
    }
}
