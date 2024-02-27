package progetto_settimanale.gestione_dispositivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Integer>{
}
