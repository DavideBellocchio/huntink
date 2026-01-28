package it.huntink.webapp.model.dto;

import it.huntink.webapp.model.enums.ColoreTatuaggio;
import it.huntink.webapp.model.enums.StileTatuaggio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TatuaggioDto {

    private Long id;
    private String nome;
    private double altezza;
    private double larghezza;
    private StileTatuaggio stile;
    private ColoreTatuaggio colore;
    private LocalDate data;
}
