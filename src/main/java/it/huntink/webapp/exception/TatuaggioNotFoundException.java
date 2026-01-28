package it.huntink.webapp.exception;

public class TatuaggioNotFoundException extends RuntimeException{

    public TatuaggioNotFoundException() {
        super("Tatuaggio non trovato");
    }
}
