package progetto_settimanale.gestione_dispositivi.model.Dipendente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DipendenteRequest {

    @NotNull(message = "Il campo username non può essere null")
    @NotEmpty(message = "Il campo username non può essere vuoto")
    @Size(min = 4, message = "Il campo username deve essere di almeno 4 caratteri")
    private String username;

    @NotEmpty( message = "Il campo nome non può essere vuoto")
    @NotNull(message = "Il campo nome non può essere null")
    @Size(min = 3, message = "Il nome deve contenere almeno 3 caratteri")
    private String nome;

    @NotEmpty( message = "Il campo cognome non può essere vuoto")
    @NotNull(message = "Il campo cognome non può essere null")
    @Size(min = 3, message = "Il cognome deve contenere almeno 4 caratteri")
    private String cognome;

    private String img;

    @NotNull
    @Email(
            message = "inserisci un email esistente",
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
    )
    private String email;

//    private List<Integer> idDispositivi;
}
