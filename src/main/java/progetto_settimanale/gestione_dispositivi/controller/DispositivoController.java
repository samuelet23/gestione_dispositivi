package progetto_settimanale.gestione_dispositivi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import progetto_settimanale.gestione_dispositivi.Exception.*;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.DipendenteRequest;
import progetto_settimanale.gestione_dispositivi.model.Dispositivo.Dispositivo;
import progetto_settimanale.gestione_dispositivi.model.Dispositivo.DispositivoRequest;
import progetto_settimanale.gestione_dispositivi.service.DispositivoService;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivi")
public class DispositivoController {


    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("")
    public List<Dispositivo> getAll(BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dispositivoService.getAll();
    }

    @GetMapping("/inManutenzione")
    public List<Dispositivo> getAllInManutenzione(BindingResult bindingResult) throws NotFoundElementException {
       checkNotFoundElementException(bindingResult);
        return dispositivoService.getAllInManutenzione();
    }
    @GetMapping("/dismessi")
    public List<Dispositivo> getAllDismessi(BindingResult bindingResult) throws NotFoundElementException {
       checkNotFoundElementException(bindingResult);
        return dispositivoService.getAllDismessi();
    }

    @GetMapping("/{id}")
    public Dispositivo getDispositivoById(@PathVariable int id, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dispositivoService.getById(id);
    }

    @PostMapping("")
    public Dispositivo saveDispositivo(@RequestBody DispositivoRequest dispositivo, BindingResult bindingResult) throws Exception {
        checkException(bindingResult);
        return dispositivoService.saveDispositivo(dispositivo);
    }

    @PutMapping("/{id}")
    public Dispositivo updateDispositivo (@PathVariable int id, @RequestBody DispositivoRequest dispositivo, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dispositivoService.updateDispositivo(id,dispositivo);
    }

    @PatchMapping("/{id}")
    public Dispositivo updateStatoDispositivoInManutenzione(@PathVariable int id, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dispositivoService.setinManutenzione(id);
    }
    @PatchMapping("/{id}")
    public Dispositivo updateStatoDispositivoDismesso(@PathVariable int id, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dispositivoService.setDismesso(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDispositivo(@PathVariable int id,BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        dispositivoService.deleteDispositivo(id);
    }






    public static void checkNotFoundElementException(BindingResult bindingResult) throws NotFoundElementException {
        if (bindingResult.hasErrors()) {
            throw new NotFoundElementException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkException(BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new NotFoundElementException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkElementAlreadyAssignedException(BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new ElementAlreadyAssignedException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkDispositivoNonAssegnatoException(BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new DispositivoNonAssegnatoException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkDispositivoInManutenzioneException(BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new DispositivoInManutenzioneException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
    public static void checkDispositivoDismessoException(BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            throw new DispositvoDismessoException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
    }
}
