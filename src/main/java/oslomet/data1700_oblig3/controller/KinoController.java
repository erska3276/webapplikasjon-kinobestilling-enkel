package oslomet.data1700_oblig3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import oslomet.data1700_oblig3.pojo.Bestilling;
import oslomet.data1700_oblig3.pojo.Film;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KinoController {

    public final List<Bestilling> kinoBestillinger = new ArrayList<>();

    @GetMapping("/hentAlleFilmer")
    public List<Film> hentAlleFilmer() {
        List<Film> filmer = new ArrayList<>();
        filmer.add(new Film("Last Samurai"));
        filmer.add(new Film("The Dark Knight"));
        filmer.add(new Film("The Godfather"));
        filmer.add(new Film("Edge of Tomorrow"));
        filmer.add(new Film("Nightcrawler"));
        filmer.add(new Film("V for Vendetta"));
        filmer.add(new Film("Con Air"));
        filmer.add(new Film("Kill Bill: Volume 2"));
        filmer.add(new Film("Die Hard"));
        filmer.add(new Film("Pacific Rim"));
        filmer.add(new Film("Dune part 2"));

        return filmer;
    }

    @PostMapping("/leggTilBestilling")
    public void leggTilBestilling(Bestilling kinoBestilling) {
        kinoBestillinger.add(kinoBestilling);
    }

    @GetMapping("/hentAlleBestillinger")
    public List<Bestilling> hentAlleBestillinger() {
        return kinoBestillinger;
    }

    @PostMapping("/slettAlleBestillinger")
    public void slettAlleBestillinger() {
        kinoBestillinger.clear();
    }

}
