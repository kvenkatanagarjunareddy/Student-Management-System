package StudentManagementSystem;


import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students;
    private final String FILE_NAME = "students.txt";

    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Add new student
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    // View all students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Search student by ID
    public Student searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    // Delete student
    public boolean deleteStudent(int id) {
        Student student = searchStudent(id);
        if (student != null) {
            students.remove(student);
            saveToFile();
            return true;
        }
        return false;
    }

    // Save data to file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(new ArrayList<>(students));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load data from file
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            students = new ArrayList<>();
        }
    }
}
