package oslomet.data1700_oblig3.pojo;

/* POJO (Plain Old Java Object) klasse for bruk ved overføring av data
 * mellom klient og tjener/server. Har da attributter, tom-konstruktor,
 * konstruktor som setter alle attributter, og get/set-metoder for alle
 * attributtene. Spring håndterer konverteringen dersom samme attributtnavn
 * på java-objekt som attributtnavn i javascript-objektet fra klient.
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
