package cz.muni.fi.pa165.methanolmanager.service.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(int id) {
        super("Failed to find entity with id: " + id);
    }
}
