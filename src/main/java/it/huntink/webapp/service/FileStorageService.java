package it.huntink.webapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    public String salvaFile(MultipartFile file, String sottoCartella);
    public void eliminaFile(String fileName);

}
