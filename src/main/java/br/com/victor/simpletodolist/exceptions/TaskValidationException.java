package br.com.victor.simpletodolist.exceptions;

public class TaskValidationException extends RuntimeException {

    public TaskValidationException(String ex) {
        super(ex);
    }

}
