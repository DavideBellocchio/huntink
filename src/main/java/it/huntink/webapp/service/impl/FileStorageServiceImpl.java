package it.huntink.webapp.service.impl;

import it.huntink.webapp.exception.FileStorageException;
import it.huntink.webapp.service.FileStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${huntink.upload-dir}")
    private String uploadBaseDir;


    @Override
    public String salvaFile(MultipartFile file, String sottoCartella) {

        try{
            File directory = new File(uploadBaseDir, sottoCartella);
            if(!directory.exists()){
                directory.mkdirs();
            }
            String estensione = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String nome = UUID.randomUUID() + estensione;
            File destinazione = new File(directory, nome);
            file.transferTo(destinazione);
            return sottoCartella + "/" + nome;
        } catch (IOException e) {
            throw new FileStorageException("Errore durante il salvataggio del file", e.getCause());
        }
    }

    @Override
    public void eliminaFile(String fileName) {
        File file = new File(uploadBaseDir, fileName);
        if(file.exists()){
            file.delete();
        }
    }
}
