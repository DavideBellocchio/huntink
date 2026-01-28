package it.huntink.webapp.service.impl;

import it.huntink.webapp.exception.TatuaggioNotFoundException;
import it.huntink.webapp.mapper.TatuaggioMapper;
import it.huntink.webapp.model.dto.validator.TatuaggioValidatorDto;
import it.huntink.webapp.model.dto.TatuaggioDto;
import it.huntink.webapp.model.entity.Tatuaggio;
import it.huntink.webapp.repository.TatuaggioRepository;
import it.huntink.webapp.service.TatuaggioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TatuaggioServiceImpl implements TatuaggioService {

    @Autowired
    TatuaggioRepository tatuaggioRepository;
    @Autowired
    TatuaggioMapper tatuaggioMapper;

    @Override
    @Transactional
    public TatuaggioDto insTatuaggio(TatuaggioValidatorDto tatuaggioValidatorDto) {
        Tatuaggio t = tatuaggioMapper.toEntity(tatuaggioValidatorDto);
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
        tatuaggioRepository.delete(t);
    }


}
