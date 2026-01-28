package it.huntink.webapp.model.dto.validator;

import it.huntink.webapp.model.enums.ColoreTatuaggio;
import it.huntink.webapp.model.enums.StileTatuaggio;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TatuaggioValidatorDto {


    @Size(min = 5, max = 30, message = "{Size.Tatuaggio.nome.Validation}")
    @NotBlank(message = "{NotBlank.Tatuaggio.nome.Validation}")
    private String nome;

    @Max(value = 999, message = "{Max.Tatuaggio.altezza.Validation}")
    @DecimalMin(value = "0.01", message = "{Min.Tatuaggio.altezza.Validation}")
    @NotNull(message = "{NotNull.Tatuaggio.altezza.Validation}")
    private double altezza;

    @Max(value = 999, message = "{Max.Tatuaggio.larghezza.Validation}")
    @DecimalMin(value = "0.01", message = "{Min.Tatuaggio.larghezza.Validation}")
    @NotNull(message = "{NotNull.Tatuaggio.larghezza.Validation}")
    private double larghezza;

    @NotNull(message = "{NotNull.Tatuaggio.stile.Validation}")
    private StileTatuaggio stile;

    @NotNull(message = "{NotNull.Tatuaggio.colore.Validation}")
    private ColoreTatuaggio colore;

    @NotNull(message = "{NotNull.Tatuaggio.data.Validation}")
    private LocalDate data;


}
