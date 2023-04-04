package org.example.src.app;


import org.example.src.repository.NotaFileRepository;
import org.example.src.repository.StudentFileRepository;
import org.example.src.repository.StudentXMLRepo;
import org.example.src.repository.TemaXMLRepo;
import org.example.src.repository.NotaXMLRepo;
import org.example.src.repository.TemaFileRepository;
import org.example.src.service.Service;
import org.example.src.validation.NotaValidator;
import org.example.src.validation.StudentValidator;
import org.example.src.validation.TemaValidator;
import org.example.src.view.UI;

public class MainApplication {

    public static void main(String[] args) {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "src/main/java/org/example/fisiere/Studenti.xml";
        String filenameTema = "src/main/java/org/example/fisiere/Teme.xml";
        String filenameNota = "src/main/java/org/example/fisiere/Note.xml";

        //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
        UI ui = new UI(service);
        ui.run();
    }

}
