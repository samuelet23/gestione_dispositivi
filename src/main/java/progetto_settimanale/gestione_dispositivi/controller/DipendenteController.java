package progetto_settimanale.gestione_dispositivi.controller;

import com.cloudinary.Cloudinary;
import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import progetto_settimanale.gestione_dispositivi.Exception.*;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.Dipendente;
import progetto_settimanale.gestione_dispositivi.model.Dipendente.DipendenteRequest;
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

    @PatchMapping("/idDipendente/idDispositivo")
    public Dipendente assegnaDispositivoAlDipendente(@RequestParam int idDipendente, @RequestParam int idDispositivo, BindingResult bindingResult) throws NotFoundElementException {
       checkNotFoundElementException(bindingResult);
        return dipendenteService.assegnaDispositivo(idDipendente, idDispositivo);
    }
    @DeleteMapping("/idDipendente/idDispositivo")
    public Dipendente deleteDispositivoDalDipendente(@RequestParam int idDipendente, @RequestParam int idDispositivo, BindingResult bindingResult) throws NotFoundElementException {
       checkNotFoundElementException(bindingResult);
        return dipendenteService.eliminaDispositivoDaDipendente(idDipendente, idDispositivo);
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

    @PatchMapping("/{id}/upload")
    public Dipendente uploadImg(@PathVariable int id, @RequestParam("upload") MultipartFile file, BindingResult bindingResult) throws IOException, NotFoundElementException {
        checkNotFoundElementException(bindingResult);
        checkIOException(bindingResult);
        String url = cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url").toString();
        return dipendenteService.upload(id,url );

    }

    @DeleteMapping("/{id}")
    public void deleteDipendente(@PathVariable int id,BindingResult bindingResult) throws NotFoundElementException {
        checkNotFoundElementException(bindingResult);
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
