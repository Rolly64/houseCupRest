package org.generation.italy.houseCupRest.model.exceptions;

public class EntityNotFoundException extends Exception {
    private String entityName;
    public EntityNotFoundException(String message, String entityName) {
        super(message);
        this.entityName = entityName;
    }
    public String getEntityName() {
      return entityName;
    }
    public String getFullMessage() {
      return String.format("%s %s", getMessage(), entityName);
    }
}
