# Sequence Diagram — ToDo App

This file contains a visual sequence diagram for the ToDo app (backend + frontend). Copy the Mermaid block into any Markdown viewer that supports Mermaid or export it to an image using the commands below.

## Mermaid diagram

```mermaid
sequenceDiagram
    participant FE as React Frontend (localhost:3000)
    participant BE as Spring Boot (localhost:8080)
    participant Ctrl as ToDoController
    participant Repo as TaskRepository (JpaRepository)
    participant JPA as Hibernate/JPA
    participant DB as MySQL

    %% GET /tasks
    FE->>BE: GET /tasks
    BE->>Ctrl: route -> getTasks()
    Ctrl->>Repo: findAll()
    Repo->>JPA: JPA -> SQL SELECT
    JPA->>DB: SELECT * FROM task
    DB-->>JPA: rows
    JPA-->>Repo: entities
    Repo-->>Ctrl: List<Task>
    Ctrl-->>FE: 200 OK + JSON list

    %% POST /tasks
    FE->>BE: POST /tasks {title,completed}
    BE->>Ctrl: route -> createTask()
    Ctrl->>Repo: save(task)
    Repo->>JPA: INSERT
    JPA->>DB: INSERT INTO task (...)
    DB-->>JPA: generated id
    JPA-->>Repo: saved entity
    Repo-->>Ctrl: Task with id
    Ctrl-->>FE: 201 Created + JSON

    %% PUT /tasks/{id}
    FE->>BE: PUT /tasks/{id} {title,completed}
    BE->>Ctrl: route -> updateTask(id)
    Ctrl->>Repo: findById(id)
    Repo-->>Ctrl: Task (or null)
    alt task exists
        Ctrl->>Repo: save(updatedTask)
        Repo->>JPA: UPDATE
        JPA->>DB: UPDATE task SET ...
        Ctrl-->>FE: 200 OK + JSON
    else not found
        Ctrl-->>FE: 404 or null (current code returns null)
    end

    %% DELETE /tasks/{id}
    FE->>BE: DELETE /tasks/{id}
    BE->>Ctrl: route -> deleteTask(id)
    Ctrl->>Repo: deleteById(id)
    Repo->>JPA: DELETE
    JPA->>DB: DELETE FROM task WHERE id=...
    Ctrl-->>FE: 200 OK + "Task deleted!"
```

## Export to image (SVG/PNG)

You can export this Mermaid diagram to an image using `@mermaid-js/mermaid-cli`.

Install and run (global install option):

```bash
npm install -g @mermaid-js/mermaid-cli
mmdc -i demo/docs/sequence.mmd -o demo/docs/sequence.svg
```

Or using `npx` without global install:

```bash
npx -p @mermaid-js/mermaid-cli mmdc -i demo/docs/sequence.mmd -o demo/docs/sequence.svg
```

Embed the exported SVG in Markdown (works on GitHub):

```markdown
![Sequence diagram](docs/sequence.svg)
```

If you want, I can attempt to generate and add the exported SVG here — say "generate image" and I'll try it (requires Node/npm available on your machine).
