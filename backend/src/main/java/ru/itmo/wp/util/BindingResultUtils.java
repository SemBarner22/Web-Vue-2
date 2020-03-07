package ru.itmo.wp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class BindingResultUtils {
    public static String getErrorMessage(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().get(0);
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                String result = "Field " + fieldError.getField() + ": " + fieldError.getDefaultMessage();
                System.out.println(result);
                return result;
            } else {
                return objectError.getDefaultMessage();
            }
        } else {
            return null;
        }
    }
}
