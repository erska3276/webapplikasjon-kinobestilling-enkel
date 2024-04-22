package oslomet.data1700_oblig3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import oslomet.data1700_oblig3.pojo.Bestilling;
import oslomet.data1700_oblig3.pojo.Film;
import oslomet.data1700_oblig3.repository.KinoRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KinoController {

    @Autowired
    KinoRepository rep;

    @GetMapping("/hentAlleFilmer")
    public List<Film> hentAlleFilmer() {
        List<Film> filmer = new ArrayList<>();
        filmer.add(new Film(1, "Last Samurai"));
        filmer.add(new Film(2, "The Dark Knight"));
        filmer.add(new Film(3, "The Godfather"));
        filmer.add(new Film(4, "Edge of Tomorrow"));
        filmer.add(new Film(5, "Nightcrawler"));
        filmer.add(new Film(6, "V for Vendetta"));
        filmer.add(new Film(7, "Con Air"));
        filmer.add(new Film(8, "Kill Bill: Volume 2"));
        filmer.add(new Film(9, "Die Hard"));
        filmer.add(new Film(10, "Pacific Rim"));
        filmer.add(new Film(11, "Dune part 2"));

        return filmer;
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
