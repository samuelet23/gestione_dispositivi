package progetto_settimanale.gestione_dispositivi.model.Dispositivo;

import jakarta.persistence.*;
import lombok.Data;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Type.StatoDispositivo;

@Entity
@Data
public class Dispositivo {

    @Enumerated(EnumType.STRING)
    private StatoDispositivo statoDispositivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
}
