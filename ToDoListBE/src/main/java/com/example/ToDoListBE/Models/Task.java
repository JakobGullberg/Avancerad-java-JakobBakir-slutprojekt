package com.example.ToDoListBE.Models;

// Modell
// Klassen Task representerar en "to-do"-uppgift med attribut för ID, namn, beskrivning och datum.
public class Task {
    private int id; // Unikt ID för varje uppgift
    private String name; // Namnet på uppgiften
    private String description; // Beskrivning av vad uppgiften handlar om
    private String date; // Datum då uppgiften ska utföras

    // Konstruktor för att skapa en ny uppgift
    public Task(int id, String name, String description, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    // Getter och setter för ID
    public int getId() {
        return id;
    }

    // Getter och setter för namn
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter och setter för beskrivning
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter och setter för datum
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}