package br.com.victor.simpletodolist.validators;

import br.com.victor.simpletodolist.exceptions.TaskValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskValidator {
    public static void validateTaskName(String taskName) {
        Pattern pattern = Pattern.compile("^(?=.{3,99})(?=\\S+\\s+\\S+)[a-zA-Z\\d\\s]*$");
        Matcher matcher = pattern.matcher(taskName);
        if (!matcher.matches()) {
            throw new TaskValidationException("Task should have at least 2 words but no more then 100 characters");
        }
    }
}
