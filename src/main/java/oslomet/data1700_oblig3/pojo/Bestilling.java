package oslomet.data1700_oblig3.pojo;

/* POJO (Plain Old Java Object) klasse for bruk ved overføring av data
*  mellom klient og tjener/server. Spring håndterer konverteringen dersom
*  samme attributt-navn på java-objekt som variabelnavn i javascript-objektet.
* */
public class Bestilling {

    private long id;
    private String film;
    private int antall;
    private String fornavn;
    private String etternavn;
    private String telefon;
    private String epost;

    //Konstruktører
    public Bestilling() {}
    public Bestilling(long id, String film, int antall, String fornavn,
                      String etternavn, String telefon, String epost) {
        this.id = id;
        this.film = film;
        this.antall = antall;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.telefon = telefon;
        this.epost = epost;
    }

    //get og set-metoder
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getFilm() {
        return film;
    }
    public void setFilm(String film) {
        this.film = film;
    }
    public int getAntall() {
        return antall;
    }
    public void setAntall(int antall) {
        this.antall = antall;
    }
    public String getFornavn() {
        return fornavn;
    }
    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }
    public String getEtternavn() {
        return etternavn;
    }
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }
    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public String getEpost() {
        return epost;
    }
    public void setEpost(String epost) {
        this.epost = epost;
    }
}
