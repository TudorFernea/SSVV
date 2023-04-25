import org.example.src.domain.Student;
import org.example.src.domain.Tema;
import org.example.src.repository.TemaXMLRepo;
import org.example.src.service.Service;
import org.example.src.validation.TemaValidator;
import org.example.src.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

class AddTemaTest {

    private static Service service;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        service = new Service(null, null, new TemaXMLRepo("src/Test/java/fisiere/Teme.xml"), new TemaValidator(), null, null);
    }

    @org.junit.jupiter.api.Test
    void Testcase1() {
        Tema tema = new Tema("1", "abc", 6, 7);
        if (service.findTema("1") != null)
            service.deleteTema("1");
        assertNull(service.addTema(tema));
    }

    @org.junit.jupiter.api.Test
    void Testcase2() {
        Tema tema = new Tema("", "abc", 6, 7);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase3() {
        Tema tema = new Tema("1", "", 6, 7);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase4() {
        Tema tema = new Tema("1", "abc", 0, 7);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase5() {
        Tema tema = new Tema("1", "abc", 6, 16);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }

    @org.junit.jupiter.api.Test
    void Testcase6() {
        Tema tema = new Tema("1", "abc", 6, 7);
        if (service.findTema("1") != null)
            service.deleteTema("1");
        service.addTema(tema);
        assertNotNull(service.addTema(tema));
    }
}

