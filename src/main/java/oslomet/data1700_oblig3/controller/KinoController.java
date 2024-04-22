package oslomet.data1700_oblig3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import oslomet.data1700_oblig3.pojo.Bestilling;
import oslomet.data1700_oblig3.pojo.Film;
import oslomet.data1700_oblig3.repository.KinoRepository;

import java.util.List;

@RestController
public class KinoController {

    @Autowired
    KinoRepository rep;

    @GetMapping("/hentAlleFilmer")
    public List<Film> hentAlleFilmer() {
        return rep.hentAlleFilmer();
    }

    @PostMapping("/leggTilBestilling")
    public void leggTilBestilling(Bestilling kinoBestilling) {
        rep.lagreBestilling(kinoBestilling);
    }

    @GetMapping("/hentAlleBestillinger")
    public List<Bestilling> hentAlleBestillinger() {
        return rep.hentAlleBestillinger();
    }

    @PostMapping("/slettAlleBestillinger")
    public void slettAlleBestillinger() {
        rep.slettAlleBestillinger();
    }

}
