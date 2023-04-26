package org.example.src.validation;

import org.example.src.domain.Tema;
public class TemaValidator implements Validator<Tema> {

    /**
     * Valideaza o tema
     * @param entity - tema pe care o valideaza
     * @throws ValidationException daca tema nu e valida
     */
    @Override
    public void validate(Tema entity) throws ValidationException {
        if(entity.getID().equals("") || entity.getID() == null) {
            throw new ValidationException("Numar tema invalid!");
        }
        if(entity.getDescriere().equals("") || entity.getDescriere() == null){
            throw new ValidationException("Descriere invalida!");
        }
        if(entity.getDeadline() < 1 || entity.getDeadline() > 14) {
            throw new ValidationException("Deadlineul trebuie sa fie intre 1-14.");
        }
        if(entity.getPrimire() < 1 || entity.getPrimire() > 14) {
            throw new ValidationException("Saptamana primirii trebuie sa fie intre 1-14.");
        }
        if(entity.getPrimire() > entity.getDeadline()){
            throw new ValidationException("Nu se poate.");
        }
    }
}
