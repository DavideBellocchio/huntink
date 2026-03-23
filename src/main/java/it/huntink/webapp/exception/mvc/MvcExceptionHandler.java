package it.huntink.webapp.exception.mvc;

import it.huntink.webapp.exception.FileStorageException;
import it.huntink.webapp.exception.TatuaggioNotFoundException;
import it.huntink.webapp.model.dto.TatuaggioDto;
import it.huntink.webapp.model.dto.validator.TatuaggioValidatorDto;
import it.huntink.webapp.model.enums.ColoreTatuaggio;
import it.huntink.webapp.model.enums.StileTatuaggio;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;


@ControllerAdvice(basePackages = "it.huntink.webapp.controller.mvc")
public class MvcExceptionHandler {

    @ExceptionHandler(TatuaggioNotFoundException.class)
    public String handleTatuaggioNotFound(TatuaggioNotFoundException ex, Model model){

        model.addAttribute("message", ex.getMessage());

        return "";
    }

    @ExceptionHandler(FileStorageException.class)
    public String handleFileStorage(FileStorageException ex, Model model){

        model.addAttribute("error", ex.getMessage());
        model.addAttribute("tatuaggio", new TatuaggioValidatorDto());
        model.addAttribute("stili", StileTatuaggio.values());
        model.addAttribute("colori", ColoreTatuaggio.values());
        return "instatuaggio";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex, Model model){

        model.addAttribute("message", "Il parametro non può essere vuoto");
        model.addAttribute("tatuaggi", List.of());

        return "tatuaggi";
    }

}
