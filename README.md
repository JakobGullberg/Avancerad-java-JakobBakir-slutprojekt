# To-Do List Backend

## Beskrivning

Detta projekt är en enkel **To-Do List Backend-applikation** byggd i Java med hjälp av **Spring Boot**. Applikationen tillhandahåller RESTful API:er för att hantera uppgifter (Tasks). Den stöder CRUD-operationer som:

- Skapa en uppgift (Create)
- Hämta en eller flera uppgifter (Read)
- Uppdatera en uppgift (Update)
- Ta bort en uppgift (Delete)

Alla uppgifter hanteras med en **HashMap** där varje uppgift identifieras av ett unikt ID.

## Funktionalitet

Följande REST API-endpoints är tillgängliga:

### 1. Hämta alla uppgifter

**GET** `/api/tasks`

- Returnerar en lista med alla uppgifter.

### 2. Hämta en specifik uppgift

**GET** `/api/tasks/{id}`

- Hämtar en specifik uppgift baserat på dess ID.
- Returnerar 404 om uppgiften inte hittas.

### 3. Skapa en ny uppgift

**POST** `/api/tasks`

- Lägger till en ny uppgift i systemet.
- Automatisk ID-generering sker om ID saknas.

### 4. Uppdatera en uppgift

**PUT** `/api/tasks/{id}`

- Uppdaterar en befintlig uppgift baserat på ID.
- Returnerar 404 om uppgiften inte hittas.

### 5. Ta bort en uppgift

**DELETE** `/api/tasks/{id}`

- Tar bort en specifik uppgift baserat på ID.
- Returnerar 404 om uppgiften inte hittas.

## Projektstruktur

Projektet är organiserat enligt följande:

```
ToDoListBE/
|-- src/
|   |-- main/
|       |-- java/com/example/ToDoListBE/
|           |-- Controllers/          # Innehåller TaskController som hanterar REST API-endpoints
|           |-- Interfaces/           # Innehåller TaskOperations (interface) som definierar kontraktet för CRUD-operationer
|           |-- Models/               # Innehåller Task-modellen
|           |-- Services/             # Innehåller AbstractTaskService och TaskServiceImpl
|       |-- resources/
|           |-- application.properties
|-- README.md
```

### Nyckelklasser

- **TaskController**:
    - Hanterar API-endpoints för CRUD-operationer.
- **TaskOperations**:
    - Ett interface som definierar kontraktet för alla CRUD-metoder.
- **AbstractTaskService**:
    - En abstrakt klass som implementerar TaskOperations och tillhandahåller grundläggande logik för Task-hantering.
- **TaskServiceImpl**:
    - En konkret klass som utökar AbstractTaskService och implementerar specifik logik som ID-generering.
- **Task**:
    - Modellen som representerar en uppgift med attribut som ID, namn, beskrivning och datum.

## Krav

Följande krav måste vara uppfyllda för att köra projektet:

1. **Java 17** eller högre.
2. **Maven** installerat.
3. En IDE som stöder Spring Boot (t.ex., IntelliJ IDEA eller Eclipse).

## Installation och körning

1. Klona repot:
   ```bash
   git clone <repository-url>
   ```
2. Öppna projektet i din IDE (t.ex. IntelliJ IDEA eller Eclipse).
3. Kör applikationen genom att köra huvudklassen `ToDoListBeApplication`.

Applikationen kommer att vara tillgänglig på `http://localhost:8080`.

## Testning

Testa API:et med verktyget **Postman**. Följande är exempel på hur du kan använda Postman för att testa de olika API-endpoints:

### Skapa en ny uppgift:
1. Öppna Postman.
2. Skapa en ny **POST**-begäran med URL:
   ```
   http://localhost:8080/api/tasks
   ```
3. Gå till fliken **Body** och välj **raw** samt formatet **JSON**.
4. Ange följande JSON i textfältet:
   ```json
   {
       "name": "Läs bok",
       "description": "Läs 30 sidor",
       "date": "2024-12-25"
   }
   ```
5. Klicka på **Send** för att skicka begäran.

### Hämta alla uppgifter:
1. Skapa en ny **GET**-begäran med URL:
   ```
   http://localhost:8080/api/tasks
   ```
2. Klicka på **Send** för att få en lista med alla uppgifter.

### Uppdatera en uppgift:
1. Skapa en ny **PUT**-begäran med URL:
   ```
   http://localhost:8080/api/tasks/1
   ```
2. Gå till fliken **Body**, välj **raw** samt formatet **JSON**.
3. Ange följande JSON i textfältet:
   ```json
   {
       "name": "Gå till gymmet",
       "description": "Träning",
       "date": "2024-12-26"
   }
   ```
4. Klicka på **Send** för att uppdatera uppgiften.

### Ta bort en uppgift:
1. Skapa en ny **DELETE**-begäran med URL:
   ```
   http://localhost:8080/api/tasks/1
   ```
2. Klicka på **Send** för att ta bort uppgiften med ID 1.