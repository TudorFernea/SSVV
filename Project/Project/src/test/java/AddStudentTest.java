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
        service = new Service(new StudentXMLRepo("src/test/java/fisiere/Studenti.xml"), new StudentValidator(), null, null, null, null);
    }

    @org.junit.jupiter.api.Test
    void testcase1() {
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        if (service.findStudent("1") != null)
            service.deleteStudent("1");
        assertNull(service.addStudent(student));
    }

    @org.junit.jupiter.api.Test
    void testcase2() {
        Student student = null;
        assertThrows(NullPointerException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void testcase3() {
        Student student = new Student("", "Fernea", 933, "ledamtotcodu@gmail.com");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void testcase4() {
        Student student = new Student("1", "", 933, "ledamtotcodu@gmail.com");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void testcase5() {
        Student student = new Student("1", "Fernea", -1, "ledamtotcodu@gmail.com");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void testcase6() {
        Student student = new Student("1", "Fernea", 933, "");
        assertThrows(ValidationException.class, () -> {
            service.addStudent(student);
        });
    }

    @org.junit.jupiter.api.Test
    void testcase7() {
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        service.addStudent(student);
        assertNotNull(service.addStudent(student));
    }
}

