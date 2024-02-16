package progetto_settimanale.gestione_dispositivi.service;

import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import progetto_settimanale.gestione_dispositivi.Exception.NotFoundElementException;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.DipendenteRequest;
import progetto_settimanale.gestione_dispositivi.repository.DipendenteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

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

    public void deleteDipendente(int id) throws NotFoundElementException {
        Dipendente d = getById(id);
        dipendenteRepository.delete(d);
    }

    public Dipendente upload(int id, String url) throws NotFoundElementException {
        Dipendente dipendente = getById(id);
        dipendente.setImg(url);
        return dipendenteRepository.save(dipendente);
    }
}
