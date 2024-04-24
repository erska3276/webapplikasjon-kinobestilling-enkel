package oslomet.data1700_oblig3.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import oslomet.data1700_oblig3.pojo.Bestilling;
import oslomet.data1700_oblig3.pojo.Film;
import oslomet.data1700_oblig3.repository.KinoRepository;

import java.io.IOException;
import java.util.List;

/* Denne klassen representerer kontroller paa server i vaar MVC-arkitektur
 * og har ansvar for aa kommunisere med klienten (view = .html og .js).
 * Klienten faar da tak i server via denne klassens metoder som har
 * dekoratorer "Get-, Post-, Put-, og DeleteMapping" og deres "/endepunkt"
 * til server url. Retur av java-objekt tilbake til klient handteres av
 * Spring som konverterer om til et JSON-objekt.
 * */
@RestController
public class KinoController {

    @Autowired
    KinoRepository rep;

    //Servervalidering av input til Bestilling-objekt
    private boolean validerBestilling(Bestilling b) {
        String filmValidate = ".+";
        String antallValidate = "^\\d{1,3}$";
        String fnavnValidate = ".+";
        String enavnValidate = ".+";
        String tlfValidate = "^(\\+47)?\\d{8}$";
        //Mange varianter, denne er hentet fra https://regexr.com/3e48o
        String epostValidate = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        boolean test1;
        if (b.getFilm() == null) test1 = false;
        else test1 = b.getFilm().matches(filmValidate);

        boolean test2 = (b.getAntall()+"").matches(antallValidate);
        boolean test3 = b.getFornavn().matches(fnavnValidate);
        boolean test4 = b.getEtternavn().matches(enavnValidate);
        boolean test5 = b.getTelefon().matches(tlfValidate);
        boolean test6 = b.getEpost().matches(epostValidate);

        return test1 && test2 && test3 && test4 && test5 && test6;
    }

    @GetMapping("/hentAlleFilmer")
    public List<Film> hentAlleFilmer(HttpServletResponse response) throws IOException {
        List<Film> filmer = rep.hentAlleFilmer();
        if (filmer == null) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
        return filmer;
    }

    @PostMapping("/leggTilBestilling")
    public void leggTilBestilling(Bestilling kinoBestilling, HttpServletResponse response) throws IOException {
        if (!validerBestilling(kinoBestilling)) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i Validering - prøv igjen senere");
        } else {
            if (rep.lagreBestilling(kinoBestilling) == -1) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            }
        }
    }

    @GetMapping("/hentBestilling")
    public Bestilling hentBestilling(Long id, HttpServletResponse response) throws IOException {
        Bestilling kinoBestilling = rep.hentBestilling(id);
        if(kinoBestilling == null) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
        return kinoBestilling;
    }

    @PutMapping("/endreBestilling")
    public void endreBestilling(@RequestBody Bestilling kinoBestilling, HttpServletResponse response) throws IOException {
        if (rep.endreBestilling(kinoBestilling) == -1) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
    }

    @DeleteMapping("/slettBestilling")
    public void slettBestilling(Long id, HttpServletResponse response) throws IOException {
        if (rep.slettBestilling(id)== -1) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
    }

    @GetMapping("/hentAlleBestillinger")
    public List<Bestilling> hentAlleBestillinger(HttpServletResponse response) throws IOException {
        List<Bestilling> bestillinger = rep.hentAlleBestillinger();

        if (bestillinger == null) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
        return bestillinger;
    }

    @DeleteMapping("/slettAlleBestillinger")
    public void slettAlleBestillinger(HttpServletResponse response) throws IOException {
        if (rep.slettAlleBestillinger() == -1) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
    }
}
