package progetto_settimanale.gestione_dispositivi.model.Dipendente;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class DipendenteRequest {

    @NotNull(message = "Il campo username non può essere null")
    @NotBlank(message = "Il campo username non può essere vuoto")
    @Size(min = 4, message = "Il campo username deve essere di almeno 4 caratteri")
    private String username;

    @NotBlank( message = "Il campo nome non può essere vuoto")
    @NotNull(message = "Il campo nome non può essere null")
    @Size(min = 3, message = "Il nome deve contenere almeno 3 caratteri")
    private String nome;

    @NotBlank( message = "Il campo cognome non può essere vuoto")
    @NotNull(message = "Il campo cognome non può essere null")
    @Size(min = 4, message = "Il cognome deve contenere almeno 4 caratteri")
    private String cognome;

    @NotNull
    @Email(
            message = "inserisci un email esistente",
            regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
    )
    private String email;

//    private List<Integer> idDispositivi;
}
