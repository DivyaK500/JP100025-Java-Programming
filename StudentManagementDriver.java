import java.util.*;

class Student {
    private String name;
    private int id;
    private double marks;

    public Student(String name, int id, double marks) {
        this.name = name;
        this.id = id;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

class StudentManagement {
    private Map<Integer, Student> students = new HashMap<>();

    public void addStudent(int id, String name, double marks) {
        if (students.containsKey(id)) {
            System.out.println("Student with this ID already exists.");
        } else {
            students.put(id, new Student(name, id, marks));
            System.out.println("Student added successfully.");
        }
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : students.values()) {
                System.out.println(student);
            }
        }
    }

    public void updateStudent(int id, String newName, double newMarks) {
        if (students.containsKey(id)) {
            Student student = students.get(id);
            student.setName(newName);
            student.setMarks(newMarks);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student with this ID does not exist.");
        }
    }

    public void deleteStudent(int id) {
        if (students.remove(id) != null) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student with this ID does not exist.");
        }
    }

    public void sortByName() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
        } else {
            List<Student> sortedList = new ArrayList<>(students.values());
            sortedList.sort(Comparator.comparing(Student::getName));
            for (Student student : sortedList) {
                System.out.println(student);
            }
        }
    }

    public void sortByMarks() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
        } else {
            List<Student> sortedList = new ArrayList<>(students.values());
            sortedList.sort(Comparator.comparingDouble(Student::getMarks).reversed());
            for (Student student : sortedList) {
                System.out.println(student);
            }
        }
    }
}

class StudentManagementDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Student\n2. View Students\n3. Update Student\n4. Delete Student\n5. Sort by Name\n6. Sort by Marks\n7. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 :{
                    System.out.println("Enter ID, Name, and Marks:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    String name = sc.nextLine();
                    double marks = sc.nextDouble();
                    sm.addStudent(id, name, marks);
                    break;
                }
                case 2:
                {
                    sm.viewStudents();
                    break;}
                case 3 : {
                    System.out.println("Enter ID, New Name, and New Marks:");
                    int id = sc.nextInt();
                    sc.nextLine();
                    String name = sc.nextLine();
                    double marks = sc.nextDouble();
                    sm.updateStudent(id, name, marks);
                    break;
                }
                case 4 :{
                    System.out.println("Enter ID to delete:");
                    int id = sc.nextInt();
                    sm.deleteStudent(id);
                    break;
                }
                case 5:{sm.sortByName();break;}
                case 6: {sm.sortByMarks();break;}
                case 7: {
                    running = false;
                    System.out.println("Exiting...");
                    break;
                }
                default:  System.out.println("Invalid option. Try again.");
            }
        }
        sc.close();
    }
}
