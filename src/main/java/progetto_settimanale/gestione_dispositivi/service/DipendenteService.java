package progetto_settimanale.gestione_dispositivi.service;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import progetto_settimanale.gestione_dispositivi.Exception.DispositivoNonAssegnatoException;
import progetto_settimanale.gestione_dispositivi.Exception.ElementAlreadyAssignedException;
import progetto_settimanale.gestione_dispositivi.Exception.NotFoundElementException;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.DipendenteRequest;
import progetto_settimanale.gestione_dispositivi.model.Dispositivo.Dispositivo;
import progetto_settimanale.gestione_dispositivi.model.Type.StatoDispositivo;
import progetto_settimanale.gestione_dispositivi.repository.DipendenteRepository;
import progetto_settimanale.gestione_dispositivi.repository.DispositivoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private DispositivoService dispositivoService;

    public List<Dipendente> getAll() throws NotFoundElementException {
        List<Dipendente> dipendenti =dipendenteRepository.findAll();
        if (dipendenti.isEmpty()) {
            throw new NotFoundElementException("La lista è vuota");
        }
        return dipendenti;
    }

    public Dipendente getById(int id) throws NotFoundElementException {
        Optional<Dipendente> optionalDipendente = dipendenteRepository.findById(id);
        if (optionalDipendente.isEmpty()){
            throw new NotFoundElementException("Dipende con id= "+id+" non trovato");
        }
        return optionalDipendente.get();
    }

    public Dipendente saveDipendente(DipendenteRequest dipendente){
        Dipendente d = new Dipendente();
        d.setNome(dipendente.getNome());
        d.setCognome(dipendente.getCognome());
        d.setUsername(dipendente.getUsername());
        d.setEmail(dipendente.getEmail());

        return dipendenteRepository.save(d);
    }

    public Dipendente updateDipendente(int id, DipendenteRequest dipendente) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setNome(dipendente.getNome());
        d.setCognome(dipendente.getCognome());
        d.setUsername(dipendente.getUsername());
        d.setEmail(dipendente.getEmail());

        return d;
    }
    public Dipendente updateNome(int id, String nome) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setNome(nome);
        return d;
    }
    public Dipendente updateCognome(int id, String cognome) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setCognome(cognome);
        return d;
    }
    public Dipendente updateUsername(int id, String username) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setUsername(username);
        return d;
    }
    public Dipendente updateEmail(int id, String email) throws NotFoundElementException {
        Dipendente d = getById(id);
        d.setEmail(email);
        return d;
    }

    public void deleteDipendente(int id) throws NotFoundElementException {
        Dipendente d = getById(id);
        dipendenteRepository.delete(d);
    }

    public Dipendente upload(int id, String url) throws NotFoundElementException {
        Dipendente dipendente = getById(id);
        dipendente.setImg(url);
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente assegnaDispositivo(int idDipendente, int idDispositivo) throws NotFoundElementException {
        Dipendente dipendente = getById(idDipendente);
        Dispositivo dispositivo = dispositivoService.getById(idDispositivo);

        if (dispositivo.getStatoDispositivo() == StatoDispositivo.ASSEGNATO) {
            throw new ElementAlreadyAssignedException("Il dispositivo con id= "+idDispositivo+" è gia stato assegnato");
        }

        dispositivo.setStatoDispositivo(StatoDispositivo.ASSEGNATO);
        dispositivo.setDipendente(dipendente);
        dipendente.getDispositivi().add(dispositivo);

        return dipendenteRepository.save(dipendente);

    }

    public Dipendente eliminaDispositivoDaDipendente(int idDipendente, int idDispositivo) throws NotFoundElementException {
        Dipendente dipendente = getById(idDipendente);
        Dispositivo dispositivo = dispositivoService.getById(idDispositivo);

        if (!dispositivo.getDipendente().equals(dipendente)) {
            throw new DispositivoNonAssegnatoException("Dispositivo con id= "+idDispositivo+" non è assegnato a nessuna persona con id= "+idDipendente);
        }

        dispositivo.setStatoDispositivo(StatoDispositivo.DISPONIBILE);
        dispositivo.setDipendente(null);
        dipendente.getDispositivi().remove(dispositivo);

        return dipendente;

    }



}
