package it.huntink.webapp.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexMvcController {

    private String saluti = "Saluti, sono la tua prima applicazione web creata in Spring Boot 3";

    @GetMapping(value="/")
    public String getWelcome(Model model)
    {
        model.addAttribute("intestazione", "HUNTINK");
        model.addAttribute("saluti", saluti);

        return "index";
    }


}
