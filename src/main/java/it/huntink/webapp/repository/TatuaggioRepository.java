package it.huntink.webapp.repository;

import it.huntink.webapp.model.entity.Tatuaggio;
import it.huntink.webapp.model.enums.ColoreTatuaggio;
import it.huntink.webapp.model.enums.StileTatuaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TatuaggioRepository extends JpaRepository<Tatuaggio, Long> {

    @Query(value = "SELECT * FROM tatuaggio t WHERE UPPER(t.nome) like UPPER(CONCAT('%', :nome, '%'))", nativeQuery = true)
    List<Tatuaggio> findByNomeLike(@Param("nome") String nome);

    List<Tatuaggio> findByColore(ColoreTatuaggio colore);

    List<Tatuaggio> findByStile(StileTatuaggio stile);
}
