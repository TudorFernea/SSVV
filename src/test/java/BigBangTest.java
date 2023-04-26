import org.example.src.domain.Nota;
import org.example.src.domain.Student;
import org.example.src.domain.Tema;
import org.example.src.repository.NotaXMLRepo;
import org.example.src.repository.StudentXMLRepo;
import org.example.src.repository.TemaXMLRepo;
import org.example.src.service.Service;
import org.example.src.validation.NotaValidator;
import org.example.src.validation.StudentValidator;
import org.example.src.validation.TemaValidator;
import org.example.src.validation.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BigBangTest {
    private static Service service;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        StudentXMLRepo r1 = new StudentXMLRepo("src/Test/java/fisiere/Studenti.xml");
        TemaXMLRepo r2 = new TemaXMLRepo("src/Test/java/fisiere/Teme.xml");
        service = new Service(r1, new StudentValidator(),  r2, new TemaValidator(),  new NotaXMLRepo("src/Test/java/fisiere/Note.xml"), new NotaValidator(r1,r2));
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
        Tema tema = new Tema("1", "abc", 6, 5);
        if (service.findTema("1") != null)
            service.deleteTema("1");
        assertNull(service.addTema(tema));
    }

    @org.junit.jupiter.api.Test
    void Testcase3() {
        Nota nota = new Nota("1","1","",10, LocalDate.now());
        if (service.findTema("1") != null)
            service.deleteTema("1");
        assertThrows(ValidationException.class, () -> {
            service.addNota(nota, "Le dam tot codu");
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase4() {
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        if (service.findStudent("1") != null)
            service.deleteStudent("1");
        assertNull(service.addStudent(student));

        Tema tema = new Tema("1", "abc", 6, 5);
        if (service.findTema("1") != null)
            service.deleteTema("1");
        assertNull(service.addTema(tema));

        Nota nota = new Nota("1","1","1",10, LocalDate.now());
        if (service.findTema("1") != null)
            service.deleteTema("1");
        assertThrows(ValidationException.class, () -> {
            service.addNota(nota, "Le dam tot codu");
        });
    }
}
