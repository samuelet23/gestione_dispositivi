package progetto_settimanale.gestione_dispositivi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import progetto_settimanale.gestione_dispositivi.Exception.DispositivoInManutenzioneException;
import progetto_settimanale.gestione_dispositivi.Exception.DispositvoDismessoException;
import progetto_settimanale.gestione_dispositivi.Exception.NotFoundElementException;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.DipendenteRequest;
import progetto_settimanale.gestione_dispositivi.model.Dispositivo.Dispositivo;
import progetto_settimanale.gestione_dispositivi.model.Dispositivo.DispositivoRequest;
import progetto_settimanale.gestione_dispositivi.model.Type.StatoDispositivo;
import progetto_settimanale.gestione_dispositivi.repository.DispositivoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public List<Dispositivo> getAll() throws NotFoundElementException {
        List<Dispositivo> dispositivi =dispositivoRepository.findAll();
        if (dispositivi.isEmpty()) {
            throw new NotFoundElementException("La lista di dispositivi è vuota");
        }
        return dispositivi;
    }

    public List<Dispositivo> getAllInManutenzione() throws NotFoundElementException {
        List<Dispositivo> inManutenzione =dispositivoRepository.findAllByStatoDispositivo(StatoDispositivo.IN_MANUTENZIONE);
        if (inManutenzione.isEmpty()) {
            throw new NotFoundElementException("Nessun dispositivo è in Manutenzione");
        }
        return inManutenzione;
    }
public List<Dispositivo> getAllDismessi() throws NotFoundElementException {
        List<Dispositivo> dismessi =dispositivoRepository.findAllByStatoDispositivo(StatoDispositivo.DISMESSO);
        if (dismessi.isEmpty()) {
            throw new NotFoundElementException("Nessun dispositivo è dismesso");
        }
        return dismessi;
    }

    public Dispositivo getById(int id) throws NotFoundElementException {
        Optional<Dispositivo> optionalDispositivo = dispositivoRepository.findById(id);
        if (optionalDispositivo.isEmpty()){
            throw new NotFoundElementException("Dispositivo con id= "+id+" non trovato");
        }
        return optionalDispositivo.get();
    }

    public Dispositivo saveDispositivo(DispositivoRequest dispositivo) {
        Dispositivo d = new Dispositivo();
        d.setTipoDispositivo(dispositivo.getTipoDispositivo());
        d.setStatoDispositivo(dispositivo.getStatoDispositivo());
        return dispositivoRepository.save(d);
    }
    public Dispositivo updateDispositivo(int id, DispositivoRequest dispositivo) throws NotFoundElementException {
        Dispositivo d = new Dispositivo();
        d.setStatoDispositivo(dispositivo.getStatoDispositivo());
        return d;
    }

    public void deleteDispositivo(int id) throws NotFoundElementException {
        Dispositivo d = getById(id);
        dispositivoRepository.delete(d);
    }

    public Dispositivo setinManutenzione(int id) throws NotFoundElementException {
        Dispositivo dispositivo = getById(id);
        if (dispositivo.getStatoDispositivo() == StatoDispositivo.IN_MANUTENZIONE) {
            throw new DispositivoInManutenzioneException("Il dispositivo è già in manutenzione");
        }
        dispositivo.setStatoDispositivo(StatoDispositivo.IN_MANUTENZIONE);

        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo setDismesso(int id)throws NotFoundElementException{
        Dispositivo dispositivo = getById(id);
        if (dispositivo.getStatoDispositivo() == StatoDispositivo.DISMESSO) {
            throw new DispositvoDismessoException("Il dispositivo è già dismesso");
        }

        dispositivo.setStatoDispositivo(StatoDispositivo.DISMESSO);
        return dispositivoRepository.save(dispositivo);
    }
}
