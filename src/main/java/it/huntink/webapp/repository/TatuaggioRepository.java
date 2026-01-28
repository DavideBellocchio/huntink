package it.huntink.webapp.repository;

import it.huntink.webapp.model.entity.Tatuaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TatuaggioRepository extends JpaRepository<Tatuaggio, Long> {

    List<Tatuaggio> findByNomeLike(String nome);
}
