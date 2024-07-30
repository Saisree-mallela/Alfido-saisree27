import java.util.*;

// Main class
public class StudentManagementSystem {
    public static void main(String[] args) {
        School school = new School();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Record Grade");
            System.out.println("5. View Student Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    school.addStudent();
                    break;
                case 2:
                    school.addCourse();
                    break;
                case 3:
                    school.enrollStudentInCourse();
                    break;
                case 4:
                    school.recordGrade();
                    break;
                case 5:
                    school.viewStudentDetails();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

// School class to manage students and courses
class School {
    private List<Student> students;
    private List<Course> courses;
    private Scanner scanner;
    
    public School() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        students.add(new Student(name, id));
        System.out.println("Student added successfully.");
    }
    
    public void addCourse() {
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        courses.add(new Course(name, code));
        System.out.println("Course added successfully.");
    }
    
    public void enrollStudentInCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        
        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);
        
        if (student != null && course != null) {
            student.enrollInCourse(course);
            System.out.println("Student enrolled in course successfully.");
        } else {
            System.out.println("Student or course not found.");
        }
    }
    
    public void recordGrade() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        Student student = findStudent(studentId);
        Course course = findCourse(courseCode);
        
        if (student != null && course != null) {
            student.setGrade(course, grade);
            System.out.println("Grade recorded successfully.");
        } else {
            System.out.println("Student or course not found.");
        }
    }
    
    public void viewStudentDetails() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudent(studentId);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }
    
    private Student findStudent(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    private Course findCourse(String code) {
        return courses.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}

// Student class
class Student {
    private String name;
    private String id;
    private Map<Course, Double> grades;
    
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.grades = new HashMap<>();
    }
    
    public String getId() {
        return id;
    }
    
    public void enrollInCourse(Course course) {
        grades.put(course, null);
    }
    
    public void setGrade(Course course, double grade) {
        if (grades.containsKey(course)) {
            grades.put(course, grade);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: ").append(name).append(" (ID: ").append(id).append(")\n");
        sb.append("Courses and Grades:\n");
        for (Map.Entry<Course, Double> entry : grades.entrySet()) {
            sb.append(entry.getKey().getName()).append(": ");
            sb.append(entry.getValue() == null ? "Not graded" : entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}

// Course class
class Course {
    private String name;
    private String code;
    
    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCode() {
        return code;
    }
}