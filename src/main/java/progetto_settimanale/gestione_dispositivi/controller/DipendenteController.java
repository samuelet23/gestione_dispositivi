package progetto_settimanale.gestione_dispositivi.controller;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import progetto_settimanale.gestione_dispositivi.Exception.NotFoundElementException;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.DipendenteRequest;
import progetto_settimanale.gestione_dispositivi.model.Dispositivo.Dispositivo;
import progetto_settimanale.gestione_dispositivi.service.DipendenteService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private Cloudinary cloudinary;


    @GetMapping("")
    public List<Dipendente> getAll() throws NotFoundElementException {

        return dipendenteService.getAll();
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable int id) throws NotFoundElementException {
        return dipendenteService.getById(id);
    }

    @PostMapping("")
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteRequest dipendente, BindingResult bindingResult) throws Exception {
        checkException(bindingResult);
        return dipendenteService.saveDipendente(dipendente);
    }

    @PatchMapping("/{idDipendente}/{idDispositivo}")
    public Dispositivo assegnaDispositivoAlDipendente(@PathVariable int idDipendente, @PathVariable int idDispositivo) throws NotFoundElementException {
        return dipendenteService.assegnaDispositivo(idDipendente, idDispositivo);
    }
    @DeleteMapping("/idDipendente/idDispositivo")
    public Dipendente deleteDispositivoDalDipendente(@RequestParam int idDipendente, @RequestParam int idDispositivo) throws NotFoundElementException {
        return dipendenteService.eliminaDispositivoDaDipendente(idDipendente, idDispositivo);
    }

    @PutMapping("/{id}/dipendente")
    public Dipendente updateDipendente (@PathVariable int id, @RequestBody @Validated DipendenteRequest dipendente, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return dipendenteService.updateDipendente(id,dipendente);
    }
    //inserire su postman, il body modalità row: Text e scrivere solo la string da aggiornare Es: Rossi
    @PatchMapping("/{id}/cognome")
    public Dipendente updateCognome(@PathVariable int id, @RequestBody @Validated String cognome,BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return  dipendenteService.updateCognome(id, cognome);
    }

    //inserire su postman, il body modalità row: Text e scrivere solo la string da aggiornare Es: Mario
    @PatchMapping("/{id}/nome")
    public Dipendente updateNome(@PathVariable int id, @RequestBody @Validated String nome, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return  dipendenteService.updateNome(id, nome);
    }
    //inserire su postman, il body modalità row: Text e scrivere solo la string da aggiornare Es: Mario23

    @PatchMapping("/{id}/username")
    public Dipendente updateUsername(@PathVariable int id, @RequestBody @Validated String username, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return  dipendenteService.updateUsername(id, username);
    }
    //inserire su postman, il body modalità row: Text e scrivere solo la string da aggiornare Es: Mario@gmail.com

    @PatchMapping("/{id}/email")
    public Dipendente updateEmail(@PathVariable int id, @RequestBody @Validated String email, BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        return  dipendenteService.updateEmail(id, email);
    }

    @PatchMapping("/{id}/upload")
    public Dipendente uploadImg(@PathVariable int id, @RequestParam("upload") MultipartFile file) throws IOException, NotFoundElementException {
        String url = cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url").toString();
        return dipendenteService.upload(id,url );

    }

    @DeleteMapping("/{id}")
    public void deleteDipendente(@PathVariable int id) throws NotFoundElementException {
        dipendenteService.deleteDipendente(id);
    }




    public static void checkIOException(BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors() ) {
            throw new IOException(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        }
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
