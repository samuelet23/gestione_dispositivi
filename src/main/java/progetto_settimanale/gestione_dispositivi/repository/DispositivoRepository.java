package progetto_settimanale.gestione_dispositivi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import progetto_settimanale.gestione_dispositivi.model.Dispositivo.Dispositivo;
import progetto_settimanale.gestione_dispositivi.model.Type.StatoDispositivo;

import java.util.List;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer>, PagingAndSortingRepository<Dispositivo, Integer> {

    @Query("SELECT d FROM Dispositivo d WHERE d.statoDispositivo = :statoDispositivo")
    List<Dispositivo> findAllByStatoDispositivo(StatoDispositivo statoDispositivo);

}
