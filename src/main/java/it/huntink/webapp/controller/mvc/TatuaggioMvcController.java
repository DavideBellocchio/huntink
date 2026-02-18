package it.huntink.webapp.controller.mvc;

import it.huntink.webapp.model.dto.TatuaggioDto;
import it.huntink.webapp.service.TatuaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
