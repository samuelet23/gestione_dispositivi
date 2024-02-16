package progetto_settimanale.gestione_dispositivi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import progetto_settimanale.gestione_dispositivi.Exception.*;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.DipendenteRequest;
import progetto_settimanale.gestione_dispositivi.service.DipendenteService;

import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;



    @GetMapping("")
    public List<Dipendente> getAll(BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dipendenteService.getAll();
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable int id, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dipendenteService.getById(id);
    }

    @PostMapping("")
    public Dipendente saveDipendente(@RequestBody DipendenteRequest dipendente,BindingResult bindingResult) throws Exception {
        checkException(bindingResult);
        return dipendenteService.saveDipendente(dipendente);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente (@PathVariable int id, @RequestBody DipendenteRequest dipendente, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dipendenteService.updateDipendente(id,dipendente);
    }
    @PatchMapping("/{id}")
    public Dipendente updateCognome(@PathVariable int id, @RequestBody String cognome,BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return  dipendenteService.updateCognome(id, cognome);
    }
    @PatchMapping("/{id}")
    public Dipendente updateNome(@PathVariable int id, @RequestBody String nome, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return  dipendenteService.updateNome(id, nome);
    }
    @PatchMapping("/{id}")
    public Dipendente updateUsername(@PathVariable int id, @RequestBody String username, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return  dipendenteService.updateUsername(id, username);
    }
    @PatchMapping("/{id}")
    public Dipendente updateEmail(@PathVariable int id, @RequestBody String email, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return  dipendenteService.updateEmail(id, email);
    }

    @DeleteMapping("/{id}")
    public void deleteDipendente(@PathVariable int id,BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        dipendenteService.deleteDipendente(id);
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



}
