import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

public class TodoList {
    private static final String FILENAME = "tasks.txt";
    private List<Task> tasks;

    public TodoList() {
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        saveTasksToFile();
    }

    public void listTasks() {
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasksToFile();
            System.out.println("Task removed.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    private void loadTasksFromFile() {
        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNextLine()) {
                String description = scanner.nextLine();
                Task task = new Task(description);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist; no tasks loaded.
        }
    }

    private void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(FILENAME)) {
            for (Task task : tasks) {
                writer.println(task.getDescription());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Remove Task");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    todoList.addTask(description);
                    break;
                case 2:
                    todoList.listTasks();
                    break;
                case 3:
                    System.out.print("Enter task index to remove: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    todoList.removeTask(index - 1);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                       scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
     
    }
}
