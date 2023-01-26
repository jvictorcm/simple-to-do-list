package br.com.victor.simpletodolist.validators;

import br.com.victor.simpletodolist.exceptions.TaskValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskValidatorTest {

    @Test
    void shouldAllowCorrectInput() {
        String toBeTested = "valid input";
        assertDoesNotThrow(() -> TaskValidator.validateTaskName(toBeTested));
    }

    @Test
    void shouldThrowExceptionForSingleWord() {
        String toBeTested = "invalidInput        ";
        Exception ex = assertThrows(TaskValidationException.class, () -> {
            TaskValidator.validateTaskName(toBeTested);
        });
        assertEquals("Task should have at least 2 words but no more then 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionForTooLongInput() {
        String toBeTested = "invalid InputInputInputInputInputInputInputInputInputInputInputInputInputInputInputInputInputInputInput";
        Exception ex = assertThrows(TaskValidationException.class, () -> {
            TaskValidator.validateTaskName(toBeTested);
        });
        assertEquals("Task should have at least 2 words but no more then 100 characters", ex.getMessage());
    }

}