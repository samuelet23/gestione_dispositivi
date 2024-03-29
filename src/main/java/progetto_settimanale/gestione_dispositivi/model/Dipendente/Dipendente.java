package progetto_settimanale.gestione_dispositivi.model.Dipendente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import progetto_settimanale.gestione_dispositivi.model.Dispositivo.Dispositivo;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dipendente_id")
    @SequenceGenerator(name = "dipendente_id", initialValue = 1, allocationSize = 1)
    private int id;

    @Column(unique = true)
    private String username;

    private String nome;
    private String cognome;

    private String img;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dipendente")
    private Set<Dispositivo> dispositivi = new HashSet<>();
}

