package it.huntink.webapp.controller.rest;

import it.huntink.webapp.exception.FileStorageException;
import it.huntink.webapp.model.dto.TatuaggioDto;
import it.huntink.webapp.model.dto.validator.TatuaggioValidatorDto;
import it.huntink.webapp.service.TatuaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tatuaggi")
public class TatuaggioRestController {

    @Autowired
    TatuaggioService tatuaggioService;

    @GetMapping
    public ResponseEntity<List<TatuaggioDto>> getAllTatuaggi(){
        List<TatuaggioDto> tatuaggi = tatuaggioService.selTatuaggi();
        return ResponseEntity.ok(tatuaggi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TatuaggioDto> getTatuaggio(@PathVariable Long id){
        TatuaggioDto t = tatuaggioService.selById(id);
        return ResponseEntity.ok(t);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<TatuaggioDto> createTatuaggio(@Valid @RequestPart("tatuaggio") TatuaggioValidatorDto tatuaggioValidatorDto, @RequestPart("immagine") MultipartFile file){
        if (file == null || file.isEmpty()){
            throw new FileStorageException("L'immagine del tatuaggio è OBBLIGATORIA");
        }
        TatuaggioDto t = tatuaggioService.insTatuaggio(tatuaggioValidatorDto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TatuaggioDto> updateTatuaggio(@PathVariable Long id, @Valid @RequestBody TatuaggioDto tatuaggioDto){
        TatuaggioDto t = tatuaggioService.updTatuaggio(id,tatuaggioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTatuaggio(@PathVariable Long id){
        tatuaggioService.delTatuaggio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TatuaggioDto>> searchTatuaggi(@RequestParam String parametro, @RequestParam String valore){
        List<TatuaggioDto> tatuaggi = tatuaggioService.searchTatuaggi(parametro, valore);
        return ResponseEntity.ok(tatuaggi);
    }



}
