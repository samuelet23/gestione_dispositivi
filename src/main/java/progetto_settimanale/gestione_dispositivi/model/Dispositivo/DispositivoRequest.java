package progetto_settimanale.gestione_dispositivi.model.Dispositivo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Type.StatoDispositivo;
import progetto_settimanale.gestione_dispositivi.model.Type.TipoDispositivo;

@Data
public class DispositivoRequest {

    @NotNull(message = "Il campo desiderato non può essere null")
    private TipoDispositivo tipoDispositivo;

    @NotNull(message = "Il campo desiderato non può essere null")
    private StatoDispositivo statoDispositivo;

    private Integer idDipendente;
}

