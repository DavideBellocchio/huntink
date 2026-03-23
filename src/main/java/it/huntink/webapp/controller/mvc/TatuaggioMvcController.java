package it.huntink.webapp.controller.mvc;

import it.huntink.webapp.model.dto.TatuaggioDto;
import it.huntink.webapp.model.dto.validator.TatuaggioValidatorDto;
import it.huntink.webapp.model.enums.ColoreTatuaggio;
import it.huntink.webapp.model.enums.StileTatuaggio;
import it.huntink.webapp.service.TatuaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/tatuaggi")
public class TatuaggioMvcController {


    @Autowired
    TatuaggioService tatuaggioService;

    @GetMapping()
    public String getTatuaggi(Model model)
    {
        List<TatuaggioDto> tatuaggi = tatuaggioService.selTatuaggi();

        model.addAttribute("tatuaggi", tatuaggi);

        return "tatuaggi";
    }

    @GetMapping("/search")
    public String cercaTatuaggi(@RequestParam(name="parametro", required = true) String parametro,
                                @RequestParam(name="valore") String valore,
                                Model model)
    {

        List<TatuaggioDto> tatuaggi = tatuaggioService.searchTatuaggi(parametro, valore);

        model.addAttribute("tatuaggi", tatuaggi);
        model.addAttribute("parametro", parametro);
        model.addAttribute("valore", valore);

        return "tatuaggi";
    }

    @GetMapping("/new")
    public String newTatuaggio(Model model)
    {

        model.addAttribute("tatuaggio", new TatuaggioDto());
        model.addAttribute("stili", StileTatuaggio.values());
        model.addAttribute("colori", ColoreTatuaggio.values());
        return "instatuaggio";
    }

    @PostMapping("/savedata")
    public String insTatuaggio(@Valid @ModelAttribute("tatuaggio") TatuaggioValidatorDto t,
                               BindingResult result,
                               @RequestParam("file") MultipartFile file,
                               Model model)
    {
        if (result.hasErrors()) {
            model.addAttribute("tatuaggio", t);
            model.addAttribute("stili", StileTatuaggio.values());
            model.addAttribute("colori", ColoreTatuaggio.values());
            return "instatuaggio";
        }
        TatuaggioDto tattoo = tatuaggioService.insTatuaggio(t, file);
        List<TatuaggioDto> tatuaggi = tatuaggioService.selTatuaggi();
        model.addAttribute("tatuaggi", tatuaggi);
        return "tatuaggi";
    }


}
