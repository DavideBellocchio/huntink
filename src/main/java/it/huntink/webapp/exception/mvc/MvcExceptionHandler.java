package it.huntink.webapp.exception.mvc;

import it.huntink.webapp.exception.TatuaggioNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice(basePackages = "it.huntink.webapp.controller.mvc")
public class MvcExceptionHandler {

    @ExceptionHandler(TatuaggioNotFoundException.class)
    public String handleTatuaggioNotFound(TatuaggioNotFoundException ex, Model model){

        model.addAttribute("message", ex.getMessage());

        return "";
    }

}
