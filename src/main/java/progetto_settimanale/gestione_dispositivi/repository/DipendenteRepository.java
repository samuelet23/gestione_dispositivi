package progetto_settimanale.gestione_dispositivi.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer>, PagingAndSortingRepository<Dipendente,Integer> {
}
