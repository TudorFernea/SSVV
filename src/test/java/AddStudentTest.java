import org.example.src.domain.Student;
import org.example.src.repository.StudentXMLRepo;
import org.example.src.service.Service;
import org.example.src.validation.StudentValidator;
import org.example.src.validation.ValidationException;


import static org.junit.jupiter.api.Assertions.*;

class AddStudentTest {

    private static Service service;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        service = new Service(new StudentXMLRepo("src/Test/java/fisiere/Studenti.xml"), new StudentValidator(), null, null, null, null);
    }

    @org.junit.jupiter.api.Test
    void Testcase1() {
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        if (service.findStudent("1") != null)
            service.deleteStudent("1");
        assertNull(service.addStudent(student));
    }

    @org.junit.jupiter.api.Test
    void Testcase2() {
        Student student = null;
        assertThrows(NullPointerException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase3() {
        Student student = new Student("", "Fernea", 933, "ledamtotcodu@gmail.com");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase4() {
        Student student = new Student("1", "", 933, "ledamtotcodu@gmail.com");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase5() {
        Student student = new Student("1", "Fernea", -1, "ledamtotcodu@gmail.com");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase6() {
        Student student = new Student("1", "Fernea", 933, "");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase7() {
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        service.addStudent(student);
        assertNotNull(service.addStudent(student));
    }
}

