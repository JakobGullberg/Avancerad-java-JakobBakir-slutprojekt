package com.example.ToDoListBE.Interfaces;

import com.example.ToDoListBE.Models.Task;
import java.util.List;

// TaskOperations är ett interface som är kontraktet för Task-objekt.
public interface TaskOperations {

    // Hämtar en lista med alla uppgifter.
    // Returnerar en lista av Task-objekt.
    List<Task> getAllTasks();

    // Hämtar en specifik uppgift baserat på ID.
    // Returnerar Task-objektet om det finns.
    Task getTaskById(int id);

    // Lägger till en ny uppgift.
    // Tar emot ett Task-objekt som ska läggas till och returnerar det skapade Task-objektet.
    Task addTask(Task task);

    // Uppdaterar en befintlig uppgift baserat på dess ID.
    // Tar emot ID:t för uppgiften som ska uppdateras och det uppdaterade Task-objektet.
    // Returnerar det uppdaterade Task-objektet, eller null om uppgiften inte hittas.
    Task updateTask(int id, Task updatedTask);

    // Tar bort en uppgift baserat på dess ID.
    // Returnerar true om uppgiften togs bort, annars false.
    boolean deleteTask(int id);
}
