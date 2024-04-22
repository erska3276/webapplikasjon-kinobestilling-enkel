package oslomet.data1700_oblig3.pojo;

/* POJO (Plain Old Java Object) klasse for bruk ved overføring av data
 *  mellom klient og tjener/server. Spring håndterer konverteringen dersom
 *  samme attributt-navn på java-objekt som variabelnavn i javascript-objektet.
 * */
public class Film {

    private long id;
    private String tittel;

    //Konstruktører
    public Film() {}
    public Film(long id, String tittel) {
        this.id = id;
        this.tittel = tittel;
    }

    //get- og set-metoder
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getTittel() {
        return tittel;
    }
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }
}
