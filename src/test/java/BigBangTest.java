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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BigBangTest {
    private static Service service;

    @Mock
    private StudentXMLRepo studentXMLRepo;

    @Mock
    private TemaXMLRepo temaXMLRepo;

    @Mock
    private NotaXMLRepo notaXMLRepo;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        TemaXMLRepo r2 = new TemaXMLRepo("src/Test/java/fisiere/Teme.xml");
        service = new Service(studentXMLRepo, new StudentValidator(),  temaXMLRepo, new TemaValidator(), notaXMLRepo, new NotaValidator(studentXMLRepo,temaXMLRepo));
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

    @org.junit.jupiter.api.Test
    void Testcase5(){
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        when(studentXMLRepo.save(student)).thenReturn(null);
        assertNull(service.addStudent(student));
    }

    @org.junit.jupiter.api.Test
    void Testcase6(){
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        when(studentXMLRepo.save(student)).thenReturn(null);
        assertNull(service.addStudent(student));

        Tema tema = new Tema("1", "abc", 6, 5);
        when(temaXMLRepo.save(tema)).thenReturn(null);
        assertNull(service.addTema(tema));
    }

    @org.junit.jupiter.api.Test
    void Testcase7() throws IOException {
        Student student = new Student("1", "Fernea", 933, "ledamtotcodu@gmail.com");
        when(studentXMLRepo.save(student)).thenReturn(null);
        assertNull(service.addStudent(student));

        Tema tema = new Tema("1", "abc", 6, 5);
        when(temaXMLRepo.save(tema)).thenReturn(null);
        assertNull(service.addTema(tema));

        Nota nota = new Nota("1","1","1",10, LocalDate.of(2018,11,3));
        when(notaXMLRepo.save(nota)).thenReturn(null);
        when(studentXMLRepo.findOne(any())).thenReturn(student);
        when(temaXMLRepo.findOne(any())).thenReturn(tema);
        assertEquals(service.addNota(nota,"le dam tot codu"),10);
    }
}
