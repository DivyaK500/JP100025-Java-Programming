import jdk.jfr.Description;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String description;
    private boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    @Override
    public String toString() {
        return (isComplete ? "[X] " : "[ ] ") + description;
    }
}

public class ToDoList {
    private ArrayList<Task> tasks;
    private Scanner scanner;

    public ToDoList() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addTask(String description) {
        tasks.add(new Task(description));
    }

    public void editTask(int index, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).editDescription(newDescription);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskComplete(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markComplete();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void displayTasks() {
        System.out.println("Pending Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isComplete()) {
                System.out.println(i + ": " + tasks.get(i));
            }
        }
        System.out.println("\nCompleted Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isComplete()) {
                System.out.println(i + ": " + tasks.get(i));
            }
        }
    }

    public void saveTasksToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                writer.write(task.getDescription() + "|" + (task.isComplete() ? "1" : "0"));
                writer.newLine();
            }
            System.out.println("Tasks saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasksFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Task task = new Task(parts[0]);
                if (parts[1].equals("1")) {
                    task.markComplete();
                }
                tasks.add(task);
            }
            System.out.println("Tasks loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ToDoList todoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("\nTo-Do List Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task Complete");
            System.out.println("5. Display Tasks");
            System.out.println("6. Save Tasks to File");
            System.out.println("7. Load Tasks from File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            command = scanner.nextLine();

            switch (command) {
                case "1":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    todoList.addTask(description);
                    break;
                case "2":
                    System.out.print("Enter task index to edit: ");
                    int editIndex = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter new task description: ");
                    String newDescription = scanner.nextLine();
                    todoList.editTask(editIndex, newDescription); // Corrected this line
                    break;
                case "3":
                    System.out.print("Enter task index to delete: ");
                    int deleteIndex = Integer.parseInt(scanner.nextLine());
                    todoList.deleteTask(deleteIndex);
                    break;
                case "4":
                    System.out.print("Enter task index to mark complete: ");
                    int completeIndex = Integer.parseInt(scanner.nextLine());
                    todoList.markTaskComplete(completeIndex);
                    break;
                case "5":
                    todoList.displayTasks();
                    break;
                case "6":
                    System.out.print("Enter filename to save tasks: ");
                    String saveFilename = scanner.nextLine();
                    todoList.saveTasksToFile(saveFilename);
                    break;
                case "7":
                    System.out.print("Enter filename to load tasks: ");
                    String loadFilename = scanner.nextLine();
                    todoList.loadTasksFromFile(loadFilename);
                    break;
                case "0":
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!command.equals("0"));
        scanner.close();
    }
}