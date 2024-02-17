package progetto_settimanale.gestione_dispositivi.model.Dispositivo;

import jakarta.persistence.*;
import lombok.Data;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Type.StatoDispositivo;
import progetto_settimanale.gestione_dispositivi.model.Type.TipoDispositivo;

@Entity
@Data
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispositivo_id")
    @SequenceGenerator(name = "dispositivo_id", initialValue = 1, allocationSize = 1)
    private int id;

    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipoDispositivo;

    @Enumerated(EnumType.STRING)
    private StatoDispositivo statoDispositivo;

    @ManyToOne()
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
}
