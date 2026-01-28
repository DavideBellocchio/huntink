package it.huntink.webapp.model.entity;

import it.huntink.webapp.model.enums.ColoreTatuaggio;
import it.huntink.webapp.model.enums.StileTatuaggio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tatuaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tatuaggio_generator")
    @SequenceGenerator(name = "tatuaggio_generator", sequenceName = "tatuaggio_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double altezza;

    @Column(nullable = false)
    private double larghezza;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StileTatuaggio stile;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ColoreTatuaggio colore;

    @Column(nullable = false)
    private LocalDate data;
}
