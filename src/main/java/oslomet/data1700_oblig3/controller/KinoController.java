package oslomet.data1700_oblig3.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import oslomet.data1700_oblig3.pojo.Bestilling;
import oslomet.data1700_oblig3.pojo.Film;
import oslomet.data1700_oblig3.repository.KinoRepository;

import java.io.IOException;
import java.util.List;

@RestController
public class KinoController {

    @Autowired
    KinoRepository rep;

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
        if (rep.lagreBestilling(kinoBestilling) == -1) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
    }

    //TODO: ENDRE TIL DELETEMAPPING?
    @GetMapping("/slettBestilling")
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

    @PostMapping("/slettAlleBestillinger")
    public void slettAlleBestillinger(HttpServletResponse response) throws IOException {
        if (rep.slettAlleBestillinger() == 0) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
    }

}
