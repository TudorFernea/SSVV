package service;

import domain.Student;
import repository.StudentXMLRepo;
import validation.StudentValidator;
import validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private static Service service;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        this.service = new Service(new StudentXMLRepo("fisiere/Studenti.xml"), new StudentValidator(), null, null, null, null);
        this.service.deleteStudent("1");
    }

    @org.junit.jupiter.api.Test
    void testcase1() {
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        var result = service.addStudent(student);
        assertNull(result);
    }

    @org.junit.jupiter.api.Test
    void testcase2() {
        Student student = new Student("1", "", 933, "ledamtotcodu@gmail.com");
        assertThrows(ValidationException.class, () -> {var result = service.addStudent(student);});
    }
}