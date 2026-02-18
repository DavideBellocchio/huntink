package it.huntink.webapp.service.impl;

import it.huntink.webapp.exception.FileStorageException;
import it.huntink.webapp.exception.TatuaggioNotFoundException;
import it.huntink.webapp.mapper.TatuaggioMapper;
import it.huntink.webapp.model.dto.validator.TatuaggioValidatorDto;
import it.huntink.webapp.model.dto.TatuaggioDto;
import it.huntink.webapp.model.entity.Tatuaggio;
import it.huntink.webapp.model.enums.ColoreTatuaggio;
import it.huntink.webapp.model.enums.StileTatuaggio;
import it.huntink.webapp.repository.TatuaggioRepository;
import it.huntink.webapp.service.FileStorageService;
import it.huntink.webapp.service.TatuaggioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class TatuaggioServiceImpl implements TatuaggioService {

    @Autowired
    TatuaggioRepository tatuaggioRepository;
    @Autowired
    TatuaggioMapper tatuaggioMapper;
    @Autowired
    FileStorageService fileStorageService;

    @Override
    @Transactional
    public TatuaggioDto insTatuaggio(TatuaggioValidatorDto tatuaggioValidatorDto, MultipartFile file) {

        String immagine = fileStorageService.salvaFile(file,"tatuaggi");
        Tatuaggio t = tatuaggioMapper.toEntity(tatuaggioValidatorDto);
        t.setImmagine(immagine);
        t.setData(LocalDate.now());
        tatuaggioRepository.save(t);
        return tatuaggioMapper.toResponseDto(t);
    }

    @Override
    public List<TatuaggioDto> selTatuaggi() {
        return tatuaggioRepository.findAll()
                .stream()
                .map(tatuaggioMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<TatuaggioDto> selByNome(String nome) {
        return tatuaggioRepository.findByNomeLike(nome)
                .stream()
                .map(tatuaggioMapper::toResponseDto)
                .toList();
    }

    @Override
    public TatuaggioDto selById(Long id) {
        Tatuaggio t = tatuaggioRepository.findById(id).orElseThrow(TatuaggioNotFoundException::new);
        return tatuaggioMapper.toResponseDto(t);
    }

    @Override
    public List<TatuaggioDto> searchTatuaggi(String parametro, String valore) {
        switch (parametro){
            case "all":
                return tatuaggioRepository.findAll()
                        .stream()
                        .map(tatuaggioMapper::toResponseDto)
                        .toList();
            case "nome":
                return tatuaggioRepository.findByNomeLike(valore)
                        .stream()
                        .map(tatuaggioMapper::toResponseDto)
                        .toList();
            case "colore":
                return tatuaggioRepository.findByColore(ColoreTatuaggio.valueOf(valore.toUpperCase()))
                        .stream()
                        .map(tatuaggioMapper::toResponseDto)
                        .toList();
            case "stile":
                return tatuaggioRepository.findByStile(StileTatuaggio.valueOf(valore.toUpperCase()))
                        .stream()
                        .map(tatuaggioMapper::toResponseDto)
                        .toList();
            default:
                return List.of();
        }
    }

    @Override
    @Transactional
    public TatuaggioDto updTatuaggio(Long id, TatuaggioDto tatuaggioDto) {
        Tatuaggio t = tatuaggioRepository.findById(id).orElseThrow(TatuaggioNotFoundException::new);
        tatuaggioMapper.updateEntityFromDto(tatuaggioDto, t);
        tatuaggioRepository.save(t);
        return tatuaggioMapper.toResponseDto(t);
    }

    @Override
    @Transactional
    public void delTatuaggio(Long id) {
        Tatuaggio t = tatuaggioRepository.findById(id).orElseThrow(TatuaggioNotFoundException::new);
        fileStorageService.eliminaFile(t.getImmagine());
        tatuaggioRepository.delete(t);
    }


}
