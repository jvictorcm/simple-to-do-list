package br.com.victor.simpletodolist.config;

import br.com.victor.simpletodolist.exceptions.TaskPutMappingCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(TaskExceptionHandler.class);

    @ExceptionHandler({TaskPutMappingCreation.class})
    public ResponseEntity<Object> handleAll(final TaskPutMappingCreation ex, final WebRequest request) {
        return new ResponseEntity<>(ex.getCreatedTask(), HttpStatus.CREATED);
    }

}
