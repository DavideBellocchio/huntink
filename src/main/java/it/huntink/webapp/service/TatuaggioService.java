package it.huntink.webapp.service;

import it.huntink.webapp.model.dto.validator.TatuaggioValidatorDto;
import it.huntink.webapp.model.dto.TatuaggioDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TatuaggioService {

    public TatuaggioDto insTatuaggio(TatuaggioValidatorDto tatuaggioValidatorDto, MultipartFile file);
    public List<TatuaggioDto>  selTatuaggi();
    public List<TatuaggioDto>  selByNome(String nome);
    public List<TatuaggioDto> searchTatuaggi(String parametro, String valore);
    public TatuaggioDto selById(Long id);
    public TatuaggioDto updTatuaggio(Long id, TatuaggioDto tatuaggioDto);
    public void delTatuaggio(Long id);

}
