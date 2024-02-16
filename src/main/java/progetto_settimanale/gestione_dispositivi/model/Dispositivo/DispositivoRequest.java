package progetto_settimanale.gestione_dispositivi.model.Dispositivo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Type.StatoDispositivo;

@Data
public class DispositivoRequest {

    @NotNull(message = "Il campo desiderato non può essere null")
    @NotBlank(message = "Il campo desiderato non può essere vuoto")
    //gestire validazione in caso venga inviato un enum inesistente
    private StatoDispositivo statoDispositivo;

    private Integer idDipendente;
}

