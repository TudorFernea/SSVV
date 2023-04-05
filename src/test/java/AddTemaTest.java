import org.example.src.domain.Student;
import org.example.src.domain.Tema;
import org.example.src.repository.TemaXMLRepo;
import org.example.src.service.Service;
import org.example.src.validation.TemaValidator;
import org.example.src.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddTemaTest {

    private static Service service;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        service = new Service(null, null, new TemaXMLRepo("src/test/java/fisiere/Teme.xml"), new TemaValidator(), null, null);
    }

    @org.junit.jupiter.api.Test
    void testcase1() {
        Tema tema = new Tema("1", "abc", 6, 7);
        if (service.findTema("1") != null)
            service.deleteTema("1");
        assertNull(service.addTema(tema));
    }

    @org.junit.jupiter.api.Test
    void testcase2() {
        Tema tema = new Tema("", "abc", 6, 7);
        assertThrows(ValidationException.class, () -> {
            service.addTema(tema);
        });
    }
}

